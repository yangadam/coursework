package cn.edu.xmu.comm.domain;

import cn.edu.xmu.comm.commons.CalcutorFactory;
import cn.edu.xmu.comm.commons.IGarbageFeeCalculator;
import cn.edu.xmu.comm.commons.IManageFeeCalculator;
import cn.edu.xmu.comm.commons.IShareCalculator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roger on 2014/12/5 0005.
 */
@Entity
public class Room extends Property {

    //region Instance Variables
    /**
     * 费用类型字符串：公摊、物业管理费、垃圾管理费
     */
    public static final String SHARE = "公摊";
    public static final String MANAGE = "物业管理费";
    //endregion

    //region Public Methods
    public static final String GARBAGE = "垃圾管理费";
    public static final String PUBFUND = "公维金";
    /**
     * 房间全称
     */
    private String fullName;
    /**
     * 房间全称
     */
    private String fullName;
    /**
     * 所属小区
     */
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Community.class)
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;
    /**
     * 所属楼宇
     */
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Building.class)
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;
    /**
     * 所属楼层
     */
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Floor.class)
    @JoinColumn(name = "floor_id", nullable = false)
    private Floor floor;

    //endregion
    /**
     * 拥有者
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            targetEntity = Owner.class)
    @JoinColumn(name = "owner_id", nullable = true)
    private Owner owner;

    /**
     * 计算户水费，户电费，户物业管理费，户公摊，户垃圾费，户公维金
     */
    public void generateRoom(List<BillItem> billItems) {
        generateEnergy(billItems);
        generateShare(billItems);
        generateManageFee(billItems);
        generateGarbageFee(billItems);
        generatePublicFund(billItems);
    }

    /**
     * 计算户水费，户电费
     *
     * @param billItems
     */
    public void generateEnergy(List<BillItem> billItems) {
        for (Device device : getDeviceList()) {
            BillItem billItem = new BillItem();
            billItem.setName(device.getType());
            billItem.setDescription(fullName);
            billItem.setUsage(device.getUsage());
            billItem.setAmount(device.calculate());
            billItem.setOwner(owner);
            billItems.add(billItem);
        }
    }

    /**
     * 计算户公摊
     *
     * @param billItems
     */
    public void generateShare(List<BillItem> billItems) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Device device : getSharedDevice()) {
            String type = device.getShareType();
            IShareCalculator calculator = CalcutorFactory.getShareCalc(type);
            BigDecimal amount = device.calculate();
            BigDecimal shareAmount = calculator.calculateShare(this, device, amount);
            totalAmount = totalAmount.add(shareAmount);
        }
        BillItem billItem = new BillItem();
        billItem.setName(SHARE);
        billItem.setDescription(fullName);
        billItem.setAmount(totalAmount);
        billItem.setOwner(owner);
        billItems.add(billItem);
    }

    /**
     * 计算户物业管理费
     *
     * @param billItems
     */
    public void generateManageFee(List<BillItem> billItems) {
        Community community = getCommunity();
        String type = community.getManageFeeType();
        IManageFeeCalculator calculator = CalcutorFactory.getManageFeeCalc(type);
        BigDecimal amount = calculator.calculate(this);
        BillItem billItem = new BillItem();
        billItem.setName(MANAGE);
        billItem.setDescription(fullName);
        billItem.setAmount(amount);
        billItem.setOwner(owner);
        billItems.add(billItem);
    }

    /**
     * 计算户垃圾费
     *
     * @param billItems
     */
    public void generateGarbageFee(List<BillItem> billItems) {
        Community community = this.community;
        String type = community.getGarbageFeeType();
        IGarbageFeeCalculator calculator = CalcutorFactory.getGarbageFeeCalc(type);
        BigDecimal amount = calculator.calculate(this);
        BillItem billItem = new BillItem();
        billItem.setName(GARBAGE);
        billItem.setDescription(fullName);
        billItem.setAmount(amount);
        billItem.setOwner(owner);
        billItems.add(billItem);
    }

    /**
     * 计算户公维金
     *
     * @param billItems
     */
    public void generatePublicFund(List<BillItem> billItems) {
        Community community = this.community;
        PublicFund publicFund = community.getPublicFund();
        if (publicFund.isNeeded()) {
            BillItem billItem = new BillItem();
            billItem.setName(PUBFUND);
            billItem.setDescription(fullName);
            billItem.setAmount(publicFund.getChargePerRoom());
            billItem.setOwner(owner);
            billItems.add(billItem);
        }
    }

    /**
     * 获取公摊的设备
     *
     * @return
     */
    public List<Device> getSharedDevice() {
        List<Device> devices = new ArrayList<Device>();
        devices.addAll(community.getDeviceList());
        devices.addAll(building.getDeviceList());
        devices.addAll(floor.getDeviceList());
        return devices;
    }

    //region Getters and Setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    //endregion

    //region Constants

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
    //endregion

}
