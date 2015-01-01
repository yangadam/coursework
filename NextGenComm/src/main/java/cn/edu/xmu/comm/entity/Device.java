package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.exception.DeviceException;
import cn.edu.xmu.comm.commons.persistence.DataEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * 设备类
 * Created by Roger on 2014/12/7 0007.
 *
 * @author Mengmeng Yang
 * @version 12/24/2014 0024
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"no"})
        }
)
public class Device extends DataEntity {

    //region Constants
    /**
     * 设备类型
     */
    public static final String WATER = "水费";
    public static final String ELECTRICITY = "电费";
    //endregion

    //region Instance Variables
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 设备号
     */
    private String no;

    /**
     * 所属小区
     */
    @ManyToOne(targetEntity = Community.class)
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

    /**
     * 拥有该设备的房产
     */
    @ManyToOne(targetEntity = Property.class)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    /**
     * 梯度定义
     */
    @ElementCollection
    @CollectionTable(
            name = "device_value",
            joinColumns = @JoinColumn(name = "device_id")
    )
    @OrderBy
    @Column(name = "device_values")
    private SortedMap<Date, BigDecimal> values = new TreeMap<Date, BigDecimal>();

    /**
     * 类型
     *
     * @see cn.edu.xmu.comm.entity.Device.DeviceType
     */
    private DeviceType type;

    /**
     * 梯度
     */
    @ManyToOne(targetEntity = Gradient.class)
    @JoinColumn(name = "gradient_id")
    private Gradient gradient = null;

    /**
     * 公摊类型
     */
    private String shareType;

    /**
     * 当前读数
     */
    private BigDecimal currentValue;

    /**
     * 上次读数
     */
    private BigDecimal lastValue;
    //endregion

    Device() {
    }

    /**
     * 构造函数（私有表）
     *  @param no       设备号
     * @param property 设备所属位置
     * @param value    初始值
     * @param type     设备类型
     */
    public Device(String no, Property property, BigDecimal value, DeviceType type) {
        this(no, property, value, type, null);
    }

    /**
     * 构造函数（公摊表）
     *  @param no        设备号
     * @param property  设备所属位置
     * @param value     初始值
     * @param type      设备类型
     * @param shareType 公摊类型
     */
    public Device(String no, Property property, BigDecimal value, DeviceType type, String shareType) {
        this.no = no;
        this.type = type;
        this.shareType = shareType;
        this.values.put(new Date(), value);
        this.community = property.getCommunity();
        this.lastValue = BigDecimal.ZERO;
        this.currentValue = BigDecimal.ZERO;
        property.addDevice(this);
    }

    //region Public Methods;

    /**
     * 获取本月用量
     *
     * @return 用量
     */
    public BigDecimal getUsage() {
        return currentValue.subtract(lastValue);
    }

