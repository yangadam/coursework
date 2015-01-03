package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.exception.DeviceException;
import cn.edu.xmu.comm.commons.exception.DifferentCommunityException;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 业主实体
 * Created by Roger on 2014/12/7 0007.
 *
 * @author Mengmeng Yang
 * @version 2014-12-23
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class Owner extends User {

    //region Instance Variables
    /**
     * 所属小区
     */
    @ManyToOne(targetEntity = Community.class)
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

    /**
     * 拥有的房间列表
     */
    @OneToMany(targetEntity = Room.class, mappedBy = "owner",
            cascade = {CascadeType.MERGE})
    private List<Room> roomList = new ArrayList<Room>();

    /**
     * 拥有的车辆列表
     */
    @OneToMany(targetEntity = Car.class, mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Car> carList = new HashSet<Car>();

    /**
     * 未支付的账单项列表
     * （注意：公维金是单独交的，但是一起算，交到不同的账户。）
     */
    @OneToMany(targetEntity = BillItem.class, mappedBy = "owner", cascade = CascadeType.ALL)
    private List<BillItem> unpaidBills = new ArrayList<BillItem>();

    @OneToMany(targetEntity = Payment.class, mappedBy = "paidBy", cascade = CascadeType.ALL)
    private List<Payment> paymentList = new ArrayList<Payment>();

    //endregion

    Owner() {
    }

    public Owner(String username, String password, String name, Community community) {
        super(username, password, name);
        this.community = community;
    }

    public Owner(String username, String password, String name, Room room)
            throws DifferentCommunityException {
        super(username, password, name);
        addRoom(room);
    }

    //region Public Methods

    /**
     * 添加房间
     *
     * @param room 要添加的房间
     * @throws DifferentCommunityException 小区不同
     * @see DifferentCommunityException
     */
    public void addRoom(Room room) throws DifferentCommunityException {
        if (community != null && !community.equals(room.getCommunity())) {
            throw new DifferentCommunityException("添加了不同的小区");
        }
        community = room.getCommunity();
        room.setOwner(this);
        roomList.add(room);
    }

    /**
     * 批量添加房间
     *
     * @param rooms 房间列表
     * @throws DifferentCommunityException 小区不同
     * @see DifferentCommunityException
     */
    public void addRoomBatch(List<Room> rooms) throws DifferentCommunityException {
        for (Room room : rooms) {
            if (community != null && !community.equals(room.getCommunity())) {
                throw new DifferentCommunityException("添加了不同的小区");
            }
            community = room.getCommunity();
            room.setOwner(this);
        }
        roomList.addAll(rooms);
    }

    /**
     * 生成账单
     */
    public void generateBill() throws DeviceException {

        for (Room room : this.roomList) {
            room.generateRoom(this.unpaidBills);
        }

        for (Car car : this.carList) {
            car.generateCar(this.unpaidBills);
        }

    }

    /**
     * 支付账单项
     *
     * @param receiveBy 收款人
     * @param billItems 账单
     * @return 支付记录
     */
    public Payment payBillItems (User receiveBy, List<BillItem> billItems) {
        for (BillItem billItem : billItems) {
            unpaidBills.remove(billItem);
            billItem.setBillItemStatus(BillItem.BillItemStatus.PAID);
        }
        return new Payment(this, receiveBy, billItems);
    }

    /**
     * 获得超期欠缴费清单
     *
     * @return 清单列表
     */
    public List<BillItem> getOverDueBillItems() {
        List<BillItem> resultBillItems = new ArrayList<BillItem>();
        for (BillItem billItem : unpaidBills) {
            if (billItem.isOverDue()) {
                billItem.updateOverDueFee();
                resultBillItems.add(billItem);
            }
        }
        return resultBillItems;
    }
    //endregion

    //region Getters and Setters
    @Override
    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public Set<Car> getCarList() {
        return carList;
    }

    public void setCarList(Set<Car> carList) {
        this.carList = carList;
    }

    public List<BillItem> getUnpaidBills() {
        for (BillItem billItem : unpaidBills) {
            billItem.updateOverDueFee();
        }
        return unpaidBills;
    }

    public void setUnpaidBills(List<BillItem> unpaidBills) {
        this.unpaidBills = unpaidBills;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    //endregion

}
