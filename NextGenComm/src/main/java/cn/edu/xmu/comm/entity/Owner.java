package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.exception.DeviceException;
import cn.edu.xmu.comm.commons.exception.DifferentCommunityException;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
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
    private Set<Room> rooms = new HashSet<Room>();

    /**
     * 拥有的车辆列表
     */
    @OneToMany(targetEntity = Car.class, mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Car> cars = new HashSet<Car>();

    /**
     * 未支付的账单项列表
     * （注意：公维金是单独交的，但是一起算，交到不同的账户。）
     */
    @OneToMany(targetEntity = BillItem.class, mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<BillItem> unpaidBills = new HashSet<BillItem>();

    /**
     * 支付
     */
    @OneToMany(targetEntity = Payment.class, mappedBy = "paidBy", cascade = CascadeType.ALL)
    private Set<Payment> payments = new HashSet<Payment>();

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
        rooms.add(room);
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
        this.rooms.addAll(rooms);
    }

    /**
     * 生成账单
     */
    public void generateBill() throws DeviceException {

        for (Room room : this.rooms) {
            room.generateRoom(this.unpaidBills);
        }

        for (Car car : this.cars) {
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
    public Payment makePayment(Staff receiveBy, Set<BillItem> billItems) {
        if (billItems == null || billItems.size() == 0) {
            return null;
        }
        unpaidBills.removeAll(billItems);
        for (BillItem billItem : billItems) {
            billItem.setOwner(null);
            billItem.setStatus(BillItem.BillItemStatus.PAID);
        }
        return new Payment(this, receiveBy, billItems);
    }

    /**
     * 获得超期欠缴费清单
     *
     * @return 清单列表
     */
    public Set<BillItem> getOverDueBillItems() {
        Set<BillItem> resultBillItems = new HashSet<BillItem>();
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

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public Set<BillItem> getUnpaidBills() {
        for (BillItem billItem : unpaidBills) {
            billItem.updateOverDueFee();
        }
        return unpaidBills;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (BillItem billItem : unpaidBills) {
            total = total.add(billItem.getAmount());
            total = total.add(billItem.getOverDueFee());
        }
        return total;
    }

    public Payment makePayment(Staff recieveBy) {
        return makePayment(recieveBy, unpaidBills);
    }

    //endregion

}
