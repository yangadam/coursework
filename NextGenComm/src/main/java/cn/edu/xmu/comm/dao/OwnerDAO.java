package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Owner;

import java.util.List;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/5/2015 0005
 */
public interface OwnerDAO extends BaseDAO<Owner, Integer> {
    /**
     * 获得某小区的所有业主
     *
     * @param community 所属小区
     * @return 业主列表
     */
    List<Owner> getAll(Community community);

    /**
     * 依据姓名获得某业主
     *
     * @param name 业主姓名
     * @return 单个业主
     */
    Owner get(String name);

    /**
     * 依据姓名获得某业主
     *
     * @param community 社区
     * @param name      业主姓名
     * @return 业主列表
     */
    List<Owner> getByName(Community community, String name);

    /**
     * 依据电话获取业主
     *
     * @param phoneNumber 电话号码
     * @return 单个业主
     */
    Owner getByPhoneNumber(String phoneNumber);

    void delete(Community community);

    List<String[]> buzzSearch(String term, Community community);

    List<Owner> ArrearageOwner(Community community);

    Owner getOwnerByRoom(Integer roomId);
}
