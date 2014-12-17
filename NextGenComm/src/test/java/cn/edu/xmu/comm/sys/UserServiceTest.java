package cn.edu.xmu.comm.sys;

import cn.edu.xmu.comm.commons.security.PasswordUtil;
import cn.edu.xmu.comm.entity.User;
import cn.edu.xmu.comm.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yummy on 12/16/2014 0016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
@TransactionConfiguration
@Transactional
public class UserServiceTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private UserService userService;

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("12345678");
        PasswordUtil passwordHelper = new PasswordUtil();
        PasswordUtil.encryptPassword(user);
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(user);
        session.flush();
        session.close();

    }

}
