package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDaoImpl;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户DAO
 *
 * @author Mengmeng Yang
 * @version 12/22/2014
 */
@Repository
public class UserDAO extends BaseDaoImpl<User, Integer> {

    /**
     * 通过用户名获得用户
     *
     * @param username 用户名
     * @return 用户
     */
    public User getByUsername(String username) {
        String ql = "select u from User u where u.username = :p1";
        return getByQL(ql, new Parameter(username));
    }

    /**
     * 获取所有用户
     *
     * @param execludeAdmin 是否除去管理员
     * @return 用户列表
     */
    public List<User> getAll(boolean execludeAdmin) {
        String ql = "select u from User u where u.username != 'admin'";
        return searchByQL(ql, null);
    }

}

