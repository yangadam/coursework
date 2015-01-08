package cn.edu.xmu.comm.service.impl;

import cn.edu.xmu.comm.commons.annotation.Required;
import cn.edu.xmu.comm.commons.calc.impl.AreaShareCalculator;
import cn.edu.xmu.comm.commons.calc.impl.CountShareCalculator;
import cn.edu.xmu.comm.commons.calc.impl.FloorShareCalculator;
import cn.edu.xmu.comm.commons.exception.DifferentCommunityException;
import cn.edu.xmu.comm.commons.utils.SecurityUtils;
import cn.edu.xmu.comm.commons.utils.SessionUtils;
import cn.edu.xmu.comm.dao.*;
import cn.edu.xmu.comm.dao.impl.DeviceDaoImpl;
import cn.edu.xmu.comm.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 物业管理模块Service实现
 *
 * @author Mengmeng Yang
 * @version 2014-12-22
 */
@Service
@Transactional(readOnly = true)
public class PropertyServiceImpl implements cn.edu.xmu.comm.service.PropertyService {


    //region Building Service

    //region DAO
    @Resource
    private BuildingDAO buildingDAO;
    @Resource
    private CommunityDAO communityDAO;
    @Resource
    private DeviceDaoImpl deviceDAO;
    //endregion

    //region Community Service
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

    //region Device Service

    /**
     * 添加楼宇
     *
     * @param no         楼宇号
     * @param floorCount 楼层数
     * @param community  所属小区
     * @return 添加的楼宇
     */
    @Override
    @Required
    @Transactional(readOnly = false)
    public Building addBuilding(Integer no, Integer floorCount, Community community) {
        community = communityDAO.get(community.getId());
        Building building = new Building(no, floorCount);
        community.addBuilding(building);
        buildingDAO.persist(building);
        return building;
    }

    /**
     * 获取小区内所有楼宇
     *
     * @return 楼宇列表
     */
    @Override
    @Required
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
    @Required
    public List<String[]> getBuildNos() {
        Community community = SessionUtils.getCommunity();
        return buildingDAO.getIdsAndNos(community);
    }

    /**
     * 添加小区
     *
     * @param name 小区名
     * @return 添加的小区
     */
    @Override
    @Required
    @Transactional(readOnly = false)
    public Community addCommunity(String name) {
        Community community = new Community(name);
        PublicFund publicFund = new PublicFund(BigDecimal.valueOf(1000), "", BigDecimal.ZERO, BigDecimal.valueOf(40));
        community.setPublicFund(publicFund);
        communityDAO.persist(community);
        ParkingLot tempParkingLot = newTempParkingLot(community);
        ParkingLot rentParkingLot = newRentParkingLot(community);
        community.getParkingLotList().add(tempParkingLot);
        community.getParkingLotList().add(rentParkingLot);
        communityDAO.merge(community);
        return community;
    }

    /**
     * 删除小区
     *
     * @param commId 小区id
     */
    @Override
    @Required
    @Transactional(readOnly = false)
    public void delCommunity(Integer commId) {
        Community community = communityDAO.get(commId);
        roomDAO.delete(community);
        ownerDAO.delete(community);
        staffDAO.delete(community);
        communityDAO.delete(community);
    }

    /**
     * 更新小区
     *
     * @param community 小区
     */
    @Override
    @Required
    @Transactional(readOnly = false)
    public void updateCommunity(Community community) {
        communityDAO.merge(community);
    }
    //endregion

    //region Floor Service

    /**
     * 获取所有小区
     *
     * @return 小区列表
     */
    @Override
    @Required
    public List<Community> getAllCommunities() {
        return communityDAO.getAll();
    }
    //endregion

    //region Owner Service

