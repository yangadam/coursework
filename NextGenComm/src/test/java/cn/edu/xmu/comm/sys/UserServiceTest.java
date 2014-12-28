package cn.edu.xmu.comm.sys;

import cn.edu.xmu.comm.commons.security.SecurityUtil;
import cn.edu.xmu.comm.entity.Owner;
import cn.edu.xmu.comm.entity.User;
import cn.edu.xmu.comm.service.SystemService;
import junit.framework.TestCase;
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
public class UserServiceTest extends TestCase {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private SystemService systemService;


    @Test
    public void testAddUser() {
        User user = new User("admin", "123", "管理员");
        SecurityUtil.encryptUser(user);
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(user);
        session.flush();
        session.close();

    }

}
