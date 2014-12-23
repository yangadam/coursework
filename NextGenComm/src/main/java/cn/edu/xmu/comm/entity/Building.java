package cn.edu.xmu.comm.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 楼宇实体
 * Created by Roger on 2014/12/7 0007.
 *
 * @author Mengmeng Yang
 * @version 2014-12-22
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Building extends Property {

    //region Instance Variables
    /**
     * 楼宇号
     */
    @Column(nullable = false)
    private Integer no;

    /**
     * 楼宇名称
     */
    private String name;

    /**
     * 所属小区
     */
    @ManyToOne(targetEntity = Community.class, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

    /**
     * 包含的楼层列表
     */
    @OneToMany(targetEntity = Floor.class, mappedBy = "building",
            cascade = CascadeType.ALL)
    private List<Floor> floorList = new ArrayList<Floor>();
    //endregion

    //region Constructors
    Building() {
    }

    public Building(Integer no, Community community) {
        this(no, String.valueOf(no).concat("号楼"), community);
    }

    public Building(Integer no, String name, Community community) {
        this.no = no;
        this.name = name;
        community.addBuilding(this);
    }
    //endregion

    /**
     * 添加楼层
     *
     * @param floor 要添加的楼层
     */
    public void addFloor(Floor floor) {
        floor.setBuilding(this);
        floorList.add(floor);
    }

    /**
     * 批量添加楼层
     *
     * @param floors 楼层列表
     */
    public void addFloors(List<Floor> floors) {
        for (Floor floor : floors) {
            floor.setBuilding(this);
        }
        floorList.addAll(floors);
    }

    /**
     * 通过楼层号获取楼层
     *
     * @param no 楼层号
     * @return 楼层（未找到为空）
     */
    public Floor getFloor(Integer no) {
        for (Floor floor : floorList) {
            if (floor.getNo().equals(no)) {
                return floor;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public List<Floor> getFloorList() {
        return floorList;
    }

    public void setFloorList(List<Floor> floorList) {
        this.floorList = floorList;
    }
    //endregion

}
