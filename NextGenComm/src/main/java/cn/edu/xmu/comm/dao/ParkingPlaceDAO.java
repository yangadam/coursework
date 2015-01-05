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
 * @version 1/5/2015 0005
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

    List<ParkingPlace> getRentParkPlace(ParkingLot parkingLot, ParkingPlace.ParkPlaceStatus parkPlaceStatus);

    ParkingPlace get(ParkingLot parkingLot, String position);

    List<ParkingPlace> getAll(Community community);
}
