package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.persistence.DataEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
     * 读数的列表
     */
    @OneToMany(targetEntity = DeviceValue.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "device_id", nullable = false)
    private List<DeviceValue> values = new ArrayList<DeviceValue>();
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
        this.values.add(new DeviceValue(value));
        this.community = property.getCommunity();
        property.addDevice(this);
    }

    //region Public Methods;

    /**
     * 获取本月用量
     *
     * @return 用量
     */
    public BigDecimal getUsage() {
        BigDecimal lastValue = values.get(values.size() - 2).getValue();
        BigDecimal currentValue = values.get(values.size() - 1).getValue();
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

    public List<DeviceValue> getValues() {
        return values;
    }

    public void setValues(List<DeviceValue> values) {
        this.values = values;
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
