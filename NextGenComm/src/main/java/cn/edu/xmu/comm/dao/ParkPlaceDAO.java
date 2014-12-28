package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.entity.ParkPlace;
import cn.edu.xmu.comm.entity.ParkingLot;
import org.springframework.stereotype.Repository;

/**
 * 停车位DAO
 * Created by Roger on 2014/12/9 0009.
 *
 * @author Mengmeng Yang
 * @version 2014-12-9
 */
@Repository
public class ParkPlaceDAO extends BaseDAO<ParkPlace, Integer> {
    /**
     * 停车场中是否含有停车位
     *
     * @param parkingLot 停车场
     * @param parkPlace  停车位
     * @return 是否含有停车位
     */
    public boolean hasParkPlace(ParkingLot parkingLot, ParkPlace parkPlace) {
        return count("from ParkPlace where parkingLot = :p1 and id = :p2", new Parameter(parkingLot, parkPlace.getId())) != 0;
    }
}
