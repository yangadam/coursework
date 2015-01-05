package cn.edu.xmu.comm.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Roger on 2014/12/7 0007.
 *
 */
@Entity
public class ParkingPlace {

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

    /**
     * 车位状态，
     * FREE:可用车位
     * LOCK:已锁定车位
     * RENT:已租用车位
     */
    private ParkPlaceStatus parkPlaceStatus;
    //endregion

    public ParkingPlace() {

    }

    public ParkingPlace(String position, ParkingLot parkingLot, BigDecimal monthlyFee) {
        this.position = position;
        this.parkingLot = parkingLot;
        this.parkPlaceStatus = ParkPlaceStatus.FREE;
        this.monthlyFee = monthlyFee;
    }

    public ParkingPlace(String position, ParkingLot parkingLot) {
        this.position = position;
        this.parkingLot = parkingLot;
        this.parkPlaceStatus = ParkPlaceStatus.FREE;
        this.monthlyFee = BigDecimal.ZERO;
    }

    //region Update ParkPlace Status
    /**
     * 锁定车位
     */
    public void lockParkPlace() {
        setParkPlaceStatus(ParkPlaceStatus.LOCK);
    }

    /**
     * 释放车位
     */
    public void freeParkPlace() {
        setParkPlaceStatus(ParkPlaceStatus.FREE);
    }

    /**
     * 租用车位
     */
    public void rentParkPlace() {
        setParkPlaceStatus(ParkPlaceStatus.RENT);
    }
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

    public ParkPlaceStatus getParkPlaceStatus() {
        return parkPlaceStatus;
    }

    public void setParkPlaceStatus(ParkPlaceStatus parkPlaceStatus) {
        this.parkPlaceStatus = parkPlaceStatus;
    }
//endregion

    /**
     * 车位状态
     */
    public enum ParkPlaceStatus {
        FREE("可用车位"), LOCK("已锁定车位"), RENT("已租用车位");

        private String typeName;

        private ParkPlaceStatus(String typeName) {
            this.typeName = typeName;
        }

        @Override
        public String toString() {
            return typeName;
        }
    }
}
