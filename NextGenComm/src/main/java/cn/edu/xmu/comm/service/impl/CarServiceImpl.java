package cn.edu.xmu.comm.service.impl;

import cn.edu.xmu.comm.commons.utils.Constants;
import cn.edu.xmu.comm.dao.*;
import cn.edu.xmu.comm.entity.*;
import cn.edu.xmu.comm.service.CarService;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Roger on 2014/12/23 0023.
 * 车辆管理Service
 */
@Service
@Transactional(readOnly = true)
public class CarServiceImpl implements CarService {
    @Resource
    private CarDAO carDAO;

    @Resource
    private ParkPlaceDAO parkPlaceDAO;

    @Resource
    private ParkBillDAO parkBillDAO;

    @Resource
    private ParkingLotDAO parkingLotDAO;

    @Resource
    private CommunityDAO communityDAO;

    @Resource
    private OwnerDAO ownerDAO;

    /**
     * 获取登陆的User所在的社区
     *
     * @return 所在社区
     */
    public Community getCommunityFromActionContext() {
        Community community = (Community) ActionContext.getContext().getSession().get(Constants.COMMUNITY);
        return community;
    }

    /**
     * 添加车辆
     *
     * @param car 车辆
     */
    @Transactional(readOnly = false)
    public void addCar(Car car) {
        carDAO.persist(car);
    }


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
    public Car addCar(String license, Owner owner, Car.CarStatus status, ParkPlace parkPlace) {
        Car car = new Car(license, owner, status, parkPlace);
        carDAO.persist(car);
        return car;
    }

    /**
     * 添加停车位
     *
     * @param parkPlace 车位
     */
    @Transactional(readOnly = false)
    public void addParkPlace(ParkPlace parkPlace) {
        parkPlaceDAO.persist(parkPlace);
    }


    /**
     * 添加停车账单
     *
     * @param parkBill 停车账单
     */
    @Transactional(readOnly = false)
    public void addParkBill(ParkBill parkBill) {
        parkBillDAO.persist(parkBill);
    }

    /**
     * 添加停车场
     *
     * @param parkingLot 停车场
     */
    @Transactional(readOnly = false)
    public void addParkingLot(ParkingLot parkingLot) {
        parkingLotDAO.persist(parkingLot);
        communityDAO.merge(parkingLot.getCommunity());
    }

    //region Get ParkingLot by Community

    /**
     * 依据社区查询临时停车车位
     *
     * @param community 社区
     * @return ParkingLot tempParkingLot
     */
    public ParkingLot getTempParkingLot(Community community) {
        return parkingLotDAO.getTempParkingLot(community);
    }

    /**
     * 依据社区查询租用停车车位
     *
     * @param community 社区
     * @return ParkingLot rentParkingLot
     */
    public ParkingLot getRentParkingLot(Community community) {
        return parkingLotDAO.getRentParkingLot(community);
    }

    /**
     * 获取当前session中的租用停车场
     *
     * @return 当前session中的租用停车场
     */
    public ParkingLot getRentParkingLotInSession() {
        return getRentParkingLot(getCommunityFromActionContext());
    }
    //endregion

    //region Judge Has Free Temp ParkPlace

    /**
     * 获取社区临时停车场的大小
     *
     * @param community 社区
     * @return Integer 社区临时停车场的大小
     */
    public int getCommunityTempParkingLotSize(Community community) {
        return getTempParkingLot(community).getParkingLotSize();
    }

    /**
     * 查询社区中临时停车的数量
     *
     * @param community 社区
     * @return Integer 社区中临时停车的数量
     */
    public int getSizeOfUnpaidParkingBIll(Community community) {
        return parkBillDAO.getSizeOfUnfinishedBill(community);
    }

    /**
     * 判断社区Community是否有空车位
     *
     * @param community 社区
     * @return Boolean 是否有空车位 True 有空车位
     */
    public Boolean hasFreeTempParkPlace(Community community) {
        community = communityDAO.get(community.getId());
        Integer sizeTempParkingLot = getCommunityTempParkingLotSize(community);
        Integer sizeTempParking = getSizeOfUnpaidParkingBIll(community);
        return sizeTempParkingLot > sizeTempParking;
    }
    //endregion

    //region Generate Parking Bill

    /**
     * 判断一辆车是否在指定小区是否有未完成的停车单
     *
     * @param community 社区
     * @param license   车牌
     * @return true 有 false 无
     */
    public boolean carHasUnfinishBill(Community community, String license) {
        return parkBillDAO.carHasUnfinishBill(community, license);
    }

