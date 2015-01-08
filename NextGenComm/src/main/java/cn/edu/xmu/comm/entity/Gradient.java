package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.persistence.DataEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Map;
import java.util.SortedMap;
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

    //region Constructors

    //region Private Instance Variables
    private Integer id;
    private Device.DeviceType type;
    private SortedMap<Double, BigDecimal> gradient = new TreeMap<Double, BigDecimal>();
    //endregion

    //region Getters and Setters

    /**
     * 无参构造函数
     */
    Gradient() {
    }

    /**
     * 构造函数
     *
     * @param unitPrice 单价
     * @param type      类型
     */
    public Gradient(BigDecimal unitPrice, Device.DeviceType type) {
        this.type = type;
        gradient.put(Double.MAX_VALUE, unitPrice);
    }

    /**
     * 构造函数
     *
     * @param readings 读数
     * @param prices   价格
     * @param type     类型
     */
    public Gradient(Double[] readings, BigDecimal[] prices, Device.DeviceType type) {
        this.type = type;
        for (int i = 0; i < readings.length; i++) {
            gradient.put(readings[i], prices[i]);
        }
        gradient.put(Double.MAX_VALUE, prices[readings.length]);
    }
    //endregion

    /**
     * 获得梯度主键
     *
     * @return 梯度主键
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
     * 获得适用设备类型
     *
     * @return 类型
     * @see cn.edu.xmu.comm.entity.Device.DeviceType
     */
    public Device.DeviceType getType() {
        return type;
    }
    //endregion

    public void setType(Device.DeviceType type) {
        this.type = type;
    }

    /**
     * 获得梯度定义
     *
     * @return 梯度定义
     */
    @ElementCollection
    @CollectionTable(
            name = "gradient_def",
            joinColumns = @JoinColumn(name = "gradient_id")
    )
    @OrderBy
    @Column(name = "gradient_VALUE")
    public Map<Double, BigDecimal> getGradient() {
        return gradient;
    }

    public void setGradient(SortedMap<Double, BigDecimal> gradient) {
        this.gradient = gradient;
    }
    //endregion

}
