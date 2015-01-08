package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.calc.CalculatorFactory;
import cn.edu.xmu.comm.commons.calc.IGarbageFeeCalculator;
import cn.edu.xmu.comm.commons.calc.IManageFeeCalculator;
import cn.edu.xmu.comm.commons.calc.IShareCalculator;
import cn.edu.xmu.comm.commons.exception.DeviceException;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
public class Room extends Property {

    //region Constants
    /**
     * 费用类型：公摊费
     */
    public static final String SHARE = "公摊";
    /**
     * 费用类型：物业管理费
     */
    public static final String MANAGE = "物业管理费";
    /**
     * 费用类型：垃圾管理费
     */
    public static final String GARBAGE = "垃圾管理费";
    /**
     * 费用类型：公维金
     */
    public static final String PUBFUND = "公维金";
    //endregion

    //region Public Methods
    //region Instance Variables
    private String no;
    private String fullName;
    private Floor floor;
    private Owner owner;

    /**
     * 无参构造函数
     */
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

    /**
     * 获取祖先
     *
     * @return 祖先列表
     */
    @Override
    public Property[] getParents() {
        return new Property[]{getFloor(), getBuilding(), getCommunity()};
    }

    /**
     * 获取祖先（包括自己）
     *
     * @return 祖先列表
     */
    @Override
    public Property[] getThisAndParents() {
        return new Property[]{this, getFloor(), getBuilding(), getCommunity()};
    }

    /**
     * 获得所属楼宇
     *
     * @return 楼宇
     */
    public Building getBuilding() {
        return floor.getBuilding();
    }

    /**
     * 获得所属小区
     *
     * @return 小区
     */
    @Override
    public Community getCommunity() {
        return getBuilding().getCommunity();
    }

    /**
     * 计算户水费，户电费，户物业管理费，户公摊，户垃圾费，户公维金
     *
     * @param billItems 未支付账单
     * @throws DeviceException 设备异常
     * @see cn.edu.xmu.comm.commons.exception.DeviceException
     */
    public void generateRoom(List<BillItem> billItems) throws DeviceException {
        generateEnergy(billItems);
        generateShare(billItems);
        generateManageFee(billItems);
        generateGarbageFee(billItems);
        generatePublicFund(billItems);
    }
    //endregion

    //region Constructors

    /**
     * 计算户水费，户电费
     *
     * @param billItems 未支付账单
     * @throws DeviceException 设备异常
     * @see cn.edu.xmu.comm.commons.exception.DeviceException
     */
    public void generateEnergy(List<BillItem> billItems) throws DeviceException {
        for (Device device : getDeviceList()) {
            String name = device.getType().getFeeType();
            BillItem billItem = new BillItem(name, fullName, device.calculate(), device.getUsage(), owner);
            billItems.add(billItem);
        }
    }

    /**
     * 计算户公摊
     *
     * @param billItems 未支付账单
     * @throws DeviceException 设备异常
     * @see cn.edu.xmu.comm.commons.exception.DeviceException
     */
    public void generateShare(List<BillItem> billItems) throws DeviceException {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Device device : getSharedDevice()) {
            String type = device.getShareType();
            IShareCalculator calculator = CalculatorFactory.getCalculator(type);
            BigDecimal amount = device.calculate();
            BigDecimal shareAmount = calculator.calculateShare(this, device, amount);
            totalAmount = totalAmount.add(shareAmount);
        }
        BillItem billItem = new BillItem(SHARE, fullName, totalAmount, null, owner);
        billItems.add(billItem);
    }
    //endregion

    //region Getters

    /**
     * 计算户物业管理费
     *
     * @param billItems 未支付账单
     */
    public void generateManageFee(List<BillItem> billItems) {
        Community community = getCommunity();
        String type = community.getManageFeeType();
        IManageFeeCalculator calculator = CalculatorFactory.getCalculator(type);
        BigDecimal amount = calculator.calculate(this);
        BillItem billItem = new BillItem(MANAGE, fullName, amount, null, owner);
        billItems.add(billItem);
    }

    /**
     * 计算户垃圾费
     *
     * @param billItems 未支付账单
     */
    public void generateGarbageFee(List<BillItem> billItems) {
        Community community = getCommunity();
        String type = community.getGarbageFeeType();
        IGarbageFeeCalculator calculator = CalculatorFactory.getCalculator(type);
        BigDecimal amount = calculator.calculate(this);
        BillItem billItem = new BillItem(GARBAGE, fullName, amount, null, owner);
        billItems.add(billItem);
    }

    /**
     * 计算户公维金
     *
     * @param billItems 未支付账单
     */
    public void generatePublicFund(List<BillItem> billItems) {
        Community community = getCommunity();
        PublicFund publicFund = community.getPublicFund();
        if (publicFund.isNeeded()) {
            BillItem billItem = new BillItem(PUBFUND, fullName, publicFund.getChargePerRoom(), null, owner);
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
        for (Property property : getParents()) {
            devices.addAll(property.getDeviceList());
        }
        return devices;
    }
    //endregion

    /**
     * 获得房间号
     *
     * @return 房间号
     */
    public String getNo() {
        return no;
    }

    //region Setters
    public void setNo(String no) {
        this.no = no;
    }

    /**
     * 获得房间全称
     *
     * @return 房间全称
     */
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    //endregion

    /**
     * 获得所属楼层
     *
     * @return 所属楼层
     */
    @ManyToOne(targetEntity = Floor.class, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "floor_id", nullable = false)
    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    /**
     * 获得拥有者
     *
     * @return 拥有者
     */
    @ManyToOne(targetEntity = Owner.class, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "owner_id", nullable = true)
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

    //region Private and Friendly Methods

    /**
     * 房间登记
     */
    void registerRoom() {
        for (Property property : getParents()) {
            property.register(this);
        }
    }

    /**
     * 房间入住
     */
    private void checkInRoom() {
        usedHouseCount++;
        usedHouseArea = houseArea;
        for (Property property : getParents()) {
            property.checkIn(this);
        }
    }
    //endregion

}
