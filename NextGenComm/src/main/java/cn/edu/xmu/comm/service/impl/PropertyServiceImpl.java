package cn.edu.xmu.comm.service.impl;

import cn.edu.xmu.comm.commons.exception.DifferentCommunityException;
import cn.edu.xmu.comm.commons.persistence.Page;
import cn.edu.xmu.comm.commons.utils.SecurityUtils;
import cn.edu.xmu.comm.dao.*;
import cn.edu.xmu.comm.entity.*;
import cn.edu.xmu.comm.service.PropertyService;
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
public class PropertyServiceImpl implements PropertyService {

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
     * @param name 小区名
     * @return 添加的小区
     */
    @Override
    @Transactional(readOnly = false)
    public Community addCommunity(String name) {
        Community community = new Community(name);
        communityDAO.persist(community);
        return community;
    }

    /**
     * 添加楼宇
     *
     * @param no         楼宇号
     * @param floorCount 楼层数
     * @param community  所属小区
     * @return 添加的楼宇
     */
    @Override
    @Transactional(readOnly = false)
    public Building addBuilding(Integer no, Integer floorCount, Community community) {
        communityDAO.refresh(community);
        Building building = new Building(no, floorCount);
        community.addBuilding(building);
        buildingDAO.persist(building);
        return building;
    }

    /**
     * 批量添加楼宇
     *
     * @param startNo    起始编号
     * @param length     结束编号
     * @param floorCount 楼层数
     * @param community  所属小区
     * @return 添加的小区列表
     */
    @Override
    @Transactional(readOnly = false)
    public List<Building> addBuilding(Integer startNo, Integer length, Integer floorCount, Community community) {
        List<Building> buildings = new ArrayList<Building>();
        for (Integer currentNo = startNo; currentNo < startNo + length; currentNo++) {
            buildings.add(new Building(currentNo, floorCount));
        }
        community.addBuildings(buildings);
        buildingDAO.persist(buildings);
        return buildings;
    }

    /**
     * 添加房间
     *
     * @param no    房间号
     * @param area  房间面积
     * @param floor 所属楼层
     * @return 添加的房间
     */
    @Override
    @Transactional(readOnly = false)
    public Room addRoom(String no, Double area, Floor floor) {
        Room room = new Room(no, area);
        floor.addRoom(room);
        roomDAO.persist(room);
        floorDAO.merge(floor);
        return room;
    }