    /**
     * 新建临时停车单
     *
     * @param community 社区
     * @param owner     业主
     * @param license   车牌
     * @return parkBill 临时停车单
     */
    @Transactional(readOnly = false)
    public ParkBill addParkBill(Community community, Owner owner, String license) {
        ParkBill parkBill = new ParkBill(license, community, owner, new Timestamp(System.currentTimeMillis()));
        parkBillDAO.persist(parkBill);
        communityDAO.merge(parkBill.getCommunity());
        ownerDAO.merge(parkBill.getOwner());
        return parkBill;
    }

    /**
     * 新建临时停车单
     *
     * @param community 社区
     * @param ownerId   业主编号
     * @param license   车牌
     * @return parkBill 临时停车单
     */
    @Transactional(readOnly = false)
    public ParkBill addParkBill(Community community, Integer ownerId, String license) {
        Owner owner = ownerDAO.get(ownerId);
        return addParkBill(community, owner, license);
    }

    /**
     * 根据车牌来获得未完成的停车单(没有离开时间)
     * 社区信息由parkBillDao在session中获得
     *
     * @param community 社区
     * @param license   车牌号
     * @return ParkBill
     */
    public ParkBill getParkBillByLicense(Community community, String license) {
        return parkBillDAO.getUnfinishedParkBill(community, license);
    }

    /**
     * 根据临时停车单生成停车单
     *
     * @param parkBill 未完成账单
     */
    @Transactional(readOnly = false)
    public ParkBill generateParkBill(ParkBill parkBill) {
        parkBill.setEndTime(new Date(System.currentTimeMillis()));
        parkBill.generateParkBill();
        parkBillDAO.merge(parkBill);
        return parkBill;
    }

    /**
     * 完成临时停车单
     *
     * @param community 社区
     * @param license   车牌号
     * @return parkBill 完成的临时停车单
     */
    @Transactional(readOnly = false)
    public ParkBill finishParkBill(Community community, String license) {
        ParkBill parkBill = getParkBillByLicense(community, license);
        if (parkBill == null)
            return null;
        parkBill = generateParkBill(parkBill);
        parkBillDAO.merge(parkBill);
        return parkBill;
    }

    /**
     * 完成临时停车单
     *
     * @param parkBillId 停车单编号
     * @return parkBill 完成的临时停车单
     */
    @Transactional(readOnly = false)
    public ParkBill finishParkBill(Integer parkBillId) {
        ParkBill parkBill = parkBillDAO.get(parkBillId);
        if (parkBill == null)
            return null;
        parkBill = generateParkBill(parkBill);
        parkBillDAO.merge(parkBill);
        return parkBill;
    }
    //endregion

    //region Judge is Rent Car

    /**
     * 根据Id获得车辆
     *
     * @param license 编号
     * @return Car 车辆
     */
    public Car getCarByLicense(String license) {
        return carDAO.get(license);
    }

    /**
     * 判断一辆是否为租用车位车辆
     *
     * @param community 社区
     * @param license   车牌
     * @return 该车是否拥有租用车位
     */
    public Boolean isRentCar(Community community, String license) {
        Car car = getCarByLicense(license);
        if (car == null)
            return false;
        ParkPlace parkPlace = car.getParkPlace();
        ParkingLot parkingLotRent = getRentParkingLot(community);
        return parkPlaceDAO.hasParkPlace(parkingLotRent, parkPlace);
    }

    /**
     * 判断一辆车是否在已登陆用户所在小区与租用车位
     *
     * @param license 车牌
     * @return 该车是否拥有租用车位
     */
    public Boolean isRentCar(String license) {
        Community community = getCommunityFromActionContext();
        return isRentCar(community, license);
    }
    //endregion

    //region Get Owner List

    /**
     * 依据社区和姓名查找业主
     *
     * @param community 社区
     * @param name      姓名
     * @return 业主
     */
    public List<Owner> getOwnerByName(Community community, String name) {
        return ownerDAO.getByName(community, name);
    }
    //endregion

    //region Get ParkPlace In RentParkingLot

    /**
     * 依据停车位状态获取在租用停车场中各个状态的停车位列表
     *
     * @param parkingLot 停车场
     * @param status     状态 ：FREE:没有车位、LOCK:锁定的车位、RENT:租用的车位
     * @return 停车位列表
     */
    public List<ParkPlace> getParkPlaceRent(ParkingLot parkingLot, ParkPlace.ParkPlaceStatus status) {
        return parkPlaceDAO.getRentParkPlace(parkingLot, status);
    }

    /**
     * 获取可租用的停车位
     *
     * @return 可租用的停车位列表
     */
    public List<ParkPlace> getFreeParkPlaceRent() {
        return getParkPlaceRent(getRentParkingLotInSession(), ParkPlace.ParkPlaceStatus.FREE);
    }

