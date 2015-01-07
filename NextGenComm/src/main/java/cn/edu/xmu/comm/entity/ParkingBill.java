package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.calc.IParkingCalculator;
import cn.edu.xmu.comm.commons.calc.impl.GradientParkingCalculator;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Roger on 2014/12/23 0023.
 *
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class ParkingBill {

    //region Instance Variables
    /**
     * 停车订单编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 车牌号
     */
    private String license;

    /**
     * 开始时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    /**
     * 离开时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    /**
     * 费用
     */
    private BigDecimal fee;

    /**
     * 访问的业主
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Owner.class)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    /**
     * 所在的社区
     */
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Community.class)
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;
    //endregion

    ParkingBill() {
    }

    public ParkingBill(String license, Community community, Owner owner, Date startTime) {
        super();
        this.license = license;
        this.community = community;
        this.owner = owner;
        this.startTime = startTime;
    }

    //region Public Methods

    public void generateParkBill() {
        IParkingCalculator parkingCalculator = new GradientParkingCalculator();
        parkingCalculator.calculate(this);
    }
    //endregion

    //region Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    //endregion

}
