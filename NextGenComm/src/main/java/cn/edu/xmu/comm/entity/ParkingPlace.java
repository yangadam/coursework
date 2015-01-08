package cn.edu.xmu.comm.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 停车位实体
 * Created by Roger on 2014/12/7 0007.
 *
 * @author Mengmeng Yang
 * @version 2014-12-7
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class ParkingPlace {

    //region Update ParkPlace Status

    //region Instance Variables
    private Integer id;
    private ParkingLot parkingLot;
    private String position;
    //endregion

    //region Constructors
    private BigDecimal monthlyFee;
    private ParkingPlaceStatus status;

    /**
     * 无参构造函数
     */
    public ParkingPlace() {

    }
    //endregion

    //region Getters and Setters

    /**
     * 构造函数
     *
     * @param position   位置
     * @param parkingLot 停车场
     * @param monthlyFee 每月费用
     */
    public ParkingPlace(String position, ParkingLot parkingLot, BigDecimal monthlyFee) {
        this.position = position;
        this.parkingLot = parkingLot;
        this.status = ParkingPlaceStatus.FREE;
        this.monthlyFee = monthlyFee;
    }

    /**
     * 构造函数
     *
     * @param position   位置
     * @param parkingLot 停车场
     */
    public ParkingPlace(String position, ParkingLot parkingLot) {
        this.position = position;
        this.parkingLot = parkingLot;
        this.status = ParkingPlaceStatus.FREE;
        this.monthlyFee = BigDecimal.ZERO;
    }

    /**
     * 锁定车位
     */
    public void lockParkPlace() {
        setStatus(ParkingPlaceStatus.LOCK);
    }

    /**
     * 释放车位
     */
    public void freeParkPlace() {
        setStatus(ParkingPlaceStatus.FREE);
    }

    /**
     * 租用车位
     */
    public void rentParkPlace() {
        setStatus(ParkingPlaceStatus.RENT);
    }
    //endregion

    /**
     * 获得停车位主键
     *
     * @return 停车位主键
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
     * 获得所属停车场
     *
     * @return 所属停车场
     */
    @ManyToOne(targetEntity = ParkingLot.class)
    @JoinColumn(name = "parkinglot_id", nullable = false)
    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    /**
     * 获得停车位在小区位置
     *
     * @return 停车位在小区位置
     */
    public String getPosition() {
        return position;
    }
    //endregion

    //region InnerEnum

    public void setPosition(String position) {
        this.position = position;
    }
    //endregion

    /**
     * 获得每月管理费
     *
     * @return 每月管理费
     */
    public BigDecimal getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(BigDecimal monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    /**
     * 获得车位状态
     *
     * @return 车位状态
     * @see cn.edu.xmu.comm.entity.ParkingPlace.ParkingPlaceStatus
     */
    public ParkingPlaceStatus getStatus() {
        return status;
    }

    public void setStatus(ParkingPlaceStatus status) {
        this.status = status;
    }
    /**
     * 车位状态
     */
    public enum ParkingPlaceStatus {
        FREE("可用车位"), LOCK("已锁定车位"), RENT("已租用车位");

        private String typeName;

        private ParkingPlaceStatus(String typeName) {
            this.typeName = typeName;
        }

        @Override
        public String toString() {
            return typeName;
        }
    }
    //endregion

}
