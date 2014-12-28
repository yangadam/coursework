package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.ParkPlace;
import cn.edu.xmu.comm.entity.ParkingLot;
import org.springframework.stereotype.Repository;

/**
 * Created by Roger on 2014/12/23 0023.
 */
@Repository
public class ParkingLotDAO extends BaseDAO<ParkingLot, Integer> {

    /**
     * 依据社区查询临时停车车位
     *
     * @param community 社区
     * @return ParkingLot tempParkingLot
     */
    public ParkingLot getTempParkingLot(Community community) {
        return getByQL("from ParkingLot where community = :p1 and type = :p2", new Parameter(community, ParkingLot.TEMP));
    }

    /**
     * 依据社区查询临时租用车位
     *
     * @param community 社区
     * @return ParkingLot rentParkingLot
     */
    public ParkingLot getRentParkingLot(Community community) {
        return getByQL("from ParkingLot where community = :p1 and type = :p2", new Parameter(community, ParkingLot.RENT));
    }

}
