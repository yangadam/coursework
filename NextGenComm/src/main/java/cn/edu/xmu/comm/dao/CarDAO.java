package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.commons.persistence.Parameter;
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
public class CarDAO extends BaseDAO<Car, String> {

    /**
     * @param community
     * @return
     */
    public List<Car> getAll(Community community) {
        String ql = "select c from Car c where c.owner.community = :p1";
        return searchByQL(ql, new Parameter(community));
    }
}
