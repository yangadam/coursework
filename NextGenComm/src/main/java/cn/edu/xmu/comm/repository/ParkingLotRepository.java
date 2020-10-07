package cn.edu.xmu.comm.repository;

import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, Integer> {

    /**
     * 依据社区查询临时停车车位
     *
     * @param community 社区
     * @return ParkingLot tempParkingLot
     */
    @Query("from ParkingLot where community = :community and type = 'TEMP'")
    ParkingLot getTempParkingLot(Community community);

    /**
     * 依据社区查询租用车位
     *
     * @param community 社区
     * @return ParkingLot rentParkingLot
     */
    @Query("from ParkingLot where community = :community and type = 'RENT'")
    ParkingLot getRentParkingLot(Community community);

    /**
     * 依据社区查询临时租用车位
     *
     * @param community        社区
     * @param parkingLotStatus 停车场状态
     * @return ParkingLot ParkingLot
     */
    @Query("from ParkingLot where community = :community and type = :parkingLotStatus")
    ParkingLot getParkingLot(Community community, ParkingLot.ParkingLotStatus parkingLotStatus);
}
