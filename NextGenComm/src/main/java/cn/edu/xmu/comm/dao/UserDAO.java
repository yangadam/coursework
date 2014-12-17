package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Roger on 2014/12/8 0008.
 */
@Repository
public class UserDAO extends BaseDAO<User, Integer> {

    /**
     * 通过用户名获得用户
     *
     * @param username
     * @return
     */
    public User getByUsername(String username) {
        return getByHql("from User where username = :p1", new Parameter(username));
    }

}

