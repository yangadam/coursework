package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.entity.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 12/31/2014 0031
 */
public interface CarService {
    /**
     * 获取登陆的User所在的社区
     *
     * @return 所在社区
     */
    Community getCommunityFromActionContext();

    /**
     * 添加车辆
     *
     * @param car 车辆
     */
    @Transactional(readOnly = false)
    void addCar(Car car);

    /**
     * 添加车辆
     *
     * @param license   车牌
     * @param owner     业主
     * @param status    状态: 租用车位 购买车位
     * @param parkPlace 停车位
     * @return 新添加的汽车
     */
    @Transactional(readOnly = false)
    Car addCar(String license, Owner owner, Car.CarStatus status, ParkPlace parkPlace);

    /**
     * 添加停车位
     *
     * @param parkPlace 车位
     */
    @Transactional(readOnly = false)
    void addParkPlace(ParkPlace parkPlace);

    /**
     * 添加停车账单
     *
     * @param parkBill 停车账单
     */
    @Transactional(readOnly = false)
    void addParkBill(ParkBill parkBill);

    /**
     * 添加停车场
     *
     * @param parkingLot 停车场
     */
    @Transactional(readOnly = false)
    void addParkingLot(ParkingLot parkingLot);

    /**
     * 依据社区查询临时停车车位
     *
     * @param community 社区
     * @return ParkingLot tempParkingLot
     */
    ParkingLot getTempParkingLot(Community community);

    /**
     * 依据社区查询租用停车车位
     *
     * @param community 社区
     * @return ParkingLot rentParkingLot
     */
    ParkingLot getRentParkingLot(Community community);

    /**
     * 获取当前session中的租用停车场
     *
     * @return 当前session中的租用停车场
     */
    ParkingLot getRentParkingLotInSession();

    /**
     * 获取社区临时停车场的大小
     *
     * @param community 社区
     * @return Integer 社区临时停车场的大小
     */
    int getCommunityTempParkingLotSize(Community community);

    /**
     * 查询社区中临时停车的数量
     *
     * @param community 社区
     * @return Integer 社区中临时停车的数量
     */
    int getSizeOfUnpaidParkingBIll(Community community);

    /**
     * 判断社区Community是否有空车位
     *
     * @param community 社区
     * @return Boolean 是否有空车位 True 有空车位
     */
    Boolean hasFreeTempParkPlace(Community community);

    /**
     * 判断一辆车是否在指定小区是否有未完成的停车单
     *
     * @param community 社区
     * @param license   车牌
     * @return true 有 false 无
     */
    boolean carHasUnfinishBill(Community community, String license);

    /**
     * 新建临时停车单
     *
     * @param community 社区
     * @param owner     业主
     * @param license   车牌
     * @return parkBill 临时停车单
     */
    @Transactional(readOnly = false)
    ParkBill addParkBill(Community community, Owner owner, String license);

    /**
     * 根据车牌来获得未完成的停车单(没有离开时间)
     * 社区信息由parkBillDao在session中获得
     *
     * @param community 社区
     * @param license   车牌号
     * @return ParkBill
     */
    ParkBill getParkBillByLicense(Community community, String license);

    /**
     * 根据临时停车单生成停车单
     *
     * @param parkBill 未完成账单
     */
    @Transactional(readOnly = false)
    ParkBill generateParkBill(ParkBill parkBill);

    /**
     * 完成临时停车单
     *
     * @param community 社区
     * @param license   车牌号
     * @return parkBill 完成的临时停车单
     */
    ParkBill finishParkBill(Community community, String license);

    /**
     * 根据Id获得车辆
     *
     * @param license 编号
     * @return Car 车辆
     */
    Car getCarByLicense(String license);

    /**
     * 判断一辆是否为租用车位车辆
     *
     * @param community 社区
     * @param license   车牌
     * @return 该车是否拥有租用车位
     */
    Boolean isRentCar(Community community, String license);

    /**
     * 判断一辆车是否在已登陆用户所在小区与租用车位
     *
     * @param license 车牌
     * @return 该车是否拥有租用车位
     */
    Boolean isRentCar(String license);

    /**
     * 依据社区和姓名查找业主
     *
     * @param community 社区
     * @param name      姓名
     * @return 业主
     */
    List<Owner> getOwnerByName(Community community, String name);

    /**
     * 依据停车位状态获取在租用停车场中各个状态的停车位列表
     *
     * @param parkingLot 停车场
     * @param status     状态 ：FREE:没有车位、LOCK:锁定的车位、RENT:租用的车位
     * @return 停车位列表
     */
    List<ParkPlace> getParkPlaceRent(ParkingLot parkingLot, ParkPlace.ParkPlaceStatus status);

    /**
     * 获取可租用的停车位
     *
     * @return 可租用的停车位列表
     */
    List<ParkPlace> getFreeParkPlaceRent();

    /**
     * 获取已租用的停车位
     *
     * @return 获取已租用的停车位
     */
    List<ParkPlace> getRentParkPlaceRent();

    /**
     * 获取已锁定的停车位 已打印合同
     *
     * @return 获取已锁定的停车位
     */
    List<ParkPlace> getParkPlaceRent();

    /**
     * 添加车辆锁住停车位
     *
     * @param license   车牌
     * @param owner     业主
     * @param parkPlace 停车位
     * @return 新增的车辆
     */
    @Transactional(readOnly = false)
    Car addCarLockParkPlace(String license, Owner owner, ParkPlace parkPlace);

    /**
     * 确认汽车租用停车位
     *
     * @param car
     */
    void confirmCarRentParkPlace(Car car);

    /**
     * 依据车牌确认汽车租用停车位
     *
     * @param license 车牌号
     */
    @Transactional(readOnly = false)
    void confirmCarRentParkPlace(String license);

    /**
     * 删除汽车释放停车位
     *
     * @param car 车辆
     */
    @Transactional(readOnly = false)
    void removeCarFreeParkPlace(Car car);

    /**
     * 依据车牌删除汽车释放停车位
     *
     * @param license 车牌号
     */
    @Transactional(readOnly = false)
    void removeCarFreeParkPlace(String license);

    /**
     * 在租用停车场中依据车位位置查找车位
     *
     * @param position 车位位置
     * @return 找到的车位
     */
    ParkPlace getRentParkPlaceByPosition(String position);

    /**
     * 获取该社区中所有未完成的停车单
     *
     * @return 该社区中所有未完成的停车单
     */
    List<ParkBill> getAllUnfinishParkBill();

    /**
     * 获取该社区中所有未完成的停车单
     *
     * @param community 社区
     * @return 该社区中所有未完成的停车单
     */
    List<ParkBill> getAllUnfinishParkBill(Community community);

    /**
     * 获取该社区中所有已完成的停车单
     *
     * @return 获取该社区中所有已完成的停车单
     */
    List<ParkBill> getAllFinishParkBill();

    /**
     * 获取该社区中所有已完成的停车单
     *
     * @param community 社区
     * @return 获取该社区中所有已完成的停车单
     */
    List<ParkBill> getAllFinishParkBill(Community community);
}
