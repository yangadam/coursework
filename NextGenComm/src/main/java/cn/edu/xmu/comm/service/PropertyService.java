package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.commons.exception.DifferentCommunityException;
import cn.edu.xmu.comm.commons.persistence.Page;
import cn.edu.xmu.comm.commons.service.BaseService;
import cn.edu.xmu.comm.commons.utils.SecurityUtils;
import cn.edu.xmu.comm.commons.utils.StringUtils;
import cn.edu.xmu.comm.dao.*;
import cn.edu.xmu.comm.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 物业管理模块Service
 *
 * @author Mengmeng Yang
 * @version 2014-12-22
 */
@Service
@Transactional(readOnly = true)
public class PropertyService extends BaseService {

    //region DAO
    @Resource
    private CommunityDAO communityDAO;

    @Resource
    private BuildingDAO buildingDAO;

    @Resource
    private FloorDAO floorDAO;

    @Resource
    private RoomDAO roomDAO;

    @Resource
    private OwnerDAO ownerDAO;

    @Resource
    private DeviceDAO deviceDAO;
    //endregion

    //region Add Operations

    /**
     * 添加小区
     *
     * @param name 小区
     * @return 添加的小区
     */
    @Transactional(readOnly = false)
    public Community addCommunity(String name) {
        Community community = new Community(name);
        communityDAO.persist(community);
        return community;
    }

    /**
     * 添加楼宇
     *
     * @param no        楼宇号
     * @param name      楼宇名称
     * @param community 所属小区
     * @return 添加的楼宇
     */
    @Transactional(readOnly = false)
    public Building addBuilding(Integer no, String name, Community community) {
        Building building;
        if (name == null || StringUtils.isBlank(name)) {
            building = new Building(no, community);
        } else {
            building = new Building(no, name, community);
        }
        buildingDAO.persist(building);
        return building;
    }

    /**
     * 批量添加楼宇
     *
     * @param startNo   起始编号
     * @param length    结束编号
     * @param community 所属小区
     * @return 添加的小区列表
     */
    @Transactional(readOnly = false)
    public List<Building> addBuilding(Integer startNo, Integer length, Community community) {
        List<Building> buildings = new ArrayList<Building>();
        for (Integer currentNo = startNo; currentNo < startNo + length; currentNo++) {
            buildings.add(new Building(currentNo, null, community));
        }
        buildingDAO.persist(buildings);
        return buildings;
    }

    /**
     * 添加楼层
     *
     * @param no       楼层
     * @param building 所属楼宇
     * @return 添加的楼层
     */
    @Transactional(readOnly = false)
    public Floor addFloor(Integer no, Building building) {
        Floor floor = new Floor(no, building);
        floorDAO.persist(floor);
        return floor;
    }

    /**
     * 批量添加楼层
     *
     * @param startNo  起始编号
     * @param length   楼层数
     * @param building 所属楼宇
     * @return 添加的楼层列表
     */
    @Transactional(readOnly = false)
    public List<Floor> addFloorBatch(Integer startNo, Integer length, Building building) {
        List<Floor> floors = new ArrayList<Floor>();
        for (Integer currentNo = startNo; currentNo < startNo + length; currentNo++) {
            floors.add(new Floor(currentNo, building));
        }
        floorDAO.persist(floors);
        return floors;
    }

    /**
     * 添加房间
     *
     * @param no    房间号
     * @param area  房间面积
     * @param floor 所属楼层
     * @return 添加的房间
     */
    @Transactional(readOnly = false)
    public Room addRoom(String no, Double area, Floor floor) {
        Room room = new Room(no, area, floor);
        roomDAO.persist(room);
        floorDAO.merge(floor);
        return room;
    }

