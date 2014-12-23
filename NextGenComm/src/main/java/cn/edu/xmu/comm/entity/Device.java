package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.persistence.DataEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Roger on 2014/12/7 0007.
 */
@Entity
public class Device extends DataEntity {

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
     * 拥有该设备的房产
     */
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Property.class)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    /**
     * 读数的列表
     */
    @OneToMany(fetch = FetchType.EAGER, targetEntity = DeviceVaule.class,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "device_id", nullable = false)
    private List<DeviceVaule> values;

    /**
     * 类型
     * <li>{@link #WATER}</li>
     * <li>{@link #ELECTRICITY}</li>
     */
    private String type;

    /**
     * 梯度定义
     */
    @ElementCollection
    @CollectionTable(
            name = "gradient",
            joinColumns = @JoinColumn(name = "device_id")
    )
    @Column(name = "gradient_value")
    private Map<BigDecimal, BigDecimal> gradient = new TreeMap<BigDecimal, BigDecimal>();

    /**
     * 公摊类型
     */
    private String shareType;
    //endregion

    //region Public Methods;

    /**
     * 获取本月用量
     *
     * @return
     */
    public BigDecimal getUsage() {
        BigDecimal lastValue = values.get(values.size() - 2).getValue();
        BigDecimal currentValue = values.get(values.size() - 1).getValue();
        return currentValue.subtract(lastValue);
    }

    /**
     * 计算本月费用
     *
     * @return
     */
    public BigDecimal calculate() {
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal amount;
        BigDecimal lastValue = BigDecimal.ZERO;
        BigDecimal curValue;
        Iterator it = gradient.entrySet().iterator();
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

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public List<DeviceVaule> getValues() {
        return values;
    }

    public void setValues(List<DeviceVaule> values) {
        this.values = values;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<BigDecimal, BigDecimal> getGradient() {
        return gradient;
    }

    public void setGradient(Map<BigDecimal, BigDecimal> gradient) {
        this.gradient = gradient;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }
    //endregion

    //region Constants
    /**
     * 设备类型
     */
    public static final String WATER = "水费";
    public static final String ELECTRICITY = "电费";
    //endregion

}
