package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.commons.annotation.Required;
import cn.edu.xmu.comm.commons.exception.DifferentCommunityException;
import cn.edu.xmu.comm.entity.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 物业管理模块Service接口
 *
 * @author Mengmeng Yang
 * @version 1/8/2015 0008
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
    @Required
    @Transactional(readOnly = false)
    Building addBuilding(Integer no, Integer floorCount, Community community);

    /**
     * 获取小区内所有楼宇
     *
     * @return 楼宇列表
     */
    @Required
    List<Building> getAllBuildings();

    /**
     * 获得某小区的楼宇id和楼宇号列表
     *
     * @return 楼宇id和楼宇号列表
     */
    @Required
    List<String[]> getBuildNos();

    /**
     * 添加小区
     *
     * @param name 小区名
     * @return 添加的小区
     */
    @Required
    @Transactional(readOnly = false)
    Community addCommunity(String name);

    /**
     * 删除小区
     *
     * @param commId 小区id
     */
    @Required
    @Transactional(readOnly = false)
    void delCommunity(Integer commId);

    /**
     * 更新小区
     *
     * @param community 小区
     */
    @Required
    @Transactional(readOnly = false)
    void updateCommunity(Community community);

    /**
     * 获取所有小区
     *
     * @return 小区列表
     */
    @Required
    List<Community> getAllCommunities();

    /**
     * 获取所有小区的名字列表
     *
     * @return 小区的名字列表
     */
    @Required
    List<String> getCommunityNames();

    /**
     * 添加私有设备
     *
     * @param no       设备号
     * @param property 设备所处位置
     * @param value    设备当前值
     * @param type     设备类型
     * @return 添加的设备
     */
    @Required
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
    @Required
    @Transactional(readOnly = false)
    Device addDevice(String no, Property property, Double value, Device.DeviceType type, String shareType);

    /**
     * 小区及下级各处各添加一个水表一个电表
     *
     * @param community 小区
     * @param shareType 公摊类型
     */
    @Required
    @Transactional(readOnly = false)
    void initialDefaultDevice(Community community, String shareType);

    /**
     * 楼宇及下级各处各添加一个水表一个电表
     *
     * @param building  楼宇
     * @param shareType 公摊类型
     */
    @Required
    @Transactional(readOnly = false)
    void initialDefaultDevice(Building building, String shareType);

    /**
     * 楼层及下级各处各添加一个水表一个电表
     *
     * @param floor     楼层
     * @param shareType 公摊类型
     */
    @Required
    @Transactional(readOnly = false)
    void initialDefaultDevice(Floor floor, String shareType);

    /**
     * 房间添加水表和电表
     *
     * @param room 设备所处位置
     */
    @Required
    @Transactional(readOnly = false)
    void initialDefaultDevice(Room room);

    /**
     * 获得某楼宇的楼层id和楼层号列表
     *
     * @param buildId 楼宇id
     * @return 楼层id和楼层号列表
     */
    @Required
    List<String[]> getFloorNos(Integer buildId);

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
    @Required
    @Transactional(readOnly = false)
    Owner addOwner(String username, String password, String name, String phoneNumber, String email, Community community);

    /**
     * 添加业主，并指定房间
     *
     * @param username    用户名
     * @param password    密码
     * @param name        姓名
     * @param room        房间
     * @param phoneNumber 电话号码
     * @param email       邮箱
     * @return 添加的业主
     * @throws cn.edu.xmu.comm.commons.exception.DifferentCommunityException 小区不同异常
     * @see cn.edu.xmu.comm.commons.exception.DifferentCommunityException
     */
    @Required
    @Transactional(readOnly = false)
    Owner addOwner(String username, String password, String name, String phoneNumber, String email, Room room)
            throws DifferentCommunityException;

    /**
     * 将业主添加到房间
     *
     * @param ownerId 业主
     * @param roomId  房间
     * @throws cn.edu.xmu.comm.commons.exception.DifferentCommunityException 小区不同异常
     * @see cn.edu.xmu.comm.commons.exception.DifferentCommunityException
     */
    @Required
    @Transactional(readOnly = false)
    void addOwnerToRoom(Integer ownerId, Integer roomId) throws DifferentCommunityException;

    /**
     * 通过id获得业主
     *
     * @param ownerId 业主id
     * @return 业主
     */
    @Required
    Owner getOwner(Integer ownerId);

    /**
     * 获得所有业主
     *
     * @return 业主列表
     */
    @Required
    List<Owner> getAllOwners();

    /**
     * 模糊搜索业主
     *
     * @param term 关键词
     * @return 业主id、用户名、姓名列表
     */
    @Required
    List<String[]> searchOwner(String term);

    /**
     * 重新从数据库加载业主
     *
     * @param user 用户
     * @return 业主
     */
    @Required
    Owner loadOwner(User user);

    /**
     * 判断小区里有无指定业主
     *
     * @param ownerId 业主id
     * @return 如果有，返回true
     */
    @Required
    Boolean hasOwner(Integer ownerId);

    /**
     * 新建临时停车场
     *
     * @param community 社区
     * @return 临时停车场
     */
    @Required
    @Transactional(readOnly = false)
    ParkingLot newTempParkingLot(Community community);

    /**
     * 新建租用停车场
     *
     * @param community 社区
     * @return 租用停车场
     */
    @Required
    @Transactional(readOnly = false)
    ParkingLot newRentParkingLot(Community community);

    /**
     * 添加房间
     *
     * @param no      房间号
     * @param area    房间面积
     * @param floorId 所属楼层id
     * @return 添加的房间
     */
    @Required
    @Transactional(readOnly = false)
    Room addRoom(String no, Double area, Integer floorId);

    /**
     * 通过房间id获得房间
     *
     * @param roomId 房间id
     * @return 房间
     */
    @Required
    Room getRoom(Integer roomId);

    /**
     * 获得所有房间
     *
     * @param floorId 楼层id
     * @return 房间列表
     */
    @Required
    List<Room> getAllRooms(Integer floorId);

    /**
     * 获得某楼层的房间id和房间号列表
     *
     * @param floorId 楼层id
     * @return 房间id和房间号列表
     */
    @Required
    List<String[]> getRoomNos(Integer floorId);

    /**
     * 获得某楼层的空闲房间id和房间号列表
     *
     * @param floorId 楼层id
     * @return 房间id和房间号列表
     */
    @Required
    List<String[]> getVacantRoomNos(Integer floorId);

    /**
     * 获得某楼层的非空房间id和房间号列表
     *
     * @param floorId 楼层id
     * @return 房间id和房间号列表
     */
    @Required
    List<String[]> getNonVacantRoomNos(Integer floorId);
}