    /**
     * 获取所有小区的名字列表
     *
     * @return 小区的名字列表
     */
    @Override
    @Required
    public List<String> getCommunityNames() {
        return communityDAO.getNames();
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
    @Required
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
    @Required
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
    @Required
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
    @Required
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
    @Required
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
    @Required
    @Transactional(readOnly = false)
    public void initialDefaultDevice(Room room) {
        Double zero = 0.0;
        if (room.getDeviceList().isEmpty()) {
            addDevice(room.getUnityCode() + "#1", room, zero, Device.DeviceType.WATER);
            addDevice(room.getUnityCode() + "#2", room, zero, Device.DeviceType.ELECTRICITY);
        }
    }

    /**
     * 获得某楼宇的楼层id和楼层号列表
     *
     * @param buildId 楼宇id
     * @return 楼层id和楼层号列表
     */
    @Override
    @Required
    public List<String[]> getFloorNos(Integer buildId) {
        return floorDAO.getIdsAndNos(buildId);
    }
    //endregion

    //region ParkingLot Service

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
    @Override
    @Required
    @Transactional(readOnly = false)
    public Owner addOwner(String username, String password, String name, String phoneNumber, String email, Community community) {
        Owner owner = new Owner(username, password, name, phoneNumber, email, community);
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
    @Required
    @Transactional(readOnly = false)
    public Owner addOwner(String username, String password, String name, String phoneNumber, String email, Room room)
            throws DifferentCommunityException {
        Owner owner = new Owner(username, password, name, phoneNumber, email, room);
        SecurityUtils.encryptUser(owner);
        ownerDAO.persist(owner);
        roomDAO.merge(room);
        return owner;
    }
    //endregion

    //region Room Service

    /**
     * 将业主添加到房间
     *
     * @param ownerId 业主
     * @param roomId  房间
     * @throws DifferentCommunityException 小区不同异常
     * @see DifferentCommunityException
     */
    @Override
    @Required
    @Transactional(readOnly = false)
    public void addOwnerToRoom(Integer ownerId, Integer roomId) throws DifferentCommunityException {
        Owner owner = ownerDAO.get(ownerId);
        Room room = roomDAO.get(roomId);
        owner.addRoom(room);
        roomDAO.merge(room);
        ownerDAO.merge(owner);
    }

    /**
     * 通过id获得业主
     *
     * @param ownerId 业主id
     * @return 业主
     */
    @Override
    @Required
    public Owner getOwner(Integer ownerId) {
        return ownerDAO.get(ownerId);
    }

    /**
     * 获得所有业主
     *
     * @return 业主列表
     */
    @Override
    @Required
    public List<Owner> getAllOwners() {
        Community community = SessionUtils.getCommunity();
        return ownerDAO.getAll(community);
    }

    /**
     * 模糊搜索业主
     *
     * @param term 关键词
     * @return 业主id、用户名、姓名列表
     */
    @Override
    @Required
    public List<String[]> searchOwner(String term) {
        Community community = SessionUtils.getCommunity();
        return ownerDAO.buzzSearch(term, community);
    }

    /**
     * 重新从数据库加载业主
     *
     * @param user 用户
     * @return 业主
     */
    @Override
    @Required
    public Owner loadOwner(User user) {
        return ownerDAO.get(user.getId());
    }

    /**
     * 判断小区里有无指定业主
     *
     * @param ownerId 业主id
     * @return 如果有，返回true
     */
    @Override
    @Required
    public Boolean hasOwner(Integer ownerId) {
        Community community = SessionUtils.getCommunity();
        Owner owner = ownerDAO.get(ownerId);
        return owner != null && community.getId().equals(owner.getCommunity().getId());
    }
    //endregion

    /**
     * 新建临时停车场
     *
     * @param community 社区
     * @return 临时停车场
     */
    @Override
    @Required
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
    @Required
    @Transactional(readOnly = false)
    public ParkingLot newRentParkingLot(Community community) {
        ParkingLot rentParkingLot = new ParkingLot();
        rentParkingLot.setType(ParkingLot.ParkingLotStatus.RENT);
        rentParkingLot.setCommunity(community);
        rentParkingLot.setName(community.getName() + "租用停车场");
        parkingLotDAO.persist(rentParkingLot);
        return rentParkingLot;
    }

    /**
     * 添加房间
     *
     * @param no      房间号
     * @param area    房间面积
     * @param floorId 所属楼层id
     * @return 添加的房间
     */
    @Override
    @Required
    @Transactional(readOnly = false)
    public Room addRoom(String no, Double area, Integer floorId) {
        Room room = new Room(no, area);
        Floor floor = floorDAO.get(floorId);
        floor.addRoom(room);
        roomDAO.persist(room);
        floorDAO.merge(floor);
        return room;
    }

    /**
     * 通过房间id获得房间
     *
     * @param roomId 房间id
     * @return 房间
     */
    @Override
    @Required
    public Room getRoom(Integer roomId) {
        return roomDAO.get(roomId);
    }

    /**
     * 获得所有房间
     *
     * @param floorId 楼层id
     * @return 房间列表
     */
    @Override
    @Required
    public List<Room> getAllRooms(Integer floorId) {
        Floor floor = floorDAO.get(floorId);
        return floor.getRoomList();
    }

    /**
     * 获得某楼层的房间id和房间号列表
     *
     * @param floorId 楼层id
     * @return 房间id和房间号列表
     */
    @Override
    @Required
    public List<String[]> getRoomNos(Integer floorId) {
        return floorDAO.getIdsAndNos(floorId);
    }

    /**
     * 获得某楼层的空闲房间id和房间号列表
     *
     * @param floorId 楼层id
     * @return 房间id和房间号列表
     */
    @Override
    @Required
    public List<String[]> getVacantRoomNos(Integer floorId) {
        return roomDAO.getVacantRoomNos(floorId);
    }

    /**
     * 获得某楼层的非空房间id和房间号列表
     *
     * @param floorId 楼层id
     * @return 房间id和房间号列表
     */
    @Override
    @Required
    public List<String[]> getNonVacantRoomNos(Integer floorId) {
        return roomDAO.getNonVacantRoomNos(floorId);
    }
    //endregion

}
