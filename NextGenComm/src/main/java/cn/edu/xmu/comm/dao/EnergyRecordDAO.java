package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.domain.EnergyRecord;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yummy on 11/29/2014 0029.
 */
@Repository("energyRecordDAO")
public class EnergyRecordDAO {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return getSessionFactory().getCurrentSession();
    }

    public void addEnergyRecord(EnergyRecord energyRecord) {
        getCurrentSession().saveOrUpdate(energyRecord);
    }

    public List<EnergyRecord> getAllEnergyRecord() {
        //noinspection JpaQlInspection
        return getCurrentSession().createQuery("from EnergyRecord").list();
    }

}
