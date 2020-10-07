package cn.edu.xmu.comm.repository;

import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.ParkingLot;
import cn.edu.xmu.comm.entity.ParkingPlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingPlaceRepository extends JpaRepository<ParkingPlace, Integer> {
    /**
     * 停车场中是否含有停车位
     *
     * @param parkingLot   停车场
     * @param id 停车位
     * @return 是否含有停车位
     */
    boolean existsByParkingLotAndId(ParkingLot parkingLot, Integer id);

    /**
     * 获得租用停车位
     *
     * @param parkingLot 停车场
     * @param status     状态
     * @return 停车位列表
     */
    List<ParkingPlace> findByParkingLotAndStatus(ParkingLot parkingLot, ParkingPlace.ParkingPlaceStatus status);

    /**
     * 获得所有停车场
     *
     * @param community 小区
     * @return 停车位列表
     */
    List<ParkingPlace> findByParkingLotCommunity(Community community);
}
