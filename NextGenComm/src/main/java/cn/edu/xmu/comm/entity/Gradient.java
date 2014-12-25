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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 梯度定义
     */
    @ElementCollection
    @CollectionTable(
            name = "gradient_def",
            joinColumns = @JoinColumn(name = "device_id")
    )
    @Column(name = "gradient_VALUE")
    private Map<BigDecimal, BigDecimal> gradient = new TreeMap<BigDecimal, BigDecimal>();
    //endregion

    //region Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<BigDecimal, BigDecimal> getGradient() {
        return gradient;
    }

    public void setGradient(Map<BigDecimal, BigDecimal> gradient) {
        this.gradient = gradient;
    }
    //endregion

}
