package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.calc.impl.AreaManageFeeCalculator;
import cn.edu.xmu.comm.commons.calc.impl.DateOverdueFineCalculator;
import cn.edu.xmu.comm.commons.calc.impl.FixGarbageFeeCalculator;
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
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
public class Community extends Property {

    @Column(nullable = false)
    private String name;
    @OneToOne(targetEntity = Director.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "deirector_id")
    private Director director;
    @OneToMany(mappedBy = "community", targetEntity = Building.class,
            cascade = CascadeType.ALL)
    private List<Building> buildingList = new ArrayList<Building>();
    @OneToMany(mappedBy = "community", targetEntity = ParkingLot.class, cascade = CascadeType.ALL)
    private List<ParkingLot> parkingLotList = new ArrayList<ParkingLot>();
    @OneToOne(targetEntity = PublicFund.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "public_fund_id")
    private PublicFund publicFund;
    @OneToMany(targetEntity = Gradient.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "community_id", nullable = false)
    private Set<Gradient> gradients = new HashSet<Gradient>();

    private String manageFeeType;
    private BigDecimal manageFee;

    private String garbageFeeType;
    private BigDecimal garbageFee;
    private String overDueFeeType;
    private BigDecimal overDueFeeRate;

    /**
     * 无参构造函数
     */
    Community() {
        super();
    }

    /**
     * 构造函数
     *
     * @param name 小区名
     */
    public Community(String name) {
        super();
        this.name = name;
        this.unityCode = "";
        this.garbageFee = BigDecimal.valueOf(4);
        this.garbageFeeType = FixGarbageFeeCalculator.class.getSimpleName();
        this.manageFee = BigDecimal.valueOf(2);
        this.manageFeeType = AreaManageFeeCalculator.class.getSimpleName();
        this.overDueFeeRate = BigDecimal.valueOf(0.5);
        this.overDueFeeType = DateOverdueFineCalculator.class.getSimpleName();
    }

    /**
     * 获取祖先
     *
     * @return 祖先列表
     */
    @Override
    public Property[] getParents() {
        return new Property[0];
    }

    /**
     * 获取祖先（包括自己）
     *
     * @return 祖先列表
     */
    @Override
    public Property[] getThisAndParents() {
        return new Property[]{this};
    }

    /**
     * 获取小区
     *
     * @return 小区（this）
     */
    @Override
    public Community getCommunity() {
        return this;
    }

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

    /**
     * 指定管理员
     *
     * @param director 管理员
     */
    public void assignDirector(Director director) {
        this.director = director;
        director.setCommunity(this);
    }

    /**
     * 通过停车场类型获取停车场
     *
     * @param type 停车场类型
     * @return 停车场
     */
    public ParkingLot getParkingLot(ParkingLot.ParkingLotStatus type) {
        for (ParkingLot parkingLot : parkingLotList) {
            if (parkingLot.getType() == type) {
                return parkingLot;
            }
        }
        return null;
    }

    /**
     * 获得小区名称
     *
     * @return 小区名称
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获得物业主任
     *
     * @return 物业主任
     */
    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    /**
     * 获得包含的楼宇列表
     *
     * @return 包含的楼宇列表
     */
    public List<Building> getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(List<Building> buildingList) {
        this.buildingList = buildingList;
    }

    /**
     * 获得包含的停车场
     *
     * @return 包含的停车场
     */
    public List<ParkingLot> getParkingLotList() {
        return parkingLotList;
    }

    public void setParkingLotList(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    /**
     * 获得管理的公维金
     *
     * @return 管理的公维金
     */
    public PublicFund getPublicFund() {
        return publicFund;
    }

    public void setPublicFund(PublicFund publicFund) {
        this.publicFund = publicFund;
    }

    /**
     * 获得计算费用的梯度
     *
     * @return 计算费用的梯度
     */
    public Set<Gradient> getGradients() {
        return gradients;
    }

    public void setGradients(Set<Gradient> gradients) {
        this.gradients = gradients;
    }

    /**
     * 获得物业管理费的计算方式
     *
     * @return 物业管理费的计算方式
     */
    public String getManageFeeType() {
        return manageFeeType;
    }

    public void setManageFeeType(String manageFeeType) {
        this.manageFeeType = manageFeeType;
    }

    /**
     * 获得物业管理费金额
     *
     * @return 物业管理费金额
     */
    public BigDecimal getManageFee() {
        return manageFee;
    }

    public void setManageFee(BigDecimal manageFee) {
        this.manageFee = manageFee;
    }

    /**
     * 获得垃圾处理费的计算方式
     *
     * @return 垃圾处理费的计算方式
     */
    public String getGarbageFeeType() {
        return garbageFeeType;
    }

    public void setGarbageFeeType(String garbageFeeType) {
        this.garbageFeeType = garbageFeeType;
    }

    /**
     * 获得垃圾处理费金额
     *
     * @return 垃圾处理费金额
     */
    public BigDecimal getGarbageFee() {
        return garbageFee;
    }

    public void setGarbageFee(BigDecimal garbageFee) {
        this.garbageFee = garbageFee;
    }

    /**
     * 获得滞纳金的计算方式
     *
     * @return 滞纳金的计算方式
     */
    public String getOverDueFeeType() {
        return overDueFeeType;
    }

    public void setOverDueFeeType(String overDueFeeType) {
        this.overDueFeeType = overDueFeeType;
    }

    /**
     * 获得滞纳金率
     *
     * @return 滞纳金率
     */
    public BigDecimal getOverDueFeeRate() {
        return overDueFeeRate;
    }

    public void setOverDueFeeRate(BigDecimal overDueFeeRate) {
        this.overDueFeeRate = overDueFeeRate;
    }

    /**
     * 判断是否相等
     *
     * @param o 比较的对象
     * @return 相等返回true
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Community)) return false;
        Community community = (Community) o;
        return !(name != null ? !name.equals(community.name) : community.name != null);
    }
}
