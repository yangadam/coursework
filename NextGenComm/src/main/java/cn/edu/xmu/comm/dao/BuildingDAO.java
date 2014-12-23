package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.entity.Building;
import cn.edu.xmu.comm.entity.Community;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Roger on 2014/12/9 0009.
 */
@Repository
public class BuildingDAO extends BaseDAO<Building, Integer> {

    /**
     * 根据小区查找
     *
     * @param community
     * @return
     */
    public List<Building> searchByCommunity(Community community) {
        return searchByQL("from Building where community = :p1", new Parameter(community));
    }

}
