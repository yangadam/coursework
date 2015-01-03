package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.persistence.DataEntity;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 房产类
 * Created by Roger on 2014/12/7 0007.
 *
 * @author Mengmeng Yang
 * @version 2014-12-22
 */
@Entity
@DynamicInsert
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
     * 统一编码
     */
    protected String unityCode;

    /**
     * 数量
     */
    protected Integer childCount;

    /**
     * 设备列表
     */
    @OneToMany(targetEntity = Device.class, mappedBy = "property",
            cascade = CascadeType.ALL)
    protected Set<Device> deviceList = new HashSet<Device>();

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
        usedHouseCount = 0;
        houseArea = 0.0;
        usedHouseArea = 0.0;
        childCount = 0;
    }

    protected Property(Double area) {
        houseCount = 1;
        usedHouseCount = 0;
        houseArea = area;
        usedHouseArea = 0.0;
        childCount = 0;
    }

    /**
     * 获得所属小区
     *
     * @return 小区
     */
    public abstract Community getCommunity();

    /**
     * 获取房产数组（不包括本身）
     *
     * @return 房产数组
     */
    public abstract Property[] getParents();

    /**
     * 获取房产数组（包括本身）
     *
     * @return 房产数组
     */
    public abstract Property[] getThisAndParents();

    /**
     * 注册
     *
     * @param property 房产
     */
    protected void register(Property property) {
        houseCount += property.getHouseCount();
        houseArea += property.getHouseArea();
    }

    /**
     * 入住
     *
     * @param property 房产
     */
    protected void checkIn(Property property) {
        usedHouseCount += property.getHouseCount();
        usedHouseArea += property.getUsedHouseArea();
    }

    /**
     * 消除
     *
     * @param property 房产
     */
    private void unregister(Property property) {
        houseCount -= property.getHouseCount();
        houseArea -= property.getHouseArea();
        usedHouseCount -= property.getUsedHouseCount();
        usedHouseArea -= property.getUsedHouseArea();
    }

    /**
     * 删除之前
     */
    @PreRemove
    public void preDelete() {
        for (Property property : getParents()) {
            property.unregister(this);
        }
    }

    /**
     * 添加设备
     *
     * @param device 要添加的设备
     */
    public void addDevice(Device device) {
        device.setProperty(this);
        if (device.getNo() == null) {
            device.setNo(unityCode + "#" + deviceList.size());
        }
//        deviceList.add(device);
    }

    //region Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnityCode() {
        return unityCode;
    }

    public void setUnityCode(String unityCode) {
        this.unityCode = unityCode;
    }

    public Integer getChildCount() {
        return childCount;
    }

    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }

    public Set<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(Set<Device> deviceList) {
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
