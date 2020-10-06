package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.entity.User;

import java.util.List;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/8/2015 0008
 */
public interface UserDAO extends BaseDAO<User, Integer> {
    /**
     * 通过用户名获得用户
     *
     * @param username 用户名
     * @return 用户
     */
    User getByUsername(String username);

    /**
     * 获取所有用户
     *
     * @param execludeAdmin 是否除去管理员
     * @return 用户列表
     */
    List<User> getAll(boolean execludeAdmin);
}
