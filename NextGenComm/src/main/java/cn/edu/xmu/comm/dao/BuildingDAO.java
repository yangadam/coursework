package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.entity.Building;
import cn.edu.xmu.comm.entity.Community;
import org.springframework.stereotype.Repository;

/**
 * 楼宇DAO
 * Created by Roger on 2014/12/9 0009.
 *
 * @author Mengmeng Yang
 * @version 2014-12-22
 */
@Repository
public class BuildingDAO extends BaseDAO<Building, Integer> {

    /**
     * 通过楼宇号获取某小区楼宇
     *
     * @param no        楼宇号
     * @param community 小区
     * @return 楼宇
     */
    public Building getByNo(Integer no, Community community) {
        return getByQL("from Building where community = :p1 and no = :p2", new Parameter(community, no));
    }

}
