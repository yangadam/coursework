package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.ParkingLot;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/8/2015 0008
 */
public interface ParkingLotDAO extends BaseDAO<ParkingLot, Integer> {
    /**
     * 依据社区查询临时停车车位
     *
     * @param community 社区
     * @return ParkingLot tempParkingLot
     */
    ParkingLot getTempParkingLot(Community community);

    /**
     * 依据社区查询租用车位
     *
     * @param community 社区
     * @return ParkingLot rentParkingLot
     */
    ParkingLot getRentParkingLot(Community community);

    /**
     * 依据社区查询临时租用车位
     *
     * @param community        社区
     * @param parkingLotStatus 停车场状态
     * @return ParkingLot ParkingLot
     */
    ParkingLot getParkingLot(Community community, ParkingLot.ParkingLotStatus parkingLotStatus);
}
