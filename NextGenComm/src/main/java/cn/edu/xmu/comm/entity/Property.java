package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.persistence.DataEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
@DynamicUpdate
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Property extends DataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    protected String unityCode;
    protected Integer childCount;
    @OneToMany(targetEntity = Device.class, mappedBy = "property",
            cascade = CascadeType.ALL)
    protected Set<Device> deviceList = new HashSet<Device>();

    protected Integer houseCount;
    protected Integer usedHouseCount;

    protected Double houseArea;
    protected Double usedHouseArea;

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

    /**
     * 构造函数
     *
     * @param area 面积
     */
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
     * 获取祖先
     *
     * @return 祖先数组
     */
    public abstract Property[] getParents();

    /**
     * 获取祖先（包括本身）
     *
     * @return 祖先数组
     */
    public abstract Property[] getThisAndParents();

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
    }

    /**
     * 获得房产主键
     *
     * @return 房产主键
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获得统一编码
     *
     * @return 统一编码
     */
    public String getUnityCode() {
        return unityCode;
    }

    public void setUnityCode(String unityCode) {
        this.unityCode = unityCode;
    }

    /**
     * 获得孩子数量
     *
     * @return 孩子数量
     */
    public Integer getChildCount() {
        return childCount;
    }

    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }

    /**
     * 获得设备列表
     *
     * @return 设备列表
     */
    public Set<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(Set<Device> deviceList) {
        this.deviceList = deviceList;
    }

    /**
     * 获得房间总数
     *
     * @return 房间总数
     */
    public Integer getHouseCount() {
        return houseCount;
    }

    public void setHouseCount(Integer houseCount) {
        this.houseCount = houseCount;
    }

    /**
     * 获得已入住房间总数
     *
     * @return 已入住房间总数
     */
    public Integer getUsedHouseCount() {
        return usedHouseCount;
    }

    public void setUsedHouseCount(Integer usedHouseCount) {
        this.usedHouseCount = usedHouseCount;
    }

    /**
     * 获得房产面积
     *
     * @return 房产面积
     */
    public Double getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(Double houseArea) {
        this.houseArea = houseArea;
    }

    /**
     * 获得已使用的房产面积
     *
     * @return 已使用的房产面积
     */
    public Double getUsedHouseArea() {
        return usedHouseArea;
    }

    public void setUsedHouseArea(Double usedHouseArea) {
        this.usedHouseArea = usedHouseArea;
    }

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
}
