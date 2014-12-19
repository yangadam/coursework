package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.commons.security.PasswordUtil;
import cn.edu.xmu.comm.dao.UserDAO;
import cn.edu.xmu.comm.entity.User;
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

    @Transactional(readOnly = false)
    public void addUser(User user) {
        System.out.print("sdfsadfasdfasdfasdfsad");
        PasswordUtil.encryptPassword(user);
        userDAO.saveOrUpdate(user);
    }

    public User getUserByUsername(String username) {
        return userDAO.getByUsername(username);
    }

}
