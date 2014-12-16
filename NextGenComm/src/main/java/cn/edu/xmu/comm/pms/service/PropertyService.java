package cn.edu.xmu.comm.pms.service;

import cn.edu.xmu.comm.commons.service.BaseService;
import cn.edu.xmu.comm.pms.dao.BuildingDAO;
import cn.edu.xmu.comm.pms.dao.CommunityDAO;
import cn.edu.xmu.comm.pms.dao.FloorDAO;
import cn.edu.xmu.comm.pms.entity.Building;
import cn.edu.xmu.comm.pms.entity.Community;
import cn.edu.xmu.comm.pms.entity.Floor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Yummy on 12/16/2014 0016.
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class PropertyService extends BaseService {

    @Resource
    private CommunityDAO communityDAO;

    @Resource
    private BuildingDAO buildingDAO;

    @Resource
    private FloorDAO floorDAO;

    /**
     * 添加小区
     *
     * @param community
     */
    @Transactional(readOnly = false)
    public void addCommunity(Community community) {
        communityDAO.saveOrUpdate(community);
    }

    /**
     * 添加楼宇
     *
     * @param building
     */
    @Transactional(readOnly = false)
    public void addBuilding(Building building) {
        buildingDAO.saveOrUpdate(building);
    }

    /**
     * 添加楼层
     *
     * @param floor
     */
    @Transactional(readOnly = false)
    public void addFloor(Floor floor) {
        floorDAO.saveOrUpdate(floor);
    }

    /**
     * 通过名字获得小区
     *
     * @param name
     * @return
     */
    public Community getCommunityByName(String name) {
        return communityDAO.getByName(name);
    }

}
