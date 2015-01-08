package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.calc.IParkingCalculator;
import cn.edu.xmu.comm.commons.calc.impl.GradientParkingCalculator;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 停车单实体
 * Created by Roger on 2014/12/23 0023.
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class ParkingBill {

    //region Public Methods

    //region Instance Variables
    private Integer id;
    //endregion

    //region Constructors
    private String license;
    private Date startTime;
    //endregion

    //region Getters
    private Date endTime;
    private BigDecimal fee;
    private Owner owner;
    private Community community;


    /**
     * 构造函数
     *
     * @param license   车牌号
     * @param community 小区
     * @param owner     业主
     * @param startTime 开始时间
     */
    public ParkingBill(String license, Community community, Owner owner, Date startTime) {
        this.license = license;
        this.community = community;
        this.owner = owner;
        this.startTime = startTime;
    }

    /**
     * 生成停车单
     */
    public void generateParkBill() {
        IParkingCalculator parkingCalculator = new GradientParkingCalculator();
        parkingCalculator.calculate(this);
    }
    //endregion

    /**
     * 获得停车单主键
     *
     * @return 停车单主键
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
     * 获得车牌号
     *
     * @return 车牌号
     */
    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    /**
     * 获得开始时间
     *
     * @return 开始时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获得离开时间
     *
     * @return 离开时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    public Date getEndTime() {
        return endTime;
    }
    //endregion

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获得费用
     *
     * @return 费用
     */
    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    /**
     * 获得访问的业主
     *
     * @return 访问的业主
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Owner.class)
    @JoinColumn(name = "owner_id", nullable = false)
    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    /**
     * 获得所在的社区
     *
     * @return 所在的社区
     */
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Community.class)
    @JoinColumn(name = "community_id", nullable = false)
    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }
    //endregion

}
