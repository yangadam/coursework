package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.entity.Car;
import cn.edu.xmu.comm.entity.Community;

import java.util.List;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/8/2015 0008
 */
public interface CarDAO extends BaseDAO<Car, Integer> {
    /**
     * 获得所有车辆
     *
     * @param community 小区
     * @return 车辆列表
     */
    List<Car> getAll(Community community);

    /**
     * 通过车牌号获取车辆
     *
     * @param license 车牌号
     * @return 车辆
     */
    Car get(String license);
}
