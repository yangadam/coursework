package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.exception.DeviceException;
import cn.edu.xmu.comm.commons.persistence.DataEntity;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

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
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
    @Column(name = "device_values", precision = 2)
    private SortedMap<Date, Double> values = new TreeMap<Date, Double>();

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
    private Double currentValue;

    /**
     * 上次读数
     */
    private Double lastValue;

    private Boolean isCalculated;
    //endregion

    Device() {
    }

    public Device(Integer id, String no, Double currentValue, Double lastValue, DeviceType type) {
        this.id = id;
        this.no = no;
        this.currentValue = currentValue;
        this.lastValue = lastValue;
        this.type = type;
    }

    /**
     * 构造函数（私有表）
     *
     * @param no       设备号
     * @param property 设备所属位置
     * @param value    初始值
     * @param type     设备类型
     */
    public Device(String no, Property property, Double value, DeviceType type) {
        this(no, property, value, type, null);
        this.isCalculated = true;
    }

    /**
     * 构造函数（公摊表）
     *
     * @param no        设备号
     * @param property  设备所属位置
     * @param value     初始值
     * @param type      设备类型
     * @param shareType 公摊类型
     */
    public Device(String no, Property property, Double value, DeviceType type, String shareType) {
        this.no = no;
        this.type = type;
        this.shareType = shareType;
        this.values.put(new Date(), value);
        this.community = property.getCommunity();
        this.lastValue = 0.0;
        this.currentValue = 0.0;
        this.isCalculated = true;
        property.addDevice(this);
    }

    //region Public Methods;

    /**
     * 获取本月用量
     *
     * @return 用量
     */
    public Double getUsage() {
        return currentValue - lastValue;
    }

    /**
     * 计算本月费用
     *
     * @return 费用
     */
    public BigDecimal calculate() throws DeviceException {
//        if (isCalculated)
//            return BigDecimal.ZERO;
        //throw new DeviceException("未录入本月的读数");
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal amount = BigDecimal.ZERO;
        Double lastValue = 0.0;
        Double curValue;
        for (Object o : getGradientMap().entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            if (getUsage().compareTo((Double) entry.getKey()) == 1) {
                curValue = (Double) entry.getKey() - lastValue;
                amount = ((BigDecimal) entry.getValue()).multiply(BigDecimal.valueOf(curValue));
                totalAmount.add(amount);
            } else if (getUsage().compareTo((Double) entry.getKey()) == -1) {
                curValue = getUsage() - lastValue;
                amount = ((BigDecimal) entry.getValue()).multiply(BigDecimal.valueOf(curValue));
                totalAmount = totalAmount.add(amount);
                break;
            } else if (getUsage().compareTo((Double) entry.getKey()) == 1) {
                curValue = (Double) entry.getKey() - lastValue;
                amount = ((BigDecimal) entry.getValue()).multiply(BigDecimal.valueOf(curValue));
                totalAmount = totalAmount.add(amount);
            }
            lastValue = (Double) entry.getKey();
        }
        setIsCalculatedTrue();
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
    public void addValue(Date date, Double value) throws DeviceException {
        if (!isCalculated)
            throw new DeviceException("上次费用尚未计算");
        if (date.before(getLastTime()))
            throw new DeviceException("不能添加最后一次录入时间之后的读数");
        if (values.containsKey(date))
            throw new DeviceException("该时间点已录入数据，请勿重复添加");
        if (!isValueValidate(date, value))
            throw new DeviceException("表内读数应随时间递增");
        lastValue = currentValue;
        currentValue = value;
        getValues().put(date, value);
        setIsCalculatedFalse();
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
     * @throws DeviceException
     */
    public void updateValue(Date date, Double value) {
        // 若新值放入Map中仍date递增，value递增，视为有效插入
        Double tempValue = values.get(date);
        values.put(date, value);
        if (!isValueValidate(date, value))
            values.put(date, tempValue);
        currentValue = value;
    }

    /**
     * 修改最后一个读数
     *
     * @param value 修改后的值
     */
    public void updateLastValue(Double value) {
        Date date = values.lastKey();
        updateValue(date, value);
    }

    /**
     * 判断(date, value)插入后values是否date递增，value递增
     * 即在所有的date和value中的时间是否相同
     *
     * @param date  日期
     * @param value 读数
     * @return 是否符合要求
     */
    public boolean isValueValidate(Date date, Double value) {
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
    public int orderOfValues(Double value) {
        int order = 0;
        for (Double v : values.values()) {
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
    private Date lastButOneKey(SortedMap<Date, Double> sortedMap) {
        if (sortedMap.size() <= 1)
            return null;
        Date lastDate = sortedMap.lastKey();
        Double lastValue = sortedMap.get(sortedMap.lastKey());
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

    public Double getCurrentValue() {
        return currentValue;
    }

    /**
     * 设置当前读数
     *
     * @param currentValue 当前读数
     *                     保护当前读数和上月读数只能通过添加值和删除值实现
     */
    private void setCurrentValue(Double currentValue) {
        this.currentValue = currentValue;
    }

    public Double getLastValue() {
        return lastValue;
    }

    /**
     * 设置上次读数
     *
     * @param lastValue 上次读数
     *                  保护当前读数和上月读数只能通过添加值和删除值实现
     */
    private void setLastValue(Double lastValue) {
        this.lastValue = lastValue;
    }

    public SortedMap<Date, Double> getValues() {
        return values;
    }

    /**
     * 设置读数表
     *
     * @param values 读数表
     *               保护当前读数和上月读数只能通过添加值和删除值实现
     */
    public void setValues(SortedMap<Date, Double> values) {
        this.values = values;
    }

    public Boolean getIsCalculated() {
        return isCalculated;
    }

    private void setIsCalculatedTrue() {
        this.isCalculated = true;
    }

    private void setIsCalculatedFalse() {
        this.isCalculated = false;
    }
    //endregion

    /**
     * 设备类型
     */
    public enum DeviceType {
        WATER("水表", "水费"), ELECTRICITY("电表", "电费");

        private String typeName;
        private String feeType;

        private DeviceType(String typeName, String feeType) {
            this.typeName = typeName;
            this.feeType = feeType;
        }

        public String getFeeType() {
            return feeType;
        }

        @Override
        public String toString() {
            return typeName;
        }

    }

}
