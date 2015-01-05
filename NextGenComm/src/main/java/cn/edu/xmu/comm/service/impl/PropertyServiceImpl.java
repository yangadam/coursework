package cn.edu.xmu.comm.service.impl;

import cn.edu.xmu.comm.commons.calc.impl.AreaShareCalculator;
import cn.edu.xmu.comm.commons.calc.impl.CountShareCalculator;
import cn.edu.xmu.comm.commons.calc.impl.FloorShareCalculator;
import cn.edu.xmu.comm.commons.exception.DifferentCommunityException;
import cn.edu.xmu.comm.commons.utils.SecurityUtils;
import cn.edu.xmu.comm.commons.utils.SessionUtils;
import cn.edu.xmu.comm.dao.*;
import cn.edu.xmu.comm.dao.impl.DeviceDaoImpl;
import cn.edu.xmu.comm.entity.*;
import cn.edu.xmu.comm.service.PropertyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

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
    private BuildingDAO buildingDAO;
    @Resource
    private CommunityDAO communityDAO;
    @Resource
    private DeviceDaoImpl deviceDAO;
    @Resource
    private FloorDAO floorDAO;
    @Resource
    private OwnerDAO ownerDAO;
    @Resource
    private ParkingLotDAO parkingLotDAO;
    @Resource
    private StaffDAO staffDAO;
    @Resource
    private RoomDAO roomDAO;
    //endregion

    //region Building Service

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
        community = communityDAO.get(community.getId());
        Building building = new Building(no, floorCount);
        community.addBuilding(building);
        buildingDAO.persist(building);
        return building;
    }
    //endregion

    //region Community Service

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
        ParkingLot tempParkingLot = newTempParkingLot(community);
        ParkingLot rentParkingLot = newRentParkingLot(community);
        community.getParkingLotList().add(tempParkingLot);
        community.getParkingLotList().add(rentParkingLot);
        communityDAO.merge(community);
        return community;
    }
    //endregion

    //region Device Service

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
    public Device addDevice(String no, Property property, Double value, Device.DeviceType type) {
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
    public Device addDevice(String no, Property property, Double value, Device.DeviceType type, String shareType) {
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
        community = communityDAO.get(community.getId());
        Double zero = 0.0;
        if (community.getDeviceList().isEmpty()) {
            addDevice(community.getUnityCode() + "#1", community, zero, Device.DeviceType.WATER, CountShareCalculator.class.getSimpleName());
            addDevice(community.getUnityCode() + "#2", community, zero, Device.DeviceType.ELECTRICITY, CountShareCalculator.class.getSimpleName());
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
        Double zero = 0.0;
        if (building.getDeviceList().isEmpty()) {
            addDevice(building.getUnityCode() + "#1", building, zero, Device.DeviceType.WATER, AreaShareCalculator.class.getSimpleName());
            addDevice(building.getUnityCode() + "#2", building, zero, Device.DeviceType.ELECTRICITY, AreaShareCalculator.class.getSimpleName());
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
        Double zero = 0.0;
        if (floor.getDeviceList().isEmpty()) {
            addDevice(floor.getUnityCode() + "#1", floor, zero, Device.DeviceType.WATER, FloorShareCalculator.class.getSimpleName());
            addDevice(floor.getUnityCode() + "#2", floor, zero, Device.DeviceType.ELECTRICITY, FloorShareCalculator.class.getSimpleName());
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
        Double zero = 0.0;
        if (room.getDeviceList().isEmpty()) {
            addDevice(room.getUnityCode() + "#1", room, zero, Device.DeviceType.WATER);
            addDevice(room.getUnityCode() + "#2", room, zero, Device.DeviceType.ELECTRICITY);
        }
    }
    //endregion

    @Override
    public Floor getFloor(Integer floorId) {
        return floorDAO.get(floorId);
    }

    //region Owner Service

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

    @Override
    public Boolean hasOwner(Integer ownerId) {
        Community community = SessionUtils.getCommunity();
        Owner owner = ownerDAO.get(ownerId);
        return owner != null && community.getId().equals(owner.getCommunity().getId());
    }
    //endregion

    //region Room Service

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
    //endregion

    /**
     * 新建临时停车场
     *
     * @param community 社区
     * @return 临时停车场
     */
    @Override
    @Transactional(readOnly = false)
    public ParkingLot newTempParkingLot(Community community) {
        ParkingLot tempParkingLot = new ParkingLot();
        tempParkingLot.setType(ParkingLot.ParkingLotStatus.TEMP);
        tempParkingLot.setFeeType("GradientParkingCalculator");
        tempParkingLot.setCommunity(community);
        tempParkingLot.getGradient().put(30, BigDecimal.valueOf(5));
        tempParkingLot.getGradient().put(90, BigDecimal.valueOf(10));
        tempParkingLot.getGradient().put(150, BigDecimal.valueOf(15));
        tempParkingLot.getGradient().put(210, BigDecimal.valueOf(20));
        tempParkingLot.setName(community.getName() + "临时停车场");
        parkingLotDAO.persist(tempParkingLot);
        return tempParkingLot;
    }

    /**
     * 新建租用停车场
     *
     * @param community 社区
     * @return 租用停车场
     */
    @Override
    @Transactional(readOnly = false)
    public ParkingLot newRentParkingLot(Community community) {
        ParkingLot rentParkingLot = new ParkingLot();
        rentParkingLot.setType(ParkingLot.ParkingLotStatus.RENT);
        rentParkingLot.setCommunity(community);
        rentParkingLot.setName(community.getName() + "租用停车场");
        parkingLotDAO.persist(rentParkingLot);
        return rentParkingLot;
    }

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
    public void delCommunity(Integer id) {
        Community community = communityDAO.get(id);
        roomDAO.delete(community);
        ownerDAO.delete(community);
        staffDAO.delete(community);
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

    @Override
    public List<Community> getAllCommunities() {
        return communityDAO.getAll();
    }
    //endregion

    //region Get Operations

    /**
     * 获取所有小区的名字列表
     *
     * @return 小区的名字列表
     */
    @Override
    public List<String> getCommunityNames() {
        return communityDAO.getNames();
    }

    @Override
    public List<Building> getAllBuildings() {
        Community community = SessionUtils.getCommunity();
        return buildingDAO.getAll(community);
    }


    /**
     * 获得某小区的楼宇id和楼宇号列表
     *
     * @return 楼宇id和楼宇号列表
     */
    @Override
    public List<String[]> getBuildNos() {
        Community community = SessionUtils.getCommunity();
        return buildingDAO.getIdsAndNos(community);
    }

    /**
     * 获得某楼宇的楼层id和楼层号列表
     *
     * @param buildId 楼宇id
     * @return 楼层id和楼层号列表
     */
    @Override
    public List<String[]> getFloorNos(Integer buildId) {
        return floorDAO.getIdsAndNos(buildId);
    }

    /**
     * 获得某楼宇的房间id和房间号列表
     *
     * @param floorId 楼层id
     * @return 房间id和房间号列表
     */
    @Override
    public List<String[]> getRoomNos(Integer floorId) {
        return floorDAO.getIdsAndNos(floorId);
    }


    @Override
    public List<Room> getAllRooms(Integer floorId) {
        Floor floor = floorDAO.get(floorId);
        return floor.getRoomList();
    }


    @Override
    public List<String[]> getVacantRoomNos(Integer floorId) {
        return roomDAO.getVacantRoomNos(floorId);
    }

    @Override
    public List<String[]> searchOwner(String term) {
        Community community = SessionUtils.getCommunity();
        return ownerDAO.buzzSearch(term, community);
    }

    @Override
    public List<String[]> getNonVacantRoomNos(Integer floorId) {
        return roomDAO.getNonVacantRoomNos(floorId);
    }


    @Override
    @Transactional(readOnly = false)
    public void updateDeviceLastKey(Device device, Date date) {
        SortedMap<Date, Double> sortedMap = device.getValues();
        Date end = sortedMap.lastKey();
        sortedMap.remove(end);
        sortedMap.put(date, 0.0);
        device.setValues(sortedMap);
        deviceDAO.merge(device);
    }

    @Override
    public Owner loadOwner(User user) {
        return ownerDAO.get(user.getId());
    }

    @Override
    public Room getRoom(Integer roomId) {
        return roomDAO.get(roomId);
    }

    @Override
    public List<Owner> getAllOwners() {
        Community community = SessionUtils.getCommunity();
        return ownerDAO.getAll(community);
    }

    @Override
    public Community getCommunity(String commName) {
        return communityDAO.getByName(commName);
    }


    //endregion

    //region Unused
//    /**
//     * 通过楼宇号获取某小区的楼宇
//     *
//     * @param no        楼宇号
//     * @param community 所属小区
//     * @return 楼宇
//     */
//    public Building getBuildingByNo(Integer no, Community community) {
//        return buildingDAO.getByNo(no, community);
//    }
//
//    /**
//     * 通过楼层号获取某楼宇的楼层
//     *
//     * @param no       楼层号
//     * @param building 所属楼宇
//     * @return 楼层
//     */
//    public Floor getFloorByNo(Integer no, Building building) {
//        return floorDAO.getByNo(no, building);
//    }
//
//    /**
//     * 通过房间号获取某楼层的房间
//     *
//     * @param no    房间号
//     * @param floor 楼层
//     * @return 房间
//     */
//    public Room getRoomByNo(String no, Floor floor) {
//        return roomDAO.getByNo(no, floor);
//    }
//    public Owner getOwner(String name) {
//        return ownerDAO.get(name);
//    }
    //    public Room getRoom(Integer roomId) {
//        return roomDAO.get(roomId);
//    }


//    public List<Owner> getAllOwners(Community community) {
//        return ownerDAO.getAll(community);
//    }
//
//    /**
//     * 获取小区所有房间
//     *
//     * @param community 小区
//     * @return 房间列表
//     */
//    public List<Room> getAllRooms(Community community) {
//        return roomDAO.getAll(community);
//    }
//    /**
//     * 获取所有小区（分页）
//     *
//     * @param page 分页对象
//     * @return 分页对象
//     */
//    public Page<Community> getAllCommunities(Page<Community> page) {
//        return communityDAO.getAll(page);
//    }

    //    /**
//     * 批量添加楼宇
//     *
//     * @param startNo    起始编号
//     * @param length     结束编号
//     * @param floorCount 楼层数
//     * @param community  所属小区
//     * @return 添加的小区列表
//     */
//    @Transactional(readOnly = false)
//    public List<Building> addBuilding(Integer startNo, Integer length, Integer floorCount, Community community) {
//        List<Building> buildings = new ArrayList<Building>();
//        for (Integer currentNo = startNo; currentNo < startNo + length; currentNo++) {
//            buildings.add(new Building(currentNo, floorCount));
//        }
//        community.addBuildings(buildings);
//        buildingDAO.persist(buildings);
//        return buildings;
//    }

//    /**
//     * 批量添加房间（面积必须相同）
//     *
//     * @param startNo 起始编号
//     * @param length  房间数
//     * @param area    面积
//     * @param floor   所属楼层
//     * @return 添加的房间列表
//     */
//    @Transactional(readOnly = false)
//    public List<Room> addRoomBatch(Integer startNo, Integer length, Double area, Floor floor) {
//        List<Room> rooms = new ArrayList<Room>();
//        for (Integer currentNo = startNo; currentNo <= startNo + length; currentNo++) {
//            rooms.add(new Room(String.valueOf(currentNo), area));
//        }
//        floor.addRooms(rooms);
//        roomDAO.persist(rooms);
//        floorDAO.merge(floor);
//        return rooms;
//    }

//    /**
//     * 通过id获得小区
//     *
//     * @param id 小区id
//     * @return 小区
//     */
//    public Community getCommunity(Integer id) {
//        return communityDAO.load(id);
//    }
//
//    public Building getBuilding(Integer id) {
//        return buildingDAO.load(id);
//    }
//
//    /**
//     * 通过名字获得小区
//     *
//     * @param name 小区名字
//     * @return 小区
//     */
//    public Community getCommunity(String name) {
//        return communityDAO.getByName(name);
//    }
    //endregion

}
