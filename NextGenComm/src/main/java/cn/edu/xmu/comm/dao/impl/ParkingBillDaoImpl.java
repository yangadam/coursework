package cn.edu.xmu.comm.dao.impl;

import cn.edu.xmu.comm.commons.persistence.BaseDaoImpl;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.dao.ParkingBillDAO;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.ParkingBill;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Roger on 2014/12/23 0023.
 * @version 2014/12/23 0008
 */
@Repository
public class ParkingBillDaoImpl extends BaseDaoImpl<ParkingBill, Integer> implements ParkingBillDAO {

    /**
     * 查询社区中临时停车的数量
     *
     * @param community 指定社区
     * @return 该社区中未完成的停车单，即临时停车数量
     */
    @Override
    public Integer getSizeOfUnfinishedBill(Community community) {
        String hql = "select p from ParkingBill p where p.community = :p1 and p.endTime is null";
        return count(hql, new Parameter(community)).intValue();
    }

    /**
     * 依据社区和车辆号查找未完成的停车账单
     *
     * @param community 社区
     * @param license   车牌
     * @return 指定的停车单
     */
    @Override
    public ParkingBill getUnfinishedParkBill(Community community, String license) {
        String hql = "select p from ParkingBill p where p.community = :p1 and p.license = :p2 and p.endTime is null";
        return getByQL(hql, new Parameter(community, license));
    }

    /**
     * 判断一辆车是否在指定小区是否有未完成的停车单
     *
     * @param community 社区
     * @param license   车牌
     * @return true 有 false 无
     */
    @Override
    public boolean carHasUnfinishBill(Community community, String license) {
        String hql = "select p from ParkingBill p where p.community = :p1 and p.license = :p2 and p.endTime is null";
        return count(hql, new Parameter(community, license)) > 0;
    }

    /**
     * 获得所有未完成停车单
     *
     * @param community 小区
     * @return 停车单列表
     */
    @Override
    public List<ParkingBill> getAllUnfinishedParkBill(Community community) {
        String ql = "select p from ParkingBill p where p.community = :p1 and p.endTime is null";
        return searchByQL(ql, new Parameter(community));
    }

    /**
     * 获得所有已完成停车单
     *
     * @param community 小区
     * @return 停车单列表
     */
    @Override
    public List<ParkingBill> getAllFinishParkBill(Community community) {
        String ql = "select p from ParkingBill p from where p.community = :p1 and p.endTime is not null";
        return searchByQL(ql, new Parameter(community));
    }

}
