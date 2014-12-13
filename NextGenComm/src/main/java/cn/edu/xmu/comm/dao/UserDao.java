package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.domain.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Roger on 2014/12/8 0008.
 */
@Repository("userDAO")
public class UserDao extends BaseDAO<User, Integer> {

}

