package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.exception.DifferentCommunityException;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * 业主实体
 * Created by Roger on 2014/12/7 0007.
 *
 * @author Mengmeng Yang
 * @version 2014-12-23
 */
@Entity
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
    @OneToMany(targetEntity = Room.class, mappedBy = "owner")
    private List<Room> roomList;

    /**
     * 拥有的车辆列表
     */
    @OneToMany(targetEntity = Car.class, mappedBy = "owner")
    private List<Car> carList;

    /**
     * 未支付的账单项列表
     * （注意：公维金是单独交的，但是一起算，交到不同的账户。）
     */
    @OneToMany(targetEntity = BillItem.class, mappedBy = "owner")
    private List<BillItem> unpaidBills;
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
    public void generateBill() {

        for (Room room : this.roomList) {
            room.generateRoom(this.unpaidBills);
        }

        for (Car car : this.carList) {
            car.generateCar(this.unpaidBills);
        }

    }
    //endregion

    //region Getters and Setters
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

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public List<BillItem> getUnpaidBills() {
        return unpaidBills;
    }

    public void setUnpaidBills(List<BillItem> unpaidBills) {
        this.unpaidBills = unpaidBills;
    }
    //endregion

}