    /**
     * 获取已租用的停车位
     *
     * @return 获取已租用的停车位
     */
    public List<ParkPlace> getRentParkPlaceRent() {
        return getParkPlaceRent(getRentParkingLotInSession(), ParkPlace.ParkPlaceStatus.RENT);
    }

    /**
     * 获取已锁定的停车位 已打印合同
     *
     * @return 获取已锁定的停车位
     */
    public List<ParkPlace> getParkPlaceRent() {
        return getParkPlaceRent(getRentParkingLotInSession(), ParkPlace.ParkPlaceStatus.LOCK);
    }
    //endregion

    /**
     * 添加车辆锁住停车位
     *
     * @param license   车牌
     * @param owner     业主
     * @param parkPlace 停车位
     * @return 新增的车辆
     */
    @Transactional(readOnly = false)
    public Car addCarLockParkPlace(String license, Owner owner, ParkPlace parkPlace) {
        Car car = new Car(license, owner, Car.CarStatus.RENT, parkPlace);
        parkPlace.lockParkPlace();
        carDAO.persist(car);
        parkPlaceDAO.merge(parkPlace);
        return car;
    }

    /**
     * 确认汽车租用停车位
     *
     * @param car
     */
    public void confirmCarRentParkPlace(Car car) {
        ParkPlace parkPlace = car.getParkPlace();
        parkPlace.rentParkPlace();
        parkPlaceDAO.merge(parkPlace);
    }

    /**
     * 依据车牌确认汽车租用停车位
     *
     * @param license 车牌号
     */
    @Transactional(readOnly = false)
    public void confirmCarRentParkPlace(String license) {
        Car car = getCarByLicense(license);
        confirmCarRentParkPlace(car);
    }

    /**
     * 删除汽车释放停车位
     *
     * @param car 车辆
     */
    @Transactional(readOnly = false)
    public void deleteCarFreeParkPlace(Car car) {
        ParkPlace parkPlace = car.getParkPlace();
        parkPlace.freeParkPlace();
        carDAO.delete(car);
        parkPlaceDAO.merge(parkPlace);
    }

    /**
     * 依据车牌删除汽车释放停车位
     *
     * @param license 车牌号
     */
    @Transactional(readOnly = false)
    public void deleteCarFreeParkPlace(String license) {
        Car car = getCarByLicense(license);
        deleteCarFreeParkPlace(car);
    }

    /**
     * 车辆租用到期释放停车位
     *
     * @param car 车辆
     */
    public void removeCarFreeParkPlace(Car car) {
        ParkPlace parkPlace = car.getParkPlace();
        car.setStatus(Car.CarStatus.NO);
        parkPlace.freeParkPlace();
        carDAO.merge(car);
        parkPlaceDAO.merge(parkPlace);
    }

    /**
     * 车辆租用到期释放停车位
     *
     * @param license 车牌号
     */
    public void removeCarFreeParkPlace(String license) {
        Car car = getCarByLicense(license);
        removeCarFreeParkPlace(car);
    }

    /**
     * 在租用停车场中依据车位位置查找车位
     *
     * @param position 车位位置
     * @return 找到的车位
     */
    public ParkPlace getRentParkPlaceByPosition(String position) {
        return parkPlaceDAO.get(getRentParkingLotInSession(), position);
    }

    /**
     * 获取该社区中所有未完成的停车单
     *
     * @return 该社区中所有未完成的停车单
     */
    public List<ParkBill> getAllUnfinishParkBill() {
        return getAllUnfinishParkBill(getCommunityFromActionContext());
    }

    /**
     * 获取该社区中所有未完成的停车单
     *
     * @param community 社区
     * @return 该社区中所有未完成的停车单
     */
    public List<ParkBill> getAllUnfinishParkBill(Community community) {
        return parkBillDAO.getAllUnfinishParkBill(community);
    }

    /**
     * 获取该社区中所有已完成的停车单
     *
     * @return 获取该社区中所有已完成的停车单
     */
    public List<ParkBill> getAllFinishParkBill() {
        return getAllFinishParkBill(getCommunityFromActionContext());
    }

    /**
     * 获取该社区中所有已完成的停车单
     *
     * @param community 社区
     * @return 获取该社区中所有已完成的停车单
     */
    public List<ParkBill> getAllFinishParkBill(Community community) {
        return parkBillDAO.getAllFinishParkBill(getCommunityFromActionContext());
    }

    /**
     * 依据id获取停车单
     *
     * @param id 编号
     * @return 停车单
     */
    public ParkBill getParkBillById(Integer id) {
        return parkBillDAO.get(id);
    }

}
