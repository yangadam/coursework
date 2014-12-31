package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.entity.Building;
import cn.edu.xmu.comm.entity.Community;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
        String ql = "select b from Building b where b.community = :p1 and b.no = :p2";
        return getByQL(ql, new Parameter(community, no));
    }

    /**
     * @param community
     * @return
     */
    public List<Building> getAll(Community community) {
        String ql = "select b from Building b where b.community = :p1";
        return searchByQL(ql, new Parameter(community));
    }

    @SuppressWarnings("unchecked")
    public List<Integer[]> getIdsAndNos(Community community) {
        String ql = "select b.id, b.no from Building b where b.community = :p1";
        List list = getAttrsByQL(ql, new Parameter(community));
        List<Integer[]> ret = new ArrayList<Integer[]>();
        for (Object aList : list) {
            Object[] objects = (Object[]) aList;
            Integer[] ints = new Integer[objects.length];
            for (int i = 0; i < objects.length; i++) {
                ints[i] = Integer.valueOf(objects[i].toString());
            }
            ret.add(ints);
        }
        return ret;
    }
}