    /**
     * 批量添加房间（面积必须相同）
     *
     * @param startNo 起始编号
     * @param endNo   结束编号
     * @param area    面积
     * @param floor   所属楼层
     * @return 添加的房间列表
     */
    @Transactional(readOnly = false)
    public List<Room> addRoomBatch(Integer startNo, Integer endNo, Double area, Floor floor) {
        List<Room> rooms = new ArrayList<Room>();
        for (Integer currentNo = startNo; currentNo <= endNo; currentNo++) {
            rooms.add(new Room(String.valueOf(currentNo), area, floor));
        }
        roomDAO.persist(rooms);
        floorDAO.merge(floor);
        return rooms;
    }

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
    public Owner addOwner(String username, String password, String name, Community community) {
        Owner owner = new Owner(username, password, name, community);
        SecurityUtils.encryptUser(owner);
        ownerDAO.persist(owner);
        return owner;
    }

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
    public Owner addOwner(String username, String password, String name, Room room)
            throws DifferentCommunityException {
        Owner owner = new Owner(username, password, name, room);
        SecurityUtils.encryptUser(owner);
        ownerDAO.persist(owner);
        roomDAO.merge(room);
        return owner;
    }

    /**
     * 将业主添加到房间
     *
     * @param owner 业主
     * @param room  房间
     * @throws DifferentCommunityException 小区不同异常
     * @see DifferentCommunityException
     */
    @Transactional(readOnly = false)
    public void addOwnerToRoom(Owner owner, Room room) throws DifferentCommunityException {
        owner.addRoom(room);
        roomDAO.merge(room);
        ownerDAO.merge(owner);
    }

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
    public Device addDevice(String no, Property property, BigDecimal value, Device.DeviceType type) {
        Device device = new Device(no, property, value, type);
        deviceDAO.persist(device);
        return device;
    }

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
    public Device addDevice(String no, Property property, BigDecimal value, Device.DeviceType type, String shareType) {
        Device device = new Device(no, property, value, type, shareType);
        deviceDAO.persist(device);
        return device;
    }

    /**
     * 小区及下级各处各添加一个水表一个电表
     *
     * @param community 小区
     * @param shareType 公摊类型
     */
    @Transactional(readOnly = false)
    public void initialDefaultDevice(Community community, String shareType) {
        BigDecimal zero = BigDecimal.ZERO;
        if (community.getDeviceList().isEmpty()) {
            addDevice(community.getUnityCode().concat("#1"), community, zero, Device.DeviceType.WATER, shareType);
            addDevice(community.getUnityCode().concat("#2"), community, zero, Device.DeviceType.ELECTRICITY, shareType);
        }
        for (Building building : community.getBuildingList()) {
            initialDefaultDevice(building, shareType);
        }
    }

    /**
     * 楼宇及下级各处各添加一个水表一个电表
     *
     * @param building  楼宇
     * @param shareType 公摊类型
     */
    @Transactional(readOnly = false)
    public void initialDefaultDevice(Building building, String shareType) {
        BigDecimal zero = BigDecimal.ZERO;
        if (building.getDeviceList().isEmpty()) {
            addDevice(building.getUnityCode().concat("#1"), building, zero, Device.DeviceType.WATER, shareType);
            addDevice(building.getUnityCode().concat("#2"), building, zero, Device.DeviceType.ELECTRICITY, shareType);
        }
        for (Floor floor : building.getFloorList()) {
            initialDefaultDevice(floor, shareType);
        }
    }

    /**
     * 楼层及下级各处各添加一个水表一个电表
     *
     * @param floor     楼层
     * @param shareType 公摊类型
     */
    @Transactional(readOnly = false)
    public void initialDefaultDevice(Floor floor, String shareType) {
        BigDecimal zero = BigDecimal.ZERO;
        if (floor.getDeviceList().isEmpty()) {
            addDevice(floor.getUnityCode().concat("#1"), floor, zero, Device.DeviceType.WATER, shareType);
            addDevice(floor.getUnityCode().concat("#2"), floor, zero, Device.DeviceType.ELECTRICITY, shareType);
        }
        for (Room room : floor.getRoomList()) {
            initialDefaultDevice(room);
        }
    }

