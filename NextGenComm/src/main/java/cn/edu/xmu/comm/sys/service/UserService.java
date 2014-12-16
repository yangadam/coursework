package cn.edu.xmu.comm.sys.service;

import cn.edu.xmu.comm.sys.dao.UserDAO;
import cn.edu.xmu.comm.sys.entity.User;
import cn.edu.xmu.comm.sys.security.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yummy on 2014/12/14.
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserDAO userDAO;

    private PasswordUtil passwordHelper = new PasswordUtil();

    public void addUser(User user) {
        passwordHelper.encryptPassword(user);
        userDAO.saveOrUpdate(user);
    }

    public User getUserByUsername(String username) {
        return userDAO.getByUsername(username);
    }

}
