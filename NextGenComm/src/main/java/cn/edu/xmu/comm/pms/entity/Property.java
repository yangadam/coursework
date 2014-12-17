package cn.edu.xmu.comm.pms.entity;

import cn.edu.xmu.comm.commons.persistence.DataEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roger on 2014/12/9 0009.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Property extends DataEntity {

    //region Instance Variables
    /**
     * 房产主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    /**
     * 设备列表
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
            targetEntity = Device.class, mappedBy = "property")
    protected List<Device> deviceList = new ArrayList<Device>();

    /**
     * 房间总数
     */
    protected Integer houseCount;

    /**
     * 已入住房间总数
     */
    protected Integer usedHouseCount;

    /**
     * 房产面积
     */
    protected Double houseArea;

    /**
     * 已使用的房产面积
     */
    protected Double usedHouseArea;
    //endregion

    /**
     * 无参构造函数
     */
    protected Property() {
        houseCount = 0;
        houseArea = 0.0;
        usedHouseCount = 0;
        usedHouseArea = 0.0;
    }

    public Property(Integer houseCount, Integer usedHouseCount, Double houseArea, Double usedHouseArea) {
        this.houseCount = houseCount;
        this.usedHouseCount = usedHouseCount;
        this.houseArea = houseArea;
        this.usedHouseArea = usedHouseArea;
    }

    //region Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }

    public Integer getHouseCount() {
        return houseCount;
    }

    public void setHouseCount(Integer houseCount) {
        this.houseCount = houseCount;
    }

    public Integer getUsedHouseCount() {
        return usedHouseCount;
    }

    public void setUsedHouseCount(Integer usedHouseCount) {
        this.usedHouseCount = usedHouseCount;
    }

    public Double getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(Double houseArea) {
        this.houseArea = houseArea;
    }

    public Double getUsedHouseArea() {
        return usedHouseArea;
    }

    public void setUsedHouseArea(Double usedHouseArea) {
        this.usedHouseArea = usedHouseArea;
    }
    //endregion

}
