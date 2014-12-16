package cn.edu.xmu.comm.pms.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Roger on 2014/12/7 0007.
 */
@Entity
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
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Community.class)
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

    /**
     * 包含的楼层列表
     */
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Floor.class,
            cascade = CascadeType.ALL, mappedBy = "building")
    private List<Floor> floorList;
    //endregion

    public Floor getFloorByNo(Integer no) {
        for (Floor floor : floorList) {
            if (floor.getNo().equals(no)) {

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
