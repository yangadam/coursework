package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.entity.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/5/2015 0005
 */
@Transactional(readOnly = true)
public interface ParkingService {
    /**
     * 添加租用车位的车辆
     *
     * @param license 车牌
     * @param owner   业主
     * @return 新添加的汽车
     */
    @Transactional(readOnly = false)
    Car addCar(String license, Owner owner);

    List<Car> getAllCars();

    List<ParkingPlace> getFreeParkPlaceRent();

    @Transactional(readOnly = false)
    void confirmCarRentParkPlace(String license, Integer ownerId, Integer parkPlaceId);

    @Transactional(readOnly = false)
    void addParkingPlace(Integer parkingLotId, String position);

    @Transactional(readOnly = false)
    ParkingBill addParkBill(Integer ownerId, String license);

    List<ParkingBill> getAllFinishParkBill();

    List<ParkingBill> getAllUnfinishParkBill();

    ParkingBill getParkBill(Integer id);

    ParkingLot getRentParkingLot();

    ParkingLot getTempParkingLot();

    List<ParkingPlace> getAllParkingPlace();

    Boolean isRentCar(String license);

    Boolean hasFreeTempParkPlace();

    ParkingBill finishParkBill(Integer parkBillId);

}
