package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.commons.annotation.Required;
import cn.edu.xmu.comm.entity.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 车位管理模块接口
 *
 * @author Mengmeng Yang
 * @version 1/8/2015 0008
 */
public interface ParkingService {
    /**
     * 添加租用车位的车辆
     *
     * @param license 车牌
     * @param owner   业主
     * @return 新添加的汽车
     */
    @Required(name = "clerk,guard")
    @Transactional(readOnly = false)
    Car addCar(String license, Owner owner);

    /**
     * 获得所有车辆
     *
     * @return 车辆列表
     */
    @Required(name = "clerk,guard")
    List<Car> getAllCars();

    /**
     * 判断一辆是否为租用车位车辆
     *
     * @param license 车牌
     * @return 该车是否拥有租用车位
     */
    @Required(name = "clerk,guard")
    Boolean isRentCar(String license);

    /**
     * 判断社区Community是否有空车位
     *
     * @return Boolean 是否有空车位 True 有空车位
     */
    @Required(name = "clerk,guard")
    Boolean hasFreeTempParkPlace();

    /**
     * 获取可租用的停车位
     *
     * @return 可租用的停车位列表
     */
    List<ParkingPlace> getFreeParkPlaceRent();

    /**
     * 确认租车
     *
     * @param license     车牌号
     * @param ownerId     业主编号
     * @param parkPlaceId 停车场编号
     */
    @Required(name = "clerk,guard")
    @Transactional(readOnly = false)
    void confirmCarRentParkPlace(String license, Integer ownerId, Integer parkPlaceId);

    /**
     * 添加车位
     *
     * @param parkingLotId 停车场编号
     * @param position     位置
     */
    @Required(name = "clerk,guard")
    @Transactional(readOnly = false)
    void addParkingPlace(Integer parkingLotId, String position);

    /**
     * 新建临时停车单
     *
     * @param ownerId 业主id
     * @param license 车牌
     * @return parkBill 临时停车单
     */
    @Required(name = "clerk,guard")
    @Transactional(readOnly = false)
    ParkingBill addParkBill(Integer ownerId, String license);

    /**
     * 获取该社区中所有已完成的停车单
     *
     * @return 获取该社区中所有已完成的停车单
     */
    @Required(name = "clerk,guard")
    List<ParkingBill> getAllFinishParkBill();

    /**
     * 获取该社区中所有未完成的停车单
     *
     * @return 该社区中所有未完成的停车单
     */
    @Required(name = "clerk,guard")
    List<ParkingBill> getAllUnfinishParkBill();

    /**
     * 依据id获取停车单
     *
     * @param id 编号
     * @return 停车单
     */
    @Required(name = "clerk,guard")
    ParkingBill getParkBill(Integer id);

    /**
     * 完成临时停车单的缴费
     *
     * @param parkBillId 停车单id
     * @return 停车单
     */
    @Transactional(readOnly = false)
    ParkingBill finishParkBill(Integer parkBillId);

    /**
     * 查询租用停车车位
     *
     * @return ParkingLot rentParkingLot
     */
    @Required(name = "clerk,guard")
    ParkingLot getRentParkingLot();

    /**
     * 获取临时停车场
     *
     * @return 临时停车场
     */
    @Required(name = "clerk,guard")
    ParkingLot getTempParkingLot();

    /**
     * 获得所有停车位
     *
     * @return 停车位列表
     */
    @Required(name = "clerk,guard")
    List<ParkingPlace> getAllParkingPlace();

    /**
     * 根据临时停车单生成停车单
     *
     * @param parkingBill 未完成账单
     * @return 停车单
     */
    @Transactional(readOnly = false)
    ParkingBill generateParkBill(ParkingBill parkingBill);
}
