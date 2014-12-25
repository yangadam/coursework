package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.commons.exception.DifferentCommunityException;
import cn.edu.xmu.comm.commons.persistence.Page;
import cn.edu.xmu.comm.commons.security.SecurityUtil;
import cn.edu.xmu.comm.commons.service.BaseService;
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
     * @param community 所属小区
     * @return 添加的楼宇
     */
    @Transactional(readOnly = false)
    public Building addBuilding(Integer no, Community community) {
        Building building = new Building(no, community);
        buildingDAO.persist(building);
        return building;
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
        Building building = new Building(no, name, community);
        buildingDAO.persist(building);
        return building;
    }

    /**
     * 批量添加楼宇
     *
     * @param startNo   起始编号
     * @param endNo     结束编号
     * @param community 所属小区
     * @return 添加的小区列表
     */
    @Transactional(readOnly = false)
    public List<Building> addBuildingBatch(Integer startNo, Integer endNo, Community community) {
        List<Building> buildings = new ArrayList<Building>();
        for (Integer currentNo = startNo; currentNo <= endNo; currentNo++) {
            Building building = addBuilding(currentNo, community);
            buildings.add(building);
        }
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
     * @param endNo    结束编号
     * @param building 所属楼宇
     * @return 添加的楼层列表
     */
    @Transactional(readOnly = false)
    public List<Floor> addFloorBatch(Integer startNo, Integer endNo, Building building) {
        List<Floor> floors = new ArrayList<Floor>();
        for (Integer currentNo = startNo; currentNo <= endNo; currentNo++) {
            Floor floor = addFloor(currentNo, building);
            floors.add(floor);
        }
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
            Room room = addRoom(String.valueOf(currentNo), area, floor);
            rooms.add(room);
        }
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
        SecurityUtil.encryptUser(owner);
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
     */
    @Transactional(readOnly = false)
    public Owner addOwner(String username, String password, String name, Room room)
            throws DifferentCommunityException {
        Owner owner = new Owner(username, password, name, room);
        SecurityUtil.encryptUser(owner);
        ownerDAO.persist(owner);
        roomDAO.merge(room);
        return owner;
    }

    /**
     * 添加公摊设备
     *
     * @param no        设备号
     * @param property  设备所处位置
     * @param value     设备当前值
     * @param type      设备类型
     * @param shareType 设备公摊类型（非公摊表任意）
     * @return 添加的设备
     */
    @Transactional(readOnly = false)
    public Device addDevice(String no, Property property, BigDecimal value, String type, String shareType) {
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
        addDevice(community.getUnityCode().concat("#1"), community, zero, "水表", shareType);
        addDevice(community.getUnityCode().concat("#2"), community, zero, "电表", shareType);
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
        addDevice(building.getUnityCode().concat("#1"), building, zero, "水表", shareType);
        addDevice(building.getUnityCode().concat("#2"), building, zero, "电表", shareType);
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
        addDevice(floor.getUnityCode().concat("#1"), floor, zero, "水表", shareType);
        addDevice(floor.getUnityCode().concat("#2"), floor, zero, "电表", shareType);
        for (Room room : floor.getRoomList()) {
            initialDefaultDevice(room, shareType);
        }
    }

    /**
     * 房间添加水表和电表
     *
     * @param room      设备所处位置
     * @param shareType 公摊类型
     */
    @Transactional(readOnly = false)
    public void initialDefaultDevice(Room room, String shareType) {
        BigDecimal zero = BigDecimal.ZERO;
        addDevice(room.getUnityCode().concat("#1"), room, zero, "水表", null);
        addDevice(room.getUnityCode().concat("#2"), room, zero, "电表", null);
    }

    /**
     * 将业主添加到房间
     *
     * @param owner 业主
     * @param room  房间
     * @throws DifferentCommunityException
     */
    @Transactional(readOnly = false)
    public void addOwnerToRoom(Owner owner, Room room) throws DifferentCommunityException {
        owner.addRoom(room);
        roomDAO.merge(room);
        ownerDAO.merge(owner);
    }

    /**
     * 通过名字获得小区
     *
     * @param name 小区名字
     * @return 小区
     */

    public Community getCommunityByName(String name) {
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
     * 通过楼层号获取某楼层的房间
     *
     * @param no    房间号
     * @param floor 楼层
     * @return 房间
     */
    public Room getRoomByNo(String no, Floor floor) {
        return roomDAO.getByNo(no, floor);
    }

}
