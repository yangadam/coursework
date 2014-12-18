package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.commons.service.BaseService;
import cn.edu.xmu.comm.dao.BuildingDAO;
import cn.edu.xmu.comm.dao.CommunityDAO;
import cn.edu.xmu.comm.dao.FloorDAO;
import cn.edu.xmu.comm.entity.Building;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Floor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
    public void addCommunity(String name) {
        Community community = new Community(name);
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

    /**
     * 获取所有小区
     */
    public List<Community> listCommunities() {
        return communityDAO.getAll();
    }

}