    /**
     * 批量添加房间（面积必须相同）
     *
     * @param startNo 起始编号
     * @param length  房间数
     * @param area    面积
     * @param floor   所属楼层
     * @return 添加的房间列表
     */
    @Override
    @Transactional(readOnly = false)
    public List<Room> addRoomBatch(Integer startNo, Integer length, Double area, Floor floor) {
        List<Room> rooms = new ArrayList<Room>();
        for (Integer currentNo = startNo; currentNo <= startNo + length; currentNo++) {
            rooms.add(new Room(String.valueOf(currentNo), area));
        }
        floor.addRooms(rooms);
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
    @Override
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
    @Override
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
     * @param ownerId 业主
     * @param roomId  房间
     * @throws DifferentCommunityException 小区不同异常
     * @see DifferentCommunityException
     */
    @Override
    @Transactional(readOnly = false)
    public void addOwnerToRoom(Integer ownerId, Integer roomId) throws DifferentCommunityException {
        Owner owner = ownerDAO.get(ownerId);
        Room room = roomDAO.get(roomId);
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
    @Override
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
    @Override
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
    @Override
    @Transactional(readOnly = false)
    public void initialDefaultDevice(Community community, String shareType) {
        BigDecimal zero = BigDecimal.ZERO;
        if (community.getDeviceList().isEmpty()) {
            addDevice(null, community, zero, Device.DeviceType.WATER, shareType);
            addDevice(null, community, zero, Device.DeviceType.ELECTRICITY, shareType);
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
    @Override
    @Transactional(readOnly = false)
    public void initialDefaultDevice(Building building, String shareType) {
        BigDecimal zero = BigDecimal.ZERO;
        if (building.getDeviceList().isEmpty()) {
            addDevice(null, building, zero, Device.DeviceType.WATER, shareType);
            addDevice(null, building, zero, Device.DeviceType.ELECTRICITY, shareType);
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
    @Override
    @Transactional(readOnly = false)
    public void initialDefaultDevice(Floor floor, String shareType) {
        BigDecimal zero = BigDecimal.ZERO;
        if (floor.getDeviceList().isEmpty()) {
            addDevice(null, floor, zero, Device.DeviceType.WATER, shareType);
            addDevice(null, floor, zero, Device.DeviceType.ELECTRICITY, shareType);
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
    @Override
    @Transactional(readOnly = false)
    public void initialDefaultDevice(Room room) {
        BigDecimal zero = BigDecimal.ZERO;
        if (room.getDeviceList().isEmpty()) {
            addDevice(null, room, zero, Device.DeviceType.WATER);
            addDevice(null, room, zero, Device.DeviceType.ELECTRICITY);
        }
    }
    //endregion

    //region Update Operations

    /**
     * 更新小区
     *
     * @param community 小区
     */
    @Override
    @Transactional(readOnly = false)
    public void updateCommunity(Community community) {
        communityDAO.merge(community);
    }

    /**
     * 更新楼宇
     *
     * @param building 楼宇
     */
    @Override
    @Transactional(readOnly = false)
    public void updateBuilding(Building building) {
        buildingDAO.merge(building);
    }

    /**
     * 更新楼层
     *
     * @param floor 楼层
     */
    @Override
    @Transactional(readOnly = false)
    public void updateFloor(Floor floor) {
        floorDAO.merge(floor);
    }

    /**
     * 更新房间
     *
     * @param room 房间
     */
    @Override
    @Transactional(readOnly = false)
    public void updateRoom(Room room) {
        roomDAO.merge(room);
    }

    /**
     * 更新业主
     *
     * @param owner 业主
     */
    @Override
    @Transactional(readOnly = false)
    public void updateOwner(Owner owner) {
        ownerDAO.merge(owner);
    }

    /**
     * 更新设备
     *
     * @param device 设备
     */
    @Override
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
    @Override
    @Transactional(readOnly = false)
    public void delCommunity(Community community) {
        community.preDelete();
        communityDAO.delete(community);
    }

    @Override
    @Transactional(readOnly = false)
    public void delCommunity(Integer community) {
        communityDAO.delete(community);
    }

    /**
     * 删除楼宇
     *
     * @param building 楼宇
     */
    @Override
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
    @Override
    public Community getCommunity(Integer id) {
        return communityDAO.load(id);
    }

    @Override
    public Building getBuilding(Integer id) {
        return buildingDAO.load(id);
    }

    /**
     * 通过名字获得小区
     *
     * @param name 小区名字
     * @return 小区
     */
    @Override
    public Community getCommunity(String name) {
        return communityDAO.getByName(name);
    }

    /**
     * 获取所有小区
     *
     * @return 小区列表
     */
    @Override
    public List<Community> getAllCommunities() {
        return communityDAO.getAll();
    }

    /**
     * 获取所有小区（分页）
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @Override
    public Page<Community> getAllCommunities(Page<Community> page) {
        return communityDAO.getAll(page);
    }

    @Override
    public List<String> getCommunityNames() {
        return communityDAO.getNames();
    }

    @Override
    public List<Building> getAllBuildings(Community community) {
        return buildingDAO.getAll(community);
    }

    /**
     * 通过楼宇号获取某小区的楼宇
     *
     * @param no        楼宇号
     * @param community 所属小区
     * @return 楼宇
     */
    @Override
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
    @Override
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
    @Override
    public Room getRoomByNo(String no, Floor floor) {
        return roomDAO.getByNo(no, floor);
    }

    /**
     * 获取小区所有房间
     *
     * @param community 小区
     * @return 房间列表
     */
    @Override
    public List<Room> getAllRooms(Community community) {
        return roomDAO.getAll(community);
    }

    @Override
    public Owner getOwner(String name) {
        return ownerDAO.get(name);
    }

    @Override
    public List<String[]> getBuildingNos(Community community) {
        return buildingDAO.getIdsAndNos(community);
    }

    @Override
    public List<String[]> getFloorNos(Integer buildId) {
        return floorDAO.getIdsAndNos(buildId);
    }

    @Override
    public List<Room> getAllRooms(Integer floorId) {
        Floor floor = floorDAO.get(floorId);
        return floor.getRoomList();
    }

    @Override
    public Floor getFloor(Integer floorId) {
        return floorDAO.get(floorId);
    }

    @Override
    public List<String[]> getVacantRoomNos(Integer floorId) {
        return roomDAO.getVacantRoomNos(floorId);
    }

    @Override
    public Room getRoom(Integer roomId) {
        return roomDAO.get(roomId);
    }

    @Override
    public List<Owner> getAllOwners(Community community) {
        return ownerDAO.getAll(community);
    }

    @Override
    public List<String[]> searchOwner(String term, Community community) {
        return ownerDAO.buzzSearch(term, community);
    }
    //endregion

}
