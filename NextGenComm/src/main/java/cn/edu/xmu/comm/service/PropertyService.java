package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.commons.exception.DifferentCommunityException;
import cn.edu.xmu.comm.commons.persistence.Page;
import cn.edu.xmu.comm.entity.*;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 12/31/2014 0031
 */
public interface PropertyService {
    /**
     * 添加小区
     *
     * @param name 小区名
     * @return 添加的小区
     */
    @Transactional(readOnly = false)
    Community addCommunity(String name);

    /**
     * 添加楼宇
     *
     * @param no         楼宇号
     * @param floorCount 楼层数
     * @param community  所属小区
     * @return 添加的楼宇
     */
    @Transactional(readOnly = false)
    Building addBuilding(Integer no, Integer floorCount, Community community);

    /**
     * 批量添加楼宇
     *
     * @param startNo    起始编号
     * @param length     结束编号
     * @param floorCount 楼层数
     * @param community  所属小区
     * @return 添加的小区列表
     */
    @Transactional(readOnly = false)
    List<Building> addBuilding(Integer startNo, Integer length, Integer floorCount, Community community);

    /**
     * 添加房间
     *
     * @param no    房间号
     * @param area  房间面积
     * @param floor 所属楼层
     * @return 添加的房间
     */
    @Transactional(readOnly = false)
    Room addRoom(String no, Double area, Floor floor);

    /**
     * 批量添加房间（面积必须相同）
     *
     * @param startNo 起始编号
     * @param length  房间数
     * @param area    面积
     * @param floor   所属楼层
     * @return 添加的房间列表
     */
    @Transactional(readOnly = false)
    List<Room> addRoomBatch(Integer startNo, Integer length, Double area, Floor floor);

    /**
     * 添加业主,并指定小区
     *
     * @param username  用户名
     * @param password  密码
     * @param name      姓名
     * @param community 所属小区
     * @return 添加的业主
     */
    @Transactional(readOnly = false)
    Owner addOwner(String username, String password, String name, Community community);

    /**
     * 添加业主，并指定房间
     *
     * @param username 用户名
     * @param password 密码
     * @param name     姓名
     * @param room     房间
     * @return 添加的业主
     * @throws cn.edu.xmu.comm.commons.exception.DifferentCommunityException 小区不同异常
     * @see cn.edu.xmu.comm.commons.exception.DifferentCommunityException
     */
    @Transactional(readOnly = false)
    Owner addOwner(String username, String password, String name, Room room)
            throws DifferentCommunityException;

    /**
     * 将业主添加到房间
     *
     * @param owner 业主
     * @param room  房间
     * @throws cn.edu.xmu.comm.commons.exception.DifferentCommunityException 小区不同异常
     * @see cn.edu.xmu.comm.commons.exception.DifferentCommunityException
     */
    @Transactional(readOnly = false)
    void addOwnerToRoom(Owner owner, Room room) throws DifferentCommunityException;

    /**
     * 添加私有设备
     *
     * @param no       设备号
     * @param property 设备所处位置
     * @param value    设备当前值
     * @param type     设备类型
     * @return 添加的设备
     */
    @Transactional(readOnly = false)
    Device addDevice(String no, Property property, BigDecimal value, Device.DeviceType type);

    /**
     * 添加公摊设备
     *
     * @param no        设备号
     * @param property  设备所处位置
     * @param value     设备当前值
     * @param type      设备类型
     * @param shareType 设备公摊类型
     * @return 添加的设备
     */
    @Transactional(readOnly = false)
    Device addDevice(String no, Property property, BigDecimal value, Device.DeviceType type, String shareType);

    /**
     * 小区及下级各处各添加一个水表一个电表
     *
     * @param community 小区
     * @param shareType 公摊类型
     */
    @Transactional(readOnly = false)
    void initialDefaultDevice(Community community, String shareType);

    /**
     * 楼宇及下级各处各添加一个水表一个电表
     *
     * @param building  楼宇
     * @param shareType 公摊类型
     */
    @Transactional(readOnly = false)
    void initialDefaultDevice(Building building, String shareType);

    /**
     * 楼层及下级各处各添加一个水表一个电表
     *
     * @param floor     楼层
     * @param shareType 公摊类型
     */
    @Transactional(readOnly = false)
    void initialDefaultDevice(Floor floor, String shareType);

    /**
     * 房间添加水表和电表
     *
     * @param room 设备所处位置
     */
    @Transactional(readOnly = false)
    void initialDefaultDevice(Room room);

    /**
     * 更新小区
     *
     * @param community 小区
     */
    @Transactional(readOnly = false)
    void updateCommunity(Community community);

    /**
     * 更新楼宇
     *
     * @param building 楼宇
     */
    @Transactional(readOnly = false)
    void updateBuilding(Building building);

    /**
     * 更新楼层
     *
     * @param floor 楼层
     */
    @Transactional(readOnly = false)
    void updateFloor(Floor floor);

    /**
     * 更新房间
     *
     * @param room 房间
     */
    @Transactional(readOnly = false)
    void updateRoom(Room room);

    /**
     * 更新业主
     *
     * @param owner 业主
     */
    @Transactional(readOnly = false)
    void updateOwner(Owner owner);

    /**
     * 更新设备
     *
     * @param device 设备
     */
    @Transactional(readOnly = false)
    void updateDevice(Device device);

    /**
     * 删除小区
     *
     * @param community 小区id
     */
    @Transactional(readOnly = false)
    void delCommunity(Community community);

    @Transactional(readOnly = false)
    void delCommunity(Integer community);

    /**
     * 删除楼宇
     *
     * @param building 楼宇
     */
    @Transactional(readOnly = false)
    void delBuilding(Building building);

    /**
     * 通过id获得小区
     *
     * @param id 小区id
     * @return 小区
     */
    Community getCommunity(Integer id);

    Building getBuilding(Integer id);

    /**
     * 通过名字获得小区
     *
     * @param name 小区名字
     * @return 小区
     */
    Community getCommunity(String name);

    /**
     * 获取所有小区
     *
     * @return 小区列表
     */
    List<Community> getAllCommunities();

    /**
     * 获取所有小区（分页）
     *
     * @param page 分页对象
     * @return 分页对象
     */
    Page<Community> getAllCommunities(Page<Community> page);

    List<String> getCommunityNames();

    List<Building> getAllBuildings(Community community);

    /**
     * 通过楼宇号获取某小区的楼宇
     *
     * @param no        楼宇号
     * @param community 所属小区
     * @return 楼宇
     */
    Building getBuildingByNo(Integer no, Community community);

    /**
     * 通过楼层号获取某楼宇的楼层
     *
     * @param no       楼层号
     * @param building 所属楼宇
     * @return 楼层
     */
    Floor getFloorByNo(Integer no, Building building);

    /**
     * 通过房间号获取某楼层的房间
     *
     * @param no    房间号
     * @param floor 楼层
     * @return 房间
     */
    Room getRoomByNo(String no, Floor floor);

    /**
     * 获取小区所有房间
     *
     * @param community 小区
     * @return 房间列表
     */
    List<Room> getAllRooms(Community community);

    Owner getOwner(String name);

    /**
     * 获取某小区楼宇号列表
     *
     * @param community 小区
     * @return 楼宇号列表
     */
    List<Integer> getBuildingNos(Community community);

}
