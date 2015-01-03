package cn.edu.xmu.comm.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 小区实体
 * Created by Roger on 2014/12/7 0007.
 *
 * @author Mengmeng Yang
 * @version 2014-12-22
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"name"})
        }
)
public class Community extends Property {

    //region Instance Variables
    /**
     * 小区名称
     */
    @Column(nullable = false)
    private String name;

    /**
     * 物业主任
     */
    @OneToOne(targetEntity = Director.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "deirector_id")
    private Director director;

    /**
     * 包含的楼宇列表
     */
    @OneToMany(mappedBy = "community", targetEntity = Building.class,
            cascade = CascadeType.ALL)
    private List<Building> buildingList = new ArrayList<Building>();

    /**
     * 包含的停车场
     */
    @OneToMany(mappedBy = "community", targetEntity = ParkingLot.class, cascade = CascadeType.ALL)
    private List<ParkingLot> parkingLotList = new ArrayList<ParkingLot>();

    /**
     * 管理的公维金
     */
    @OneToOne(targetEntity = PublicFund.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "public_fund_id")
    private PublicFund publicFund;

    /**
     * 计算费用的梯度
     */
    @OneToMany(targetEntity = Gradient.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "community_id", nullable = false)
    private Set<Gradient> gradients = new HashSet<Gradient>();

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

    /**
     * 滞纳金的计算方式，可能的方式
     */
    private String overDueFeeType;

    /**
     * 滞纳金率
     */
    private BigDecimal overDueFeeRate;
    //endregion

    Community() {
        super();
    }

    public Community(String name) {
        super();
        this.name = name;
        this.unityCode = "C";
    }

    @Override
    public Property[] getParents() {
        return new Property[0];
    }

    @Override
    public Property[] getThisAndParents() {
        return new Property[]{this};
    }

    @Override
    public Community getCommunity() {
        return this;
    }


    //region Public Methods

    /**
     * 添加楼宇
     *
     * @param building 要添加的楼宇
     */
    public void addBuilding(Building building) {
        building.setCommunity(this);
        building.setUnityCode(unityCode + "B" + building.getNo());
        buildingList.add(building);
        childCount++;
    }

    public void assignDirector(Director director) {
        this.director = director;
        director.setCommunity(this);
    }

    /**
     * 批量添加楼宇
     *
     * @param buildings 楼宇列表
     */
    public void addBuildings(List<Building> buildings) {
        for (Building building : buildings) {
            building.setCommunity(this);
        }
        this.buildingList.addAll(buildings);
    }

    /**
     * 通过楼宇号获取楼宇
     *
     * @param no 楼宇号
     * @return 楼宇（未找到为空）
     */
    public Building getBuilding(Integer no) {
        for (Building building : buildingList) {
            if (building.getNo().equals(no)) {
                return building;
            }
        }
        return null;
    }

    /**
     * 通过停车场类型获取停车场
     *
     * @param type 停车场类型
     * @return parkingLot
     */
    public ParkingLot getParkingLot(ParkingLot.ParkingLotStatus type) {
        for (ParkingLot parkingLot : parkingLotList) {
            if (parkingLot.getType() == type) {
                return parkingLot;
            }
        }
        return null;
    }
    //endregion

    //region Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Building> getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(List<Building> buildingList) {
        this.buildingList = buildingList;
    }

    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public void setParkingLotList(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public PublicFund getPublicFund() {
        return publicFund;
    }

    public void setPublicFund(PublicFund publicFund) {
        this.publicFund = publicFund;
    }

    public Set<Gradient> getGradients() {
        return gradients;
    }

    public void setGradients(Set<Gradient> gradients) {
        this.gradients = gradients;
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

    public String getOverDueFeeType() {
        return overDueFeeType;
    }

    public void setOverDueFeeType(String overDueFeeType) {
        this.overDueFeeType = overDueFeeType;
    }

    public BigDecimal getOverDueFeeRate() {
        return overDueFeeRate;
    }

    public void setOverDueFeeRate(BigDecimal overDueFeeRate) {
        this.overDueFeeRate = overDueFeeRate;
    }

    //endregion


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Community)) return false;

        Community community = (Community) o;

        if (name != null ? !name.equals(community.name) : community.name != null) return false;
        return true;
    }

}
