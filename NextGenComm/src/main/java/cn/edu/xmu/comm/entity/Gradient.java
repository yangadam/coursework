package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.persistence.DataEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

/**
 * 收费梯度类
 *
 * @author Mengmeng Yang
 * @version 12/24/2014
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class Gradient extends DataEntity {

    //region Instance Variables
    /**
     * 梯度主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 适用设备类型
     *
     * @see cn.edu.xmu.comm.entity.Device.DeviceType
     */
    private Device.DeviceType type;

    /**
     * 梯度定义
     */
    @ElementCollection
    @CollectionTable(
            name = "gradient_def",
            joinColumns = @JoinColumn(name = "gradient_id")
    )
    @Column(name = "gradient_VALUE")
    private Map<Double, BigDecimal> gradient = new TreeMap<Double, BigDecimal>();
    //endregion

    Gradient() {
    }

    /**
     * 构造函数
     *
     * @param unitPrice 单价
     */
    public Gradient(BigDecimal unitPrice) {
        gradient.put(Double.MAX_VALUE, unitPrice);
    }

    /**
     * 构造函数
     *
     * @param readings 读数
     * @param prices   价格
     */
    public Gradient(Double[] readings, BigDecimal[] prices) {
        for (int i = 0; i < readings.length; i++) {
            gradient.put(readings[i], prices[i]);
        }
        gradient.put(Double.MAX_VALUE, prices[readings.length]);
    }

    //region Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Device.DeviceType getType() {
        return type;
    }

    public void setType(Device.DeviceType type) {
        this.type = type;
    }

    public Map<Double, BigDecimal> getGradient() {
        return gradient;
    }

    public void setGradient(Map<Double, BigDecimal> gradient) {
        this.gradient = gradient;
    }
    //endregion

}
