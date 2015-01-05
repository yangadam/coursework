package cn.edu.xmu.comm.dao.impl;

import cn.edu.xmu.comm.commons.persistence.BaseDaoImpl;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.dao.ParkingPlaceDAO;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.ParkingLot;
import cn.edu.xmu.comm.entity.ParkingPlace;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 停车位DAO
 * Created by Roger on 2014/12/9 0009.
 *
 * @author Mengmeng Yang
 * @version 2014-12-9
 */
@Repository
public class ParkingPlaceDaoImpl extends BaseDaoImpl<ParkingPlace, Integer> implements ParkingPlaceDAO {

    /**
     * 停车场中是否含有停车位
     *
     * @param parkingLot   停车场
     * @param parkingPlace 停车位
     * @return 是否含有停车位
     */
    @Override
    public boolean hasParkingPlace(ParkingLot parkingLot, ParkingPlace parkingPlace) {
        String ql = "select p from ParkingPlace p where p.parkingLot = :p1 and id = :p2";
        long count = count(ql, new Parameter(parkingLot, parkingPlace.getId()));
        return count != 0;
    }

    @Override
    public List<ParkingPlace> getRentParkPlace(ParkingLot parkingLot, ParkingPlace.ParkPlaceStatus parkPlaceStatus) {
        String ql = "from ParkingPlace where parkingLot = :p1 and parkPlaceStatus = :p2";
        return searchByQL(ql, new Parameter(parkingLot, parkPlaceStatus));
    }

    @Override
    public ParkingPlace get(ParkingLot parkingLot, String position) {
        return getByQL("from ParkingPlace where parkingLot = :p1 and position = :p2", new Parameter(parkingLot, position));
    }

    @Override
    public List<ParkingPlace> getAll(Community community) {
        String ql = "select p from ParkingPlace p where p.parkingLot.community = :p1";
        return searchByQL(ql, new Parameter(community));
    }
}
