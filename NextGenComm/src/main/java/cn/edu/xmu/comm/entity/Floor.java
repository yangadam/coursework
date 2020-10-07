package cn.edu.xmu.comm.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
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
public class Floor extends Property {

    @Column(nullable = false)
    private Integer no;
    @ManyToOne(targetEntity = Building.class, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;
    @OneToMany(targetEntity = Room.class, mappedBy = "floor",
            cascade = CascadeType.ALL)
    private List<Room> roomList = new ArrayList<Room>();

    /**
     * 无参构造函数
     */
    Floor() {
    }

    /**
     * 构造函数
     *
     * @param no 楼层号
     */
    Floor(Integer no) {
        this.no = no;
    }

    /**
     * 获取小区
     *
     * @return 小区
     */
    @Override
    public Community getCommunity() {
        return null;
    }

    /**
     * 获取祖先
     *
     * @return 祖先列表
     */
    @Override
    public Property[] getParents() {
        return new Property[]{getBuilding(), getCommunity()};
    }

    /**
     * 获取祖先（包括自己）
     *
     * @return 祖先列表
     */
    @Override
    public Property[] getThisAndParents() {
        return new Property[]{this, getBuilding(), getCommunity()};
    }

    /**
     * 添加房间
     *
     * @param room 要添加的房间
     */
    public void addRoom(Room room) {
        room.setFloor(this);
        room.setUnityCode(unityCode + "R" + room.getNo());
        room.setFullName(building.getNo() + "号楼" + room.getNo());
        room.registerRoom();
        roomList.add(room);
    }

    /**
     * 获得层数
     *
     * @return 层数
     */
    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    /**
     * 获得所属楼宇
     *
     * @return 所属楼宇
     */
    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    /**
     * 获得包含的房间列表
     *
     * @return 包含的房间列表
     */
    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

}