    /**
     * 房间添加水表和电表
     *
     * @param room 设备所处位置
     */
    @Transactional(readOnly = false)
    public void initialDefaultDevice(Room room) {
        BigDecimal zero = BigDecimal.ZERO;
        if (room.getDeviceList().isEmpty()) {
            addDevice(room.getUnityCode().concat("#1"), room, zero, Device.DeviceType.WATER);
            addDevice(room.getUnityCode().concat("#2"), room, zero, Device.DeviceType.ELECTRICITY);
        }
    }
    //endregion

    //region Update Operations

    /**
     * 更新小区
     *
     * @param community 小区
     */
    @Transactional(readOnly = false)
    public void updateCommunity(Community community) {
        communityDAO.merge(community);
    }

    /**
     * 更新楼宇
     *
     * @param building 楼宇
     */
    @Transactional(readOnly = false)
    public void updateBuilding(Building building) {
        buildingDAO.merge(building);
    }

    /**
     * 更新楼层
     *
     * @param floor 楼层
     */
    @Transactional(readOnly = false)
    public void updateFloor(Floor floor) {
        floorDAO.merge(floor);
    }

    /**
     * 更新房间
     *
     * @param room 房间
     */
    @Transactional(readOnly = false)
    public void updateRoom(Room room) {
        roomDAO.merge(room);
    }

    /**
     * 更新业主
     *
     * @param owner 业主
     */
    @Transactional(readOnly = false)
    public void updateOwner(Owner owner) {
        ownerDAO.merge(owner);
    }

    /**
     * 更新设备
     *
     * @param device 设备
     */
    @Transactional(readOnly = false)
    public void updateDevice(Device device) {
        deviceDAO.merge(device);
    }
    //endregion

    //region Delete Operations

    /**
     * 删除小区
     *
     * @param community 小区id
     */
    @Transactional(readOnly = false)
    public void delCommunity(Community community) {
        community.preDelete();
        communityDAO.delete(community);
    }

    /**
     * 删除楼宇
     *
     * @param building 楼宇
     */
    @Transactional(readOnly = false)
    public void delBuilding(Building building) {
        building.preDelete();
        buildingDAO.delete(building);
    }
    //endregion

    //region Get Operations

    /**
     * 通过id获得小区
     *
     * @param id 小区id
     * @return 小区
     */
    public Community getCommunity(Integer id) {
        return communityDAO.get(id);
    }

    /**
     * 通过名字获得小区
     *
     * @param name 小区名字
     * @return 小区
     */
    public Community getCommunity(String name) {
        return communityDAO.getByName(name);
    }

    /**
     * 获取所有小区
     *
     * @return 小区列表
     */
    public List<Community> getAllCommunities() {
        return communityDAO.getAll();
    }

    /**
     * 获取所有小区（分页）
     *
     * @param page 分页对象
     * @return 分页对象
     */
    public Page<Community> getAllCommunities(Page<Community> page) {
        return communityDAO.getAll(page);
    }

    /**
     * 通过楼宇号获取某小区的楼宇
     *
     * @param no        楼宇号
     * @param community 所属小区
     * @return 楼宇
     */
    public Building getBuildingByNo(Integer no, Community community) {
        return buildingDAO.getByNo(no, community);
    }

    /**
     * 通过楼层号获取某楼宇的楼层
     *
     * @param no       楼层号
     * @param building 所属楼宇
     * @return 楼层
     */
    public Floor getFloorByNo(Integer no, Building building) {
        return floorDAO.getByNo(no, building);
    }

    /**
     * 通过房间号获取某楼层的房间
     *
     * @param no    房间号
     * @param floor 楼层
     * @return 房间
     */
    public Room getRoomByNo(String no, Floor floor) {
        return roomDAO.getByNo(no, floor);
    }

    /**
     * 获取小区所有房间
     *
     * @param community 小区
     * @return 房间列表
     */
    public List<Room> getAllRooms(Community community) {
        return roomDAO.getAll(community);
    }

    public Owner getOwner(String name) {
        return ownerDAO.get(name);
    }
    //endregion

}
