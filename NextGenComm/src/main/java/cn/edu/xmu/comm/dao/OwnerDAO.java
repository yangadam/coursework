package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.commons.utils.CastUtils;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Owner;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 业主DAO
 * Created by Roger on 2014/12/8 0009.
 *
 * @author Mengmeng Yang
 * @version 2014-12-8
 */
@Repository
public class OwnerDAO extends BaseDAO<Owner, Integer> {

    /**
     * 获得某小区的所有业主
     *
     * @param community 所属小区
     * @return 业主列表
     */
    public List<Owner> getAll(Community community) {
        String ql = "select o from Owner o where o.community = :p1";
        return searchByQL(ql, new Parameter(community));
    }

    /**
     * 依据姓名获得某业主
     *
     * @param name 业主姓名
     * @return 单个业主
     */
    public Owner get(String name) {
        return getByQL("from Owner where name = :p1", new Parameter(name));
    }

    /**
     * 依据姓名获得某业主
     *
     * @param community 社区
     * @param name      业主姓名
     * @return 业主列表
     */
    public List<Owner> getByName(Community community, String name) {
        return searchByQL("from Owner where name = :p1 and community = :p2", new Parameter(name, community));
    }

    /**
     * 依据电话获取业主
     *
     * @param phoneNumber 电话号码
     * @return 单个业主
     */
    public Owner getByPhoneNumber(String phoneNumber) {
        return getByQL("from Owner where phoneNumber = :p1", new Parameter(phoneNumber));
    }

    public void delete(Community community) {
        String ql = "delete from Owner where community = :p1";
        createQuery(ql, new Parameter(community)).executeUpdate();
    }

    public List<String[]> buzzSearch(String term, Community community) {
        String ql = "select o.id, o.name, o.username from Owner o " +
                "where o.community = :p1 and (o.name like :p2 or o.username like :p2)";
        List list = getAttrsByQL(ql, new Parameter(community, term + '%'));
        return CastUtils.castToListStringArray(list);
    }
}
