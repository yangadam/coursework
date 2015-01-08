package cn.edu.xmu.comm.dao.impl;

import cn.edu.xmu.comm.commons.persistence.BaseDaoImpl;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.dao.OwnerDAO;
import cn.edu.xmu.comm.entity.BillItem;
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
public class OwnerDaoImpl extends BaseDaoImpl<Owner, Integer> implements OwnerDAO {


    /**
     * 获得某小区的所有业主
     *
     * @param community 所属小区
     * @return 业主列表
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Integer> getAllId(Community community) {
        String ql = "select o.id from Owner o where o.community = :p1";
        return getAttrsByQL(ql, new Parameter(community));
    }

    /**
     * 依据姓名获得某业主
     *
     * @param name 业主姓名
     * @return 单个业主
     */
    @Override
    public Owner get(String name) {
        return getByQL("select o from Owner o where o.name = :p1", new Parameter(name));
    }

    /**
     * 依据姓名获得某业主
     *
     * @param community 社区
     * @param name      业主姓名
     * @return 业主列表
     */
    @Override
    public List<Owner> getByName(Community community, String name) {
        return searchByQL("select o from Owner o where o.name = :p1 and o.community = :p2", new Parameter(name, community));
    }

    /**
     * 依据电话获取业主
     *
     * @param phoneNumber 电话号码
     * @return 单个业主
     */
    @Override
    public Owner getByPhoneNumber(String phoneNumber) {
        return getByQL("select o from Owner o where o.phoneNumber = :p1", new Parameter(phoneNumber));
    }

    /**
     * 删除小区
     *
     * @param community 小区
     */
    @Override
    public void delete(Community community) {
        String ql = "delete from Owner o where o.community = :p1";
        createQuery(ql, new Parameter(community)).executeUpdate();
    }

    /**
     * 搜索业主
     *
     * @param term      关键字
     * @param community 小区
     * @return 业主
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<String[]> buzzSearch(String term, Community community) {
        String ql = "select o.id, o.name, o.username from Owner o " +
                "where o.community = :p1 and (o.name like :p2 or o.username like :p2)";
        return getAttrsByQL(ql, new Parameter(community, term + '%'));
    }

    /**
     * 获取欠费用户
     *
     * @param community 小区
     * @return 欠费用户列表
     */
    @Override
    public List<Owner> ArrearageOwner(Community community) {
        String ql = "select distinct b.owner from BillItem b where b.status = :p1 and b.owner.community = :p2";
        return searchByQL(ql, new Parameter(BillItem.BillItemStatus.OVERDUE, community));
    }

    /**
     * 通过房间获取业主
     *
     * @param roomId 房间id
     * @return 业主
     */
    @Override
    public Owner getOwnerByRoom(Integer roomId) {
        String ql = "select r.owner from Room r where r.id = :p1";
        return getByQL(ql, new Parameter(roomId));
    }

    /**
     * 获取所有业主
     *
     * @param community 小区
     * @return 业主列表
     */
    @Override
    public List<Owner> getAll(Community community) {
        String ql = "select o from Owner o where o.community = :p1";
        return searchByQL(ql, new Parameter(community));
    }

}
