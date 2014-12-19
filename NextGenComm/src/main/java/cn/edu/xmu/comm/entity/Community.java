package cn.edu.xmu.comm.entity;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roger on 2014/12/7 0007.
 */
@Entity
@DynamicInsert
public class Community extends Property {

    //region Instance Variables
    /**
     * 小区名称
     */
    @Column(nullable = false)
    private String name;

    /**
     * 包含的楼宇列表
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "community", targetEntity = Building.class)
    private List<Building> buildingList = new ArrayList<Building>();

    /**
     * 包含的停车位
     */
    @OneToMany(mappedBy = "community", targetEntity = ParkPlace.class)
    private List<ParkPlace> parkingLot;

    /**
     * 管理的公维金
     */
    @OneToOne(targetEntity = PublicFund.class)
    @JoinColumn(name = "public_fund_id")
    private PublicFund publicFund;

    /**
     * 物业管理费的计算方式，可能的方式，固定，按面积。。。
     */
    private String manageFeeType;

    /**
     * 物业管理费金额
     */
    private BigDecimal manageFee;

    /**
     * 垃圾处理费的计算方式，可能的方式，固定，按面积。。。
     */
    private String garbageFeeType;

    /**
     * 垃圾处理费金额
     */
    private BigDecimal garbageFee;
    //endregionss

    Community() {
    }

    public Community(String name) {
        super();
        this.name = name;
    }

    public Building getBuildingByNo(Integer no) {
        for (Building building : buildingList) {
            if (building.getNo().equals(no)) {
                return building;
            }
        }
        return null;
    }

    //region Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Building> getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(List<Building> buildingList) {
        this.buildingList = buildingList;
    }

    public List<ParkPlace> getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(List<ParkPlace> parkingLot) {
        this.parkingLot = parkingLot;
    }

    public PublicFund getPublicFund() {
        return publicFund;
    }

    public void setPublicFund(PublicFund publicFund) {
        this.publicFund = publicFund;
    }

    public String getManageFeeType() {
        return manageFeeType;
    }

    public void setManageFeeType(String manageFeeType) {
        this.manageFeeType = manageFeeType;
    }

    public BigDecimal getManageFee() {
        return manageFee;
    }

    public void setManageFee(BigDecimal manageFee) {
        this.manageFee = manageFee;
    }

    public String getGarbageFeeType() {
        return garbageFeeType;
    }

    public void setGarbageFeeType(String garbageFeeType) {
        this.garbageFeeType = garbageFeeType;
    }

    public BigDecimal getGarbageFee() {
        return garbageFee;
    }

    public void setGarbageFee(BigDecimal garbageFee) {
        this.garbageFee = garbageFee;
    }
    //endregion

}
