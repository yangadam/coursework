package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.ParkingLot;
import cn.edu.xmu.comm.entity.ParkingPlace;

import java.util.List;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/8/2015 0008
 */
public interface ParkingPlaceDAO extends BaseDAO<ParkingPlace, Integer> {
    /**
     * 停车场中是否含有停车位
     *
     * @param parkingLot   停车场
     * @param parkingPlace 停车位
     * @return 是否含有停车位
     */
    boolean hasParkingPlace(ParkingLot parkingLot, ParkingPlace parkingPlace);

    /**
     * 获得租用停车位
     *
     * @param parkingLot 停车场
     * @param status     状态
     * @return 停车位列表
     */
    List<ParkingPlace> getRentParkPlace(ParkingLot parkingLot, ParkingPlace.ParkingPlaceStatus status);

    /**
     * 通过位置获得停车位
     *
     * @param parkingLot 停车场
     * @param position   位置
     * @return 停车位
     */
    ParkingPlace get(ParkingLot parkingLot, String position);

    /**
     * 获得所有停车场
     *
     * @param community 小区
     * @return 停车位列表
     */
    List<ParkingPlace> getAll(Community community);
}
