package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.entity.User;
import org.springframework.stereotype.Repository;

/**
 * 用户DAO
 *
 * @author Mengmeng Yang
 * @version 12/22/2014
 */
@Repository
public class UserDAO extends BaseDAO<User, Integer> {

    /**
     * 通过用户名获得用户
     *
     * @param username 用户名
     * @return 用户
     */
    public User getByUsername(String username) {
        return getByQL("from User where username = :p1", new Parameter(username));
    }

}

