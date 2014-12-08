package cn.edu.xmu.comm.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by Yummy on 11/29/2014 0029.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@TransactionConfiguration
@Transactional
public class TestHql {

    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void testList() {
//        SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();
        Session session = sessionFactory.openSession();
//        Owner owner = new Owner();
//        owner.setName("Youjie");
//        owner.setUsername("roger");
//        owner.setPassword("1243");
//        session.saveOrUpdate(owner);
        //noinspection JpaQlInspection
//        String hql = "from EnergyRecord";
//        Query query = session.createQuery(hql);
//        List<EnergyRecord> list = (List<EnergyRecord>) query.list();
//        show(list);
//        session.close();
    }

//
//    private void show(List<EnergyRecord> list) {
//        for (EnergyRecord energyRecord : list) {
//            System.out.println(energyRecord);
//        }
//    }

}
