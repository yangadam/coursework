package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Staff;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 员工DAO
 * Created by Yiu-Wah WONG on 2015/1/1.
 *
 * @author Mengmeng Yang
 * @version 1/1/2015
 */
@Repository
public class StaffDAO extends BaseDAO<Staff, Integer> {

    public List<Staff> getAll(Community community) {
        String ql = "select s from Staff s where s.community = :p1";
        return searchByQL(ql, new Parameter(community));
    }

}
