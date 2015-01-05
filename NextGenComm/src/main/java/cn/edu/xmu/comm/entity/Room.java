package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.calc.CalculatorFactory;
import cn.edu.xmu.comm.commons.calc.IGarbageFeeCalculator;
import cn.edu.xmu.comm.commons.calc.IManageFeeCalculator;
import cn.edu.xmu.comm.commons.calc.IShareCalculator;
import cn.edu.xmu.comm.commons.exception.DeviceException;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 房间实体
 * Created by Roger on 2014/12/5 0005.
 *
 * @author Mengmeng Yang
 * @version 2014-12-22
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Room extends Property {

    //region Constants
    /**
     * 费用类型字符串：公摊、物业管理费、垃圾管理费
     */
    public static final String SHARE = "公摊";
    public static final String MANAGE = "物业管理费";
    public static final String GARBAGE = "垃圾管理费";
    public static final String PUBFUND = "公维金";
    //endregion

    //region Instance Variables
    /**
     * 房间号
     */
    private String no;

    /**
     * 房间全称
     */
    private String fullName;

    /**
     * 所属楼层
     */
    @ManyToOne(targetEntity = Floor.class, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "floor_id", nullable = false)
    private Floor floor;

    /**
     * 拥有者
     */
    @ManyToOne(targetEntity = Owner.class, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "owner_id", nullable = true)
    private Owner owner;
    //endregion

    Room() {
    }

    /**
     * 构造函数
     *
     * @param no        房间号
     * @param houseArea 房间面积
     */
    public Room(String no, Double houseArea) {
        super(houseArea);
        this.no = no;
    }

    @Override
    public Property[] getParents() {
        return new Property[]{getFloor(), getBuilding(), getCommunity()};
    }

    @Override
    public Property[] getThisAndParents() {
        return new Property[]{this, getFloor(), getBuilding(), getCommunity()};
    }

    @Override
    public void preDelete() {
        floor.preDelete();
    }

    //region Public Methods

    /**
     * 计算户水费，户电费，户物业管理费，户公摊，户垃圾费，户公维金
     *
     * @param billItems 未支付账单
     */
    public void generateRoom(Set<BillItem> billItems) throws DeviceException {
        generateEnergy(billItems);
        generateShare(billItems);
        generateManageFee(billItems);
        generateGarbageFee(billItems);
        generatePublicFund(billItems);
    }

    /**
     * 计算户水费，户电费
     *
     * @param billItems 未支付账单
     */
    public void generateEnergy(Set<BillItem> billItems) throws DeviceException {
        for (Device device : getDeviceList()) {
            BillItem billItem = new BillItem();
            billItem.setName(device.getType().getFeeType());
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
     * @param billItems 未支付账单
     */
    public void generateShare(Set<BillItem> billItems) throws DeviceException {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Device device : getSharedDevice()) {
            String type = device.getShareType();
            IShareCalculator calculator = CalculatorFactory.getCalculator(type);
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
     * @param billItems 未支付账单
     */
    public void generateManageFee(Set<BillItem> billItems) {
        Community community = getCommunity();
        String type = community.getManageFeeType();
        IManageFeeCalculator calculator = CalculatorFactory.getCalculator(type);
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
     * @param billItems 未支付账单
     */
    public void generateGarbageFee(Set<BillItem> billItems) {
        Community community = getCommunity();
        String type = community.getGarbageFeeType();
        IGarbageFeeCalculator calculator = CalculatorFactory.getCalculator(type);
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
     * @param billItems 未支付账单
     */
    public void generatePublicFund(Set<BillItem> billItems) {
        Community community = getCommunity();
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
     * @return 公摊设备
     */
    public List<Device> getSharedDevice() {
        List<Device> devices = new ArrayList<Device>();
        devices.addAll(getCommunity().getDeviceList());
        devices.addAll(getBuilding().getDeviceList());
        devices.addAll(floor.getDeviceList());
        return devices;
    }
    //endregion

    //region Getters and Setters
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public Building getBuilding() {
        return floor.getBuilding();
    }

    @Override
    public Community getCommunity() {
        return getBuilding().getCommunity();
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        if (this.owner == null) {
            checkInRoom();
        }
        this.owner = owner;
    }
    //endregion

    void registerRoom() {
        for (Property property : getParents()) {
            property.register(this);
        }
    }

    private void checkInRoom() {
        usedHouseCount++;
        usedHouseArea = houseArea;
        for (Property property : getParents()) {
            property.checkIn(this);
        }
    }

}
