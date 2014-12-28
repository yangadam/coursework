package cn.edu.xmu.comm.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Roger on 2014/12/7 0007.
 *
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
     * 所属停车场
     */
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ParkingLot.class)
    @JoinColumn(name = "parkinglot_id", nullable = false)
    private ParkingLot parkingLot;

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

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
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
