package cn.edu.xmu.comm.dao.impl;

import cn.edu.xmu.comm.commons.persistence.BaseDaoImpl;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.dao.CarDAO;
import cn.edu.xmu.comm.entity.Car;
import cn.edu.xmu.comm.entity.Community;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 车辆DAO
 * Created by Roger on 2014/12/9 0009.
 *
 * @author Mengmeng Yang
 * @version 2014-12-9
 */
@Repository
public class CarDaoImpl extends BaseDaoImpl<Car, Integer> implements CarDAO {

    /**
     * @param community
     * @return
     */
    @Override
    public List<Car> getAll(Community community) {
        String ql = "select c from Car c where c.owner.community = :p1";
        return searchByQL(ql, new Parameter(community));
    }

    public Car get(String license) {
        String ql = "select c from Car c where c.license = :p1";
        return getByQL(ql, new Parameter(license));
    }
}
