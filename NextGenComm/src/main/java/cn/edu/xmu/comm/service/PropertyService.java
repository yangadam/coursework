package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.commons.exception.DifferentCommunityException;
import cn.edu.xmu.comm.commons.security.SecurityUtil;
import cn.edu.xmu.comm.commons.service.BaseService;
import cn.edu.xmu.comm.dao.*;
import cn.edu.xmu.comm.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

    /**
     * 添加小区
     *
     * @param name 小区
     */
    @Transactional(readOnly = false)
    public void addCommunity(String name) {
        Community community = new Community(name);
        communityDAO.persist(community);
    }


    /**
     * 添加楼宇
     *
     * @param no        楼宇号
     * @param community 所属小区
     */
    @Transactional(readOnly = false)
    public void addBuilding(Integer no, Community community) {
        Building building = new Building(no, community);
        buildingDAO.persist(building);
    }

    /**
     * 添加楼宇
     *
     * @param no        楼宇号
     * @param name      楼宇名称
     * @param community 所属小区
     */
    @Transactional(readOnly = false)
    public void addBuilding(Integer no, String name, Community community) {
        Building building = new Building(no, name, community);
        buildingDAO.persist(building);
    }

    /**
     * 批量添加楼宇
     *
     * @param startNo   起始编号
     * @param endNo     结束编号
     * @param community 所属小区
     */
    @Transactional(readOnly = false)
    public void addBuildingBatch(Integer startNo, Integer endNo, Community community) {
        for (Integer currentNo = startNo; currentNo <= endNo; currentNo++) {
            addBuilding(currentNo, community);
        }
    }

    /**
     * 添加楼层
     *
     * @param no       楼层
     * @param building 所属楼宇
     */
    @Transactional(readOnly = false)
    public void addFloor(Integer no, Building building) {
        Floor floor = new Floor(no, building);
        floorDAO.persist(floor);
    }

    /**
     * 批量添加楼层
     *
     * @param startNo  起始编号
     * @param endNo    结束编号
     * @param building 所属楼宇
     */
    @Transactional(readOnly = false)
    public void addFloorBatch(Integer startNo, Integer endNo, Building building) {
        for (Integer currentNo = startNo; currentNo <= endNo; currentNo++) {
            addFloor(currentNo, building);
        }
    }

    /**
     * 添加房间
     *
     * @param no    房间号
     * @param area  房间面积
     * @param floor 所属楼层
     */
    @Transactional(readOnly = false)
    public void addRoom(String no, Double area, Floor floor) {
        Room room = new Room(no, area, floor);
        roomDAO.persist(room);
        floorDAO.merge(room.getFloor());
        buildingDAO.merge(room.getBuilding());
        communityDAO.merge(room.getCommunity());
    }

    /**
     * 批量添加房间（面积必须相同）
     *
     * @param startNo 起始编号
     * @param endNo   结束编号
     * @param area    面积
     * @param floor   所属楼层
     */
    @Transactional(readOnly = false)
    public void addRoomBatch(Integer startNo, Integer endNo, Double area, Floor floor) {
        for (Integer currentNo = startNo; currentNo <= endNo; currentNo++) {
            addRoom(String.valueOf(currentNo), area, floor);
        }
    }

    /**
     * 添加业主,并指定小区
     *
     * @param username 用户名
     * @param password 密码
     * @param name     姓名
     */
    @Transactional(readOnly = false)
    public void addOwner(String username, String password, String name, Community community) {
        Owner owner = new Owner(username, password, name, community);
        SecurityUtil.encryptUser(owner);
        ownerDAO.persist(owner);
    }

    /**
     * 添加业主，并指定房间
     *
     * @param username 用户名
     * @param password 密码
     * @param name     姓名
     * @param room     房间
     */
    @Transactional(readOnly = false)
    public void addOwner(String username, String password, String name, Room room)
            throws DifferentCommunityException {
        Owner owner = new Owner(username, password, name, room);
        SecurityUtil.encryptUser(owner);
        ownerDAO.persist(owner);
        roomDAO.merge(room);
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
    public List<Community> listCommunities() {
        return communityDAO.getAll();
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
