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

    /**
     * 构造函数
     *
     * @param no 楼宇号
     */
    public Building(Integer no, Integer floorCount) {
        this.no = no;
        this.unityCode = "B" + no;
        initFloors(floorCount);
    }
    //endregion


    @Override
    public Property[] getParents() {
        return new Property[]{getCommunity()};
    }

    @Override
    public Property[] getThisAndParents() {
        return new Property[]{this, getCommunity()};
    }

    /**
     * 初始化楼层
     *
     * @param floorCount 楼层数
     */
    private void initFloors(Integer floorCount) {
        for (int i = 1; i <= floorCount; i++) {
            Floor floor = new Floor(i);
            addFloor(floor);
        }
    }

    /**
     * 添加楼层
     *
     * @param floor 要添加的楼层
     */
    private void addFloor(Floor floor) {
        floor.setBuilding(this);
        floor.setUnityCode(unityCode + "F" + floor.getNo());
        floorList.add(floor);
        childCount++;
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

    @Override
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
