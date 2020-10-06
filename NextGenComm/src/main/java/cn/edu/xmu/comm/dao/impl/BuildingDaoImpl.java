package cn.edu.xmu.comm.dao.impl;

import cn.edu.xmu.comm.commons.persistence.BaseDaoImpl;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.dao.BuildingDAO;
import cn.edu.xmu.comm.entity.Building;
import cn.edu.xmu.comm.entity.Community;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 楼宇DAO
 * Created by Roger on 2014/12/9 0009.
 *
 * @author Mengmeng Yang
 * @version 2014-12-22
 */
@Repository
public class BuildingDaoImpl extends BaseDaoImpl<Building, Integer> implements BuildingDAO {

    /**
     * 通过楼宇号获取某小区楼宇
     *
     * @param no        楼宇号
     * @param community 小区
     * @return 楼宇
     */
    @Override
    public Building getByNo(Integer no, Community community) {
        String ql = "select b from Building b where b.community = :p1 and b.no = :p2";
        return getByQL(ql, new Parameter(community, no));
    }

    /**
     * 获得小区的楼宇列表
     *
     * @param community 小区
     * @return 楼宇列表
     */
    @Override
    public List<Building> getAll(Community community) {
        String ql = "select b from Building b where b.community = :p1";
        return searchByQL(ql, new Parameter(community));
    }

    /**
     * 获取楼宇id和楼宇号列表
     *
     * @param community 小区
     * @return 楼宇id和楼宇号列表
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<String[]> getIdsAndNos(Community community) {
        String ql = "select b.id, b.no from Building b where b.community = :p1";
        return getAttrsByQL(ql, new Parameter(community));
    }

}
