package cn.edu.xmu.comm.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

/**
 * 楼层实体
 * Created by Roger on 2014/12/7 0007.
 *
 * @author Mengmeng Yang
 * @version 2014-12-22
 */
@Entity
@DynamicInsert
@DynamicUpdate
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Floor extends Property {

    //region Instance Variables
    /**
     * 层数
     */
    @Column(nullable = false)
    private Integer no;

    /**
     * 所属楼宇
     */
    @ManyToOne(targetEntity = Building.class, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;

    /**
     * 包含的房间列表
     */
    @OneToMany(targetEntity = Room.class, mappedBy = "floor",
            cascade = CascadeType.ALL)
    private List<Room> roomList;
    //endregion

    Floor() {
    }

    public Floor(Integer no, Building building) {
        this.no = no;
        building.addFloor(this);
    }

    /**
     * 添加房间
     *
     * @param room 要添加的房间
     */
    public void addRoom(Room room) {
        room.setFloor(this);
        roomList.add(room);
    }

    /**
     * 批量添加房间
     *
     * @param rooms 房间列表
     */
    public void addRooms(List<Room> rooms) {
        for (Room room : rooms) {
            room.setFloor(this);
        }
        roomList.addAll(rooms);
    }

    /**
     * 通过房间号获取房间
     *
     * @param no 房间号
     * @return 房间（未找到为空）
     */
    public Room getRoom(String no) {
        for (Room room : roomList) {
            if (room.getNo().equals(no)) {
                return room;
            }
        }
        return null;
    }

    //region Getters and Setters
    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Community getCommunity() {
        return building.getCommunity();
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }
    //endregion

}
