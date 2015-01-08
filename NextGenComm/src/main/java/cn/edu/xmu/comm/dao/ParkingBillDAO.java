package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.ParkingBill;

import java.util.List;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/8/2015 0008
 */
public interface ParkingBillDAO extends BaseDAO<ParkingBill, Integer> {
    /**
     * 查询社区中临时停车的数量
     *
     * @param community 指定社区
     * @return 该社区中未完成的停车单，即临时停车数量
     */
    Integer getSizeOfUnfinishedBill(Community community);

    /**
     * 依据社区和车辆号查找未完成的停车账单
     *
     * @param community 社区
     * @param license   车牌
     * @return 指定的停车单
     */
    ParkingBill getUnfinishedParkBill(Community community, String license);

    /**
     * 判断一辆车是否在指定小区是否有未完成的停车单
     *
     * @param community 社区
     * @param license   车牌
     * @return true 有 false 无
     */
    boolean carHasUnfinishBill(Community community, String license);

    /**
     * 获得所有未完成停车单
     *
     * @param community 小区
     * @return 停车单列表
     */
    List<ParkingBill> getAllUnfinishedParkBill(Community community);

    /**
     * 获得所有已完成停车单
     *
     * @param community 小区
     * @return 停车单列表
     */
    List<ParkingBill> getAllFinishParkBill(Community community);
}
