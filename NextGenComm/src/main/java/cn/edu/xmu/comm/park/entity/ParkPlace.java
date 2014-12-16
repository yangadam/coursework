package cn.edu.xmu.comm.park.entity;

import cn.edu.xmu.comm.pms.entity.Community;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Roger on 2014/12/7 0007.
 */
@Entity
public class ParkPlace {

    //region Instance Variables
    /**
     * 停车位主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 所属社区
     */
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Community.class)
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

    /**
     * 停车位在小区位置
     */
    private String position;

    /**
     * 每月管理费
     */
    private BigDecimal monthlyFee;
    //endregion

    //region Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public BigDecimal getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(BigDecimal monthlyFee) {
        this.monthlyFee = monthlyFee;
    }
    //endregion

}