    /**
     * 计算本月费用
     *
     * @return 费用
     */
    public BigDecimal calculate() {
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal amount;
        BigDecimal lastValue = BigDecimal.ZERO;
        BigDecimal curValue;
        Iterator it = getGradientMap().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (getUsage().compareTo((BigDecimal) entry.getKey()) == 1) {
                curValue = ((BigDecimal) entry.getKey()).subtract(lastValue);
                amount = ((BigDecimal) entry.getValue()).multiply(curValue);
                totalAmount.add(amount);
            } else {
                curValue = getUsage().subtract(lastValue);
                amount = ((BigDecimal) entry.getValue()).multiply(curValue);
                totalAmount = totalAmount.add(amount);
            }
            lastValue = (BigDecimal) entry.getKey();
        }
        return totalAmount;
    }

    /**
     * 获取最后一次的录入时间
     */
    public Date getLastTime() {
        return values.lastKey();
    }

    /**
     * 添加读数
     *
     * @param date  日期
     * @param value 读数
     */
    public void addValue(Date date, BigDecimal value) throws DeviceException {
        if (date.before(getLastTime()))
            throw new DeviceException("不能添加最后一次录入时间之后的读数");
        if (values.containsKey(date))
            throw new DeviceException("该时间点已录入数据，请勿重复添加");
        if (!isValueValidate(date, value))
            throw new DeviceException("表内读数应随时间递增");
        lastValue = currentValue;
        currentValue = value;
        getValues().put(date, value);
    }

    /**
     * 删除最后一次时间
     */
    public void delValue() throws DeviceException {
        if (values.size() == 1)
            throw new DeviceException("该表中已无数据");
        currentValue = lastValue;
        values.remove(values.lastKey());
        if (values.size() == 1)
            lastValue = values.get(values.lastKey());
        else
            lastValue = values.get(lastButOneKey(values));
    }

    /**
     * 依据日期更新读数
     *
     * @param date  日期
     * @param value 读数
     * @throws Exception
     */
    public void updateValue(Date date, BigDecimal value) throws DeviceException {
        if (!values.containsKey(date))
            throw new DeviceException("没有" + date.toString() + "时刻的读数");
        // 若新值放入Map中仍date递增，value递增，视为有效插入
        BigDecimal tempValue = values.get(date);
        values.put(date, value);
        if (!isValueValidate(date, value))
            values.put(date, tempValue);
    }

    /**
     * 判断(date, value)插入后values是否date递增，value递增
     * 即在所有的date和value中的时间是否相同
     *
     * @param date  日期
     * @param value 读数
     * @return 是否符合要求
     */
    public boolean isValueValidate(Date date, BigDecimal value) {
        return orderOfDates(date) == orderOfValues(value);
    }

    /**
     * 从小到大排序 date在values所有date中的位置
     *
     * @param date 日期
     * @return 位置
     */
    public int orderOfDates(Date date) {
        int order = 0;
        for (Date d : values.keySet()) {
            if (date.compareTo(d) <= 0)
                break;
            order += 1;
        }
        return order;
    }

    /**
     * 从小到大排序 value在values所有value中的位置
     *
     * @param value 日期
     * @return 位置
     */
    public int orderOfValues(BigDecimal value) {
        int order = 0;
        for (BigDecimal v : values.values()) {
            if (value.compareTo(v) <= 0)
                break;
            order += 1;
        }
        return order;
    }

    /**
     * 返回一个sortedMap倒数第二个键(如果有的话)
     *
     * @param sortedMap 排序的映射
     * @return 倒数第二个键 否则返回null
     */
    private Date lastButOneKey(SortedMap<Date, BigDecimal> sortedMap) {
        if (sortedMap.size() <= 1)
            return null;
        Date lastDate = sortedMap.lastKey();
        BigDecimal lastValue = sortedMap.get(sortedMap.lastKey());
        sortedMap.remove(lastDate);
        Date result = values.lastKey();
        sortedMap.put(lastDate, lastValue);
        return result;
    }
    //endregion

    //region Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public DeviceType getType() {
        return type;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    public Gradient getGradient() {
        return gradient;
    }


    public void setGradient(Gradient gradient) {
        this.gradient = gradient;
    }

    public Map<Double, BigDecimal> getGradientMap() {
        return gradient.getGradient();
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    private BigDecimal getCurrentValue() {
        return currentValue;
    }

    /**
     * 设置当前读数
     *
     * @param currentValue 当前读数
     *                     保护当前读数和上月读数只能通过添加值和删除值实现
     */
    private void setCurrentValue(BigDecimal currentValue) {
        this.currentValue = currentValue;
    }

    public BigDecimal getLastValue() {
        return lastValue;
    }

    /**
     * 设置上次读数
     *
     * @param lastValue 上次读数
     *                  保护当前读数和上月读数只能通过添加值和删除值实现
     */
    private void setLastValue(BigDecimal lastValue) {
        this.lastValue = lastValue;
    }

    public SortedMap<Date, BigDecimal> getValues() {
        return values;
    }

    /**
     * 设置读数表
     *
     * @param values 读数表
     *               保护当前读数和上月读数只能通过添加值和删除值实现
     */
    private void setValues(SortedMap<Date, BigDecimal> values) {
        this.values = values;
    }

    //endregion

    /**
     * 设备类型
     */
    public enum DeviceType {
        WATER("水表"), ELECTRICITY("电表");

        private String typeName;

        private DeviceType(String typeName) {
            this.typeName = typeName;
        }

        @Override
        public String toString() {
            return typeName;
        }
    }

}
