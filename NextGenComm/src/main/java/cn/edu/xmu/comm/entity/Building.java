package cn.edu.xmu.comm.entity;

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
public class Building extends Property {

    //region Public Methods

    //region Private Instance Variables
    private Integer no;
    private Community community;
    private List<Floor> floorList = new ArrayList<Floor>();

    /**
     * 无参构造函数
     */
    Building() {
    }
    //endregion

    //region Constructors

    /**
     * 构造函数
     *
     * @param no         楼宇号
     * @param floorCount 楼层数
     */
    public Building(Integer no, Integer floorCount) {
        this.no = no;
        this.unityCode = "B" + no;
        initFloors(floorCount);
    }

    /**
     * 获取祖先
     *
     * @return 祖先列表
     */
    @Override
    public Property[] getParents() {
        return new Property[]{getCommunity()};
    }
    //endregion

    //region Getters

    /**
     * 获取祖先（包括自己）
     *
     * @return 祖先列表
     */
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
    //endregion

    /**
     * 获得楼宇号
     *
     * @return 楼宇号
     */
    @Column(nullable = false)
    public Integer getNo() {
        return no;
    }

    //region Setters
    public void setNo(Integer no) {
        this.no = no;
    }

    /**
     * 获得所属小区
     *
     * @return 所属小区
     */
    @Override
    @ManyToOne(targetEntity = Community.class, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "community_id", nullable = false)
    public Community getCommunity() {
        return community;
    }
    //endregion

    public void setCommunity(Community community) {
        this.community = community;
    }

    /**
     * 获得包含的楼层列表
     *
     * @return 包含的楼层列表
     */
    @OneToMany(targetEntity = Floor.class, mappedBy = "building",
            cascade = CascadeType.ALL)
    public List<Floor> getFloorList() {
        return floorList;
    }

    public void setFloorList(List<Floor> floorList) {
        this.floorList = floorList;
    }
    //endregion

}
