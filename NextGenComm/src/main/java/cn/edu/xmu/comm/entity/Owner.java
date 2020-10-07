package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.exception.DeviceException;
import cn.edu.xmu.comm.commons.exception.DifferentCommunityException;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
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

    @ManyToOne(targetEntity = Community.class)
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;
    @OneToMany(targetEntity = Room.class, mappedBy = "owner", cascade = {CascadeType.MERGE})
    private Set<Room> rooms = new HashSet<Room>();
    @OneToMany(targetEntity = Car.class, mappedBy = "owner", cascade = CascadeType.ALL)
    private Set<Car> cars = new HashSet<Car>();
    @OneToMany(targetEntity = BillItem.class, mappedBy = "owner", cascade = CascadeType.ALL)
    private List<BillItem> unpaidBills = new ArrayList<BillItem>();
    @OneToMany(targetEntity = Payment.class, mappedBy = "paidBy", cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<Payment>();

    /**
     * 无参构造函数
     */
    Owner() {
    }

    /**
     * 构造函数
     *
     * @param username    用户名
     * @param password    密码
     * @param name        姓名
     * @param phoneNumber 电话
     * @param email       邮箱
     * @param community   小区
     */
    public Owner(String username, String password, String name, String phoneNumber, String email, Community community) {
        super(username, password, name, phoneNumber, email);
        this.community = community;
    }

    /**
     * 构造函数
     *
     * @param username    用户名
     * @param password    密码
     * @param name        姓名
     * @param phoneNumber 电话
     * @param email       邮箱
     * @param room        房间
     * @throws DifferentCommunityException 小区不同异常
     * @see cn.edu.xmu.comm.commons.exception.DifferentCommunityException
     */
    public Owner(String username, String password, String name, String phoneNumber, String email, Room room)
            throws DifferentCommunityException {
        super(username, password, name, phoneNumber, email);
        addRoom(room);
    }

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
     * 生成账单
     *
     * @throws DeviceException 设备异常
     * @see cn.edu.xmu.comm.commons.exception.DeviceException
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
    public Payment makePayment(Staff receiveBy, List<BillItem> billItems) {
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

    /**
     * 获得代缴总金额
     *
     * @return 总额
     */
    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (BillItem billItem : unpaidBills) {
            total = total.add(billItem.getAmount());
            total = total.add(billItem.getOverDueFee());
        }
        return total;
    }

    /**
     * 获得所属小区
     *
     * @return 所属小区
     */
    @Override
    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    /**
     * 获得拥有的房间列表
     *
     * @return 房间列表
     */
    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    /**
     * 获得拥有的车辆列表
     *
     * @return 车辆列表
     */
    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    /**
     * 获得未支付的账单项列表
     * （注意：公维金是单独交的，但是一起算，交到不同的账户。）
     *
     * @return 未支付的账单项列表
     */
    public List<BillItem> getUnpaidBills() {
        return unpaidBills;
    }

    /**
     * 获得支付列表
     *
     * @return 支付列表
     */
    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
