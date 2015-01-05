package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.entity.Building;
import cn.edu.xmu.comm.entity.Community;

import java.util.List;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/5/2015 0005
 */
public interface BuildingDAO extends BaseDAO<Building, Integer> {
    /**
     * 通过楼宇号获取某小区楼宇
     *
     * @param no        楼宇号
     * @param community 小区
     * @return 楼宇
     */
    Building getByNo(Integer no, Community community);

    /**
     * @param community 小区
     * @return 楼宇
     */
    List<Building> getAll(Community community);

    @SuppressWarnings("unchecked")
    List<String[]> getIdsAndNos(Community community);
}
