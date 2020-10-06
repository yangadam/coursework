package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.exception.DeviceException;
import cn.edu.xmu.comm.commons.persistence.DataEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
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
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"no"})
})
public class Device extends DataEntity {

    //region Public Methods;

    //region Private Instance Variables
    private Integer id;
    private String no;
    private Community community;
    private Property property;
    private SortedMap<Date, Double> values = new TreeMap<Date, Double>();
    //endregion

    //region Constructors
    private DeviceType type;
    private Gradient gradient = null;
    private String shareType;
    //endregion

    //region Getters
    private Double currentValue;
    private Double lastValue;
    private Boolean isCalculated;

    /**
     * 无参构造函数
     */
    Device() {
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
     * @throws DeviceException 设备异常
     * @see cn.edu.xmu.comm.commons.exception.DeviceException
     */
    public BigDecimal calculate() throws DeviceException {
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal amount;
        Double lastValue = 0.0;
        Double curValue;
        for (Object o : gradient.getGradient().entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            if (getUsage().compareTo((Double) entry.getKey()) == 1) {
                curValue = (Double) entry.getKey() - lastValue;
                amount = ((BigDecimal) entry.getValue()).multiply(BigDecimal.valueOf(curValue));
                totalAmount = totalAmount.add(amount);
            } else if (getUsage().compareTo((Double) entry.getKey()) < 1) {
                curValue = getUsage() - lastValue;
                amount = ((BigDecimal) entry.getValue()).multiply(BigDecimal.valueOf(curValue));
                totalAmount = totalAmount.add(amount);
                break;
            }
            lastValue = (Double) entry.getKey();
        }
        isCalculated = Boolean.TRUE;
        return totalAmount;
    }

    /**
     * 添加读数
     *
     * @param date  日期
     * @param value 读数
     * @throws DeviceException 设备异常
     * @see cn.edu.xmu.comm.commons.exception.DeviceException
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
        isCalculated = Boolean.TRUE;
    }

    /**
     * 依据日期更新读数
     *
     * @param date  日期
     * @param value 读数
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
    //endregion

    /**
     * 获得设备主键
     *
     * @return 设备主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    //region Setters
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获得设备号
     *
     * @return 设备号
     */
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    /**
     * 获得所属小区
     *
     * @return 所属小区
     */
    @ManyToOne(targetEntity = Community.class)
    @JoinColumn(name = "community_id", nullable = false)
    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    /**
     * 获得拥有该设备的房产
     *
     * @return 拥有该设备的房产
     */
    @ManyToOne(targetEntity = Property.class)
    @JoinColumn(name = "property_id", nullable = false)
    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    /**
     * 获得设备读数
     *
     * @return 设备读数
     */
    @ElementCollection
    @CollectionTable(
            name = "device_value",
            joinColumns = @JoinColumn(name = "device_id")
    )
    @OrderBy
    @Column(name = "device_values")
    public SortedMap<Date, Double> getValues() {
        return values;
    }

    public void setValues(SortedMap<Date, Double> values) {
        this.values = values;
    }

    /**
     * 获得设备类型
     *
     * @return 设备类型
     * @see cn.edu.xmu.comm.entity.Device.DeviceType
     */
    public DeviceType getType() {
        return type;
    }
    //endregion

    //region Inner Enum

    public void setType(DeviceType type) {
        this.type = type;
    }
    //endregion

    /**
     * 获得梯度
     *
     * @return 梯度
     */
    @ManyToOne(targetEntity = Gradient.class)
    @JoinColumn(name = "gradient_id")
    public Gradient getGradient() {
        return gradient;
    }

    public void setGradient(Gradient gradient) {
        this.gradient = gradient;
    }

    /**
     * 获得公摊类型
     *
     * @return 公摊类型
     */
    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    /**
     * 获得当前读数
     *
     * @return 当前读数
     */
    public Double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Double currentValue) {
        this.currentValue = currentValue;
    }

    /**
     * 获得上次读数
     *
     * @return 上次读数
     */
    public Double getLastValue() {
        return lastValue;
    }

    public void setLastValue(Double lastValue) {
        this.lastValue = lastValue;
    }

    /**
     * 获得有无计算过
     *
     * @return 已经计算返回true
     */
    public Boolean getIsCalculated() {
        return isCalculated;
    }

    public void setIsCalculated(Boolean isCalculated) {
        this.isCalculated = isCalculated;
    }

    /**
     * 获取最后一次的录入时间
     *
     * @return 最后一次的录入时间
     */
    private Date getLastTime() {
        return values.lastKey();
    }
    //endregion

    //region Private Methods

    /**
     * 判断(date, value)插入后values是否date递增，value递增
     * 即在所有的date和value中的时间是否相同
     *
     * @param date  日期
     * @param value 读数
     * @return 是否符合要求
     */
    private boolean isValueValidate(Date date, Double value) {
        return orderOfDates(date) == orderOfValues(value);
    }

    /**
     * 从小到大排序 date在values所有date中的位置
     *
     * @param date 日期
     * @return 位置
     */
    private int orderOfDates(Date date) {
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
    private int orderOfValues(Double value) {
        int order = 0;
        for (Double v : values.values()) {
            if (value.compareTo(v) <= 0)
                break;
            order += 1;
        }
        return order;
    }

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
    //endregion

}
