package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Staff;

import java.util.List;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/8/2015 0008
 */
public interface StaffDAO extends BaseDAO<Staff, Integer> {
    /**
     * 获得所有员工
     *
     * @param community 小区
     * @return 员工列表
     */
    List<Staff> getAll(Community community);

    /**
     * 删除员工
     *
     * @param community 小区
     */
    void delete(Community community);
}
