package cn.edu.xmu.comm.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Roger on 2014/12/7 0007.
 */
@Entity
public class Floor extends Property {

    //region Instance Variables
    /**
     * 层数
     */
    private Integer no;

    /**
     * 所属楼宇
     */
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Building.class)
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;

    /**
     * 包含的房间列表
     */
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Room.class,
            cascade = CascadeType.ALL, mappedBy = "floor")
    private List<Room> roomList;
    //endregion

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

    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }
    //endregion

}
