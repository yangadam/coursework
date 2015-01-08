package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Owner;

import java.util.List;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/8/2015 0008
 */
public interface OwnerDAO extends BaseDAO<Owner, Integer> {
    /**
     * 获得某小区的所有业主
     *
     * @param community 所属小区
     * @return 业主列表
     */
    @SuppressWarnings("unchecked")
    List<Integer> getAllId(Community community);

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

    /**
     * 删除小区
     *
     * @param community 小区
     */
    void delete(Community community);

    /**
     * 搜索业主
     *
     * @param term      关键字
     * @param community 小区
     * @return 业主
     */
    @SuppressWarnings("unchecked")
    List<String[]> buzzSearch(String term, Community community);

    /**
     * 获取欠费用户
     *
     * @param community 小区
     * @return 欠费用户列表
     */
    List<Owner> ArrearageOwner(Community community);

    /**
     * 通过房间获取业主
     *
     * @param roomId 房间id
     * @return 业主
     */
    Owner getOwnerByRoom(Integer roomId);

    /**
     * 获取所有业主
     *
     * @param community 小区
     * @return 业主列表
     */
    List<Owner> getAll(Community community);
}
