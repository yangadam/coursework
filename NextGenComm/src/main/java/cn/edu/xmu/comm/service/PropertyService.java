package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.commons.exception.DifferentCommunityException;
import cn.edu.xmu.comm.entity.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/5/2015 0005
 */
public interface PropertyService {
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
     * 添加小区
     *
     * @param name 小区名
     * @return 添加的小区
     */
    @Transactional(readOnly = false)
    Community addCommunity(String name);

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
    Device addDevice(String no, Property property, Double value, Device.DeviceType type);

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
    Device addDevice(String no, Property property, Double value, Device.DeviceType type, String shareType);

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

    Floor getFloor(Integer floorId);

    /**
     * 添加业主,并指定小区
     *
     * @param username    用户名
     * @param password    密码
     * @param name        姓名
     * @param phoneNumber 电话号码
     * @param email       邮箱
     * @param community   所属小区
     * @return 添加的业主
     */
    @Transactional(readOnly = false)
    Owner addOwner(String username, String password, String name, String phoneNumber, String email, Community community);

    /**
     * 添加业主，并指定房间
     *
     * @param username 用户名
     * @param password 密码
     * @param name     姓名
     * @param room     房间
     * @return 添加的业主
     * @throws DifferentCommunityException 小区不同异常
     * @see DifferentCommunityException
     */
    @Transactional(readOnly = false)
    public Owner addOwner(String username, String password, String name, String phoneNumber, String email, Room room)
            throws DifferentCommunityException;

    /**
     * 将业主添加到房间
     *
     * @param ownerId 业主
     * @param roomId  房间
     * @throws cn.edu.xmu.comm.commons.exception.DifferentCommunityException 小区不同异常
     * @see cn.edu.xmu.comm.commons.exception.DifferentCommunityException
     */
    @Transactional(readOnly = false)
    void addOwnerToRoom(Integer ownerId, Integer roomId) throws DifferentCommunityException;

    Boolean hasOwner(Integer ownerId);

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

    List<Room> getAllRoom();

    /**
     * 新建临时停车场
     *
     * @param community 社区
     * @return 临时停车场
     */
    @Transactional(readOnly = false)
    ParkingLot newTempParkingLot(Community community);

    /**
     * 新建租用停车场
     *
     * @param community 社区
     * @return 租用停车场
     */
    @Transactional(readOnly = false)
    ParkingLot newRentParkingLot(Community community);

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
    void delCommunity(Integer id);

    /**
     * 删除楼宇
     *
     * @param building 楼宇
     */
    @Transactional(readOnly = false)
    void delBuilding(Building building);

    /**
     * 获取所有小区
     *
     * @return 小区列表
     */
    List<Community> getAllCommunities();

    /**
     * 获取所有小区的名字列表
     *
     * @return 小区的名字列表
     */
    List<String> getCommunityNames();

    List<Building> getAllBuildings();

    /**
     * 获得某小区的楼宇id和楼宇号列表
     *
     * @return 楼宇id和楼宇号列表
     */
    List<String[]> getBuildNos();

    /**
     * 获得某楼宇的楼层id和楼层号列表
     *
     * @param buildId 楼宇id
     * @return 楼层id和楼层号列表
     */
    List<String[]> getFloorNos(Integer buildId);

    /**
     * 获得某楼宇的房间id和房间号列表
     *
     * @param floorId 楼层id
     * @return 房间id和房间号列表
     */
    List<String[]> getRoomNos(Integer floorId);

    List<Room> getAllRooms(Integer floorId);

    List<String[]> getVacantRoomNos(Integer floorId);

    List<String[]> searchOwner(String term);

    List<String[]> getNonVacantRoomNos(Integer floorId);

    @Transactional(readOnly = false)
    void updateDeviceLastKey(Device device, Date date);

    Owner loadOwner(User user);

    Room getRoom(Integer roomId);

    List<Owner> getAllOwners();

    Community getCommunity(String commName);

    Owner getOwner(Integer ownerId);
}
