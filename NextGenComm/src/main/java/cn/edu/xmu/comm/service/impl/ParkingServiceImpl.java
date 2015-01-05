package cn.edu.xmu.comm.service.impl;

import cn.edu.xmu.comm.commons.utils.SessionUtils;
import cn.edu.xmu.comm.dao.OwnerDAO;
import cn.edu.xmu.comm.dao.ParkingBillDAO;
import cn.edu.xmu.comm.dao.ParkingLotDAO;
import cn.edu.xmu.comm.dao.ParkingPlaceDAO;
import cn.edu.xmu.comm.dao.impl.CarDaoImpl;
import cn.edu.xmu.comm.dao.impl.CommunityDaoImpl;
import cn.edu.xmu.comm.entity.*;
import cn.edu.xmu.comm.service.ParkingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Roger on 2014/12/23 0023.
 * 车辆管理Service
 */
@Service
@Transactional(readOnly = true)
public class ParkingServiceImpl implements ParkingService {

    //region DAO
    @Resource
    private CarDaoImpl carDAO;
    @Resource
    private CommunityDaoImpl communityDAO;
    @Resource
    private OwnerDAO ownerDAO;
    @Resource
    private ParkingBillDAO parkingBillDAO;
    @Resource
    private ParkingLotDAO parkingLotDAO;
    @Resource
    private ParkingPlaceDAO parkingPlaceDAO;
    //endregion

    //region Car Service

    /**
     * 添加租用车位的车辆
     *
     * @param license 车牌
     * @param owner   业主
     * @return 新添加的汽车
     */
    @Override
    @Transactional(readOnly = false)
    public Car addCar(String license, Owner owner) {
        Car car = new Car(license, owner);
        carDAO.persist(car);
        return car;
    }

//    /**
//     * 添加买车位的车辆
//     *
//     * @param license      车牌
//     * @param owner        业主
//     * @param parkingPlace 停车位
//     * @return 新添加的汽车
//     */
//    @Transactional(readOnly = false)
//    public Car addBuyCar(String license, Owner owner, ParkingPlace parkingPlace) {
//        Car car = new Car(license, owner, Car.CarStatus.BUY, parkingPlace);
//        carDAO.persist(car);
//        return car;
//    }

    @Override
    public List<Car> getAllCars() {
        Community community = SessionUtils.getCommunity();
        return carDAO.getAll(community);
    }

    /**
     * 判断一辆是否为租用车位车辆
     *
     * @param license 车牌
     * @return 该车是否拥有租用车位
     */
    @Override
    public Boolean isRentCar(String license) {
        Car car = carDAO.get(license);
        if (car == null) {
            return false;
        }
        ParkingPlace parkingPlace = car.getParkingPlace();
        ParkingLot parkingLotRent = getRentParkingLot();
        return parkingPlaceDAO.hasParkingPlace(parkingLotRent, parkingPlace);
    }
    //endregion

    //region ParkingPlace Service

    /**
     * 判断社区Community是否有空车位
     *
     * @return Boolean 是否有空车位 True 有空车位
     */
    @Override
    public Boolean hasFreeTempParkPlace() {
        Community community = SessionUtils.getCommunity();
        ParkingLot tempParkingLot = parkingLotDAO.getTempParkingLot(community);
        Integer sizeTempParkingLot = tempParkingLot.getSize();
        Integer sizeTempParking = parkingBillDAO.getSizeOfUnfinishedBill(community);
        return sizeTempParkingLot > sizeTempParking;
    }

    @Override
    public void finishParkBill(Integer parkBillId) {

    }

    /**
     * 获取可租用的停车位
     *
     * @return 可租用的停车位列表
     */
    @Override
    public List<ParkingPlace> getFreeParkPlaceRent() {
        Community community = SessionUtils.getCommunity();
        ParkingLot parkingLot = parkingLotDAO.getRentParkingLot(community);
        return parkingPlaceDAO.getRentParkPlace(parkingLot, ParkingPlace.ParkPlaceStatus.FREE);
    }
//
//    /**
//     * 添加停车位
//     *
//     * @param parkingPlace 车位
//     */
//    @Transactional(readOnly = false)
//    public void addParkingPlace(ParkingPlace parkingPlace) {
//        parkingPlaceDAO.persist(parkingPlace);
//    }
//

    /**
     * 依据车牌确认汽车租用停车位
     *
     * @param license 车牌号
     */
    @Override
    @Transactional(readOnly = false)
    public void confirmCarRentParkPlace(String license) {
        Car car = carDAO.get(license);
        ParkingPlace parkingPlace = car.getParkingPlace();
        parkingPlace.rentParkPlace();
        parkingPlaceDAO.merge(parkingPlace);
    }

    /**
     * 添加车位
     *
     * @param parkingLotId 停车场编号
     * @param position     位置
     */
    @Override
    @Transactional(readOnly = false)
    public void addParkingPlace(Integer parkingLotId, String position) {
        ParkingLot parkingLot = parkingLotDAO.get(parkingLotId);
        ParkingPlace parkingPlace = new ParkingPlace(position, parkingLot);
        parkingLot.getParkingPlaces().add(parkingPlace);
        parkingPlace.setParkingLot(parkingLot);
        parkingPlaceDAO.persist(parkingPlace);
        parkingLotDAO.merge(parkingLot);
    }
    //endregion

    //region ParkLot Service

//    /**
//     * 添加停车场
//     *
//     * @param parkingLot 停车场
//     */
//    @Transactional(readOnly = false)
//    public void addParkingLot(ParkingLot parkingLot) {
//        parkingLotDAO.persist(parkingLot);
//        communityDAO.merge(parkingLot.getCommunity());
//    }
    //endregion

    //region ParkingBill Service

    /**
     * 新建临时停车单
     *
     * @param ownerId 业主id
     * @param license 车牌
     * @return parkBill 临时停车单
     */
    @Override
    @Transactional(readOnly = false)
    public ParkingBill addParkBill(Integer ownerId, String license) {
        Community community = SessionUtils.getCommunity();
        Owner owner = ownerDAO.get(ownerId);
        ParkingBill parkingBill = new ParkingBill(license, community, owner, new Date(System.currentTimeMillis()));
        parkingBillDAO.persist(parkingBill);
        communityDAO.merge(parkingBill.getCommunity());
        ownerDAO.merge(parkingBill.getOwner());
        return parkingBill;
    }

    /**
     * 获取该社区中所有已完成的停车单
     *
     * @return 获取该社区中所有已完成的停车单
     */
    @Override
    public List<ParkingBill> getAllFinishParkBill() {
        Community community = SessionUtils.getCommunity();
        return parkingBillDAO.getAllFinishParkBill(community);
    }

    /**
     * 获取该社区中所有未完成的停车单
     *
     * @return 该社区中所有未完成的停车单
     */
    @Override
    public List<ParkingBill> getAllUnfinishParkBill() {
        Community community = SessionUtils.getCommunity();
        return parkingBillDAO.getAllUnfinishParkBill(community);
    }

    /**
     * 依据id获取停车单
     *
     * @param id 编号
     * @return 停车单
     */
    @Override
    public ParkingBill getParkBill(Integer id) {
        return parkingBillDAO.get(id);
    }

//
//    /**
//     * 添加停车账单
//     *
//     * @param parkingBill 停车账单
//     */
//    @Transactional(readOnly = false)
//    public void addParkBill(ParkingBill parkingBill) {
//        parkingBillDAO.persist(parkingBill);
//    }
    //endregion


    //region ParkingLot Service

    /**
     * 依据社区查询租用停车车位
     *
     * @return ParkingLot rentParkingLot
     */
    @Override
    public ParkingLot getRentParkingLot() {
        Community community = SessionUtils.getCommunity();
        return parkingLotDAO.getRentParkingLot(community);
    }

    @Override
    public ParkingLot getTempParkingLot() {
        Community community = SessionUtils.getCommunity();
        return parkingLotDAO.getTempParkingLot(community);
    }

    @Override
    public List<ParkingPlace> getAllParkingPlace() {
        Community community = SessionUtils.getCommunity();
        return parkingPlaceDAO.getAll(community);
    }


//    /**
//     * 获取租用停车场
//     *
//     * @return 租用停车场
//     */
//    @Override
//    public ParkingLot getRentParkingLotInSession() {
//        Community community = SessionUtils.getCommunity();
//        return getRentParkingLot(community);
//    }
    //endregion

    //region Judge Has Free Temp ParkPlace
//
//    /**
//     * 获取社区临时停车场的大小
//     *
//     * @return Integer 社区临时停车场的大小
//     */
//    public int getCommunityTempParkingLotSize() {
//        Community community = SessionUtils.getCommunity();
//        return getTempParkingLot(community).getSize();
//    }
//
//    /**
//     * 查询社区中临时停车的数量
//     *
//     * @param community 社区
//     * @return Integer 社区中临时停车的数量
//     */
//    public int getSizeOfUnpaidParkingBIll(Community community) {
//        return parkingBillDAO.getSizeOfUnfinishedBill(community);
//    }
//

    //endregion

    //region Generate Parking Bill
//
//    /**
//     * 判断一辆车是否在指定小区是否有未完成的停车单
//     *
//     * @param community 社区
//     * @param license   车牌
//     * @return true 有 false 无
//     */
//    public boolean carHasUnfinishBill(Community community, String license) {
//        return parkingBillDAO.carHasUnfinishBill(community, license);
//    }
//

//
//    /**
//     * 新建临时停车单
//     *
//     * @param community 社区
//     * @param ownerId   业主编号
//     * @param license   车牌
//     * @return parkBill 临时停车单
//     */
//    @Transactional(readOnly = false)
//    public ParkingBill addParkBill(Community community, Integer ownerId, String license) {
//        Owner owner = ownerDAO.get(ownerId);
//        return addParkBill(community, owner, license);
//    }
//
//    /**
//     * 根据车牌来获得未完成的停车单(没有离开时间)
//     * 社区信息由parkBillDao在session中获得
//     *
//     * @param community 社区
//     * @param license   车牌号
//     * @return ParkBill
//     */
//    public ParkingBill getParkBillByLicense(Community community, String license) {
//        return parkingBillDAO.getUnfinishedParkBill(community, license);
//    }
//
//    /**
//     * 根据临时停车单生成停车单
//     *
//     * @param parkingBill 未完成账单
//     */
//    @Transactional(readOnly = false)
//    public ParkingBill generateParkBill(ParkingBill parkingBill) {
//        parkingBill.setEndTime(new Date(System.currentTimeMillis()));
//        parkingBill.generateParkBill();
//        parkingBillDAO.merge(parkingBill);
//        return parkingBill;
//    }
//
//    /**
//     * 完成临时停车单
//     *
//     * @param community 社区
//     * @param license   车牌号
//     * @return parkBill 完成的临时停车单
//     */
//    @Transactional(readOnly = false)
//    public ParkingBill finishParkBill(Community community, String license) {
//        ParkingBill parkingBill = getParkBillByLicense(community, license);
//        if (parkingBill == null)
//            return null;
//        parkingBill = generateParkBill(parkingBill);
//        parkingBillDAO.merge(parkingBill);
//        return parkingBill;
//    }
//
//    /**
//     * 完成临时停车单
//     *
//     * @param parkBillId 停车单编号
//     * @return parkBill 完成的临时停车单
//     */
//    @Transactional(readOnly = false)
//    public ParkingBill finishParkBill(Integer parkBillId) {
//        ParkingBill parkingBill = parkingBillDAO.get(parkBillId);
//        if (parkingBill == null)
//            return null;
//        parkingBill = generateParkBill(parkingBill);
//        parkingBillDAO.merge(parkingBill);
//        return parkingBill;
//    }
//    //endregion
//
//    //region Judge is Rent Car
//
//    /**
//     * 根据Id获得车辆
//     *
//     * @param license 编号
//     * @return Car 车辆
//     */
//    public Car getCarByLicense(String license) {
//        return carDAO.get(license);
//    }
//
//
//    /**
//     * 判断一辆车是否在已登陆用户所在小区与租用车位
//     *
//     * @param license 车牌
//     * @return 该车是否拥有租用车位
//     */
//    public Boolean isRentCar(String license) {
//        Community community = getCommunityFromActionContext();
//        return isRentCar(community, license);
//    }
//    //endregion
//
//    //region Get Owner List
//
//    /**
//     * 依据社区和姓名查找业主
//     *
//     * @param community 社区
//     * @param name      姓名
//     * @return 业主
//     */
//    public List<Owner> getOwnerByName(Community community, String name) {
//        return ownerDAO.getByName(community, name);
//    }
//    //endregion
//
//    //region Get ParkPlace In RentParkingLot
//
//    /**
//     * 依据停车位状态获取在租用停车场中各个状态的停车位列表
//     *
//     * @param parkingLot 停车场
//     * @param status     状态 ：FREE:没有车位、LOCK:锁定的车位、RENT:租用的车位
//     * @return 停车位列表
//     */
//    public List<ParkingPlace> getParkPlaceRent(ParkingLot parkingLot, ParkingPlace.ParkPlaceStatus status) {
//        return parkingPlaceDAO.getRentParkPlace(parkingLot, status);
//    }
//
//    public List<ParkingPlace> getFreeParkPlaceRent() {
//        return null;
//    }
//
//
//    /**
//     * 获取已租用的停车位
//     *
//     * @return 获取已租用的停车位
//     */
//    public List<ParkingPlace> getRentParkPlaceRent() {
//        return getParkPlaceRent(getRentParkingLotInSession(), ParkingPlace.ParkPlaceStatus.RENT);
//    }
//
//    /**
//     * 获取已锁定的停车位 已打印合同
//     *
//     * @return 获取已锁定的停车位
//     */
//    public List<ParkingPlace> getParkPlaceRent() {
//        return getParkPlaceRent(getRentParkingLotInSession(), ParkingPlace.ParkPlaceStatus.LOCK);
//    }
//    //endregion
//
//    /**
//     * 添加车辆锁住停车位
//     *
//     * @param license      车牌
//     * @param owner        业主
//     * @param parkingPlace 停车位
//     * @return 新增的车辆
//     */
//    @Transactional(readOnly = false)
//    public Car addCarLockParkPlace(String license, Owner owner, ParkingPlace parkingPlace) {
//        Car car = new Car(license, owner, Car.CarStatus.RENT, parkingPlace);
//        parkingPlace.lockParkPlace();
//        carDAO.persist(car);
//        parkingPlaceDAO.merge(parkingPlace);
//        return car;
//    }
//

//

//
//    /**
//     * 删除汽车释放停车位
//     *
//     * @param car 车辆
//     */
//    @Transactional(readOnly = false)
//    public void deleteCarFreeParkPlace(Car car) {
//        ParkingPlace parkingPlace = car.getParkingPlace();
//        parkingPlace.freeParkPlace();
//        carDAO.delete(car);
//        parkingPlaceDAO.merge(parkingPlace);
//    }
//
//    /**
//     * 依据车牌删除汽车释放停车位
//     *
//     * @param license 车牌号
//     */
//    @Transactional(readOnly = false)
//    public void deleteCarFreeParkPlace(String license) {
//        Car car = getCarByLicense(license);
//        deleteCarFreeParkPlace(car);
//    }
//
//    /**
//     * 车辆租用到期释放停车位
//     *
//     * @param car 车辆
//     */
//    public void removeCarFreeParkPlace(Car car) {
//        ParkingPlace parkingPlace = car.getParkingPlace();
//        car.setStatus(Car.CarStatus.NO);
//        parkingPlace.freeParkPlace();
//        carDAO.merge(car);
//        parkingPlaceDAO.merge(parkingPlace);
//    }
//
//    /**
//     * 车辆租用到期释放停车位
//     *
//     * @param license 车牌号
//     */
//    public void removeCarFreeParkPlace(String license) {
//        Car car = getCarByLicense(license);
//        removeCarFreeParkPlace(car);
//    }
//
//    /**
//     * 在租用停车场中依据车位位置查找车位
//     *
//     * @param position 车位位置
//     * @return 找到的车位
//     */
//    public ParkingPlace getRentParkPlaceByPosition(String position) {
//        return parkingPlaceDAO.get(getRentParkingLotInSession(), position);
//    }
//
//    /**
//     * 获取该社区中所有未完成的停车单
//     *
//     * @return 该社区中所有未完成的停车单
//     */
//    public List<ParkingBill> getAllUnfinishParkBill() {
//        return getAllUnfinishParkBill(getCommunityFromActionContext());
//    }
//
//
//    /**
//     * 获取该社区中所有已完成的停车单
//     *
//     * @return 获取该社区中所有已完成的停车单
//     */
//    public List<ParkingBill> getAllFinishParkBill() {
//        return getAllFinishParkBill(getCommunityFromActionContext());
//    }
//
//
//

//

//
//
//    /**
//     * 获取社区中所有的车位
//     *
//     * @param community 社区
//     * @return 所有的车位
//     */
//    public List<ParkingPlace> getAllParkPlace(Community community) {
//        ParkingLot parkingLotRent = community.getParkingLot(ParkingLot.ParkingLotStatus.RENT);
//        ParkingLot parkingLotTemp = community.getParkingLot(ParkingLot.ParkingLotStatus.TEMP);
//        List<ParkingPlace> resultList = parkingLotRent.getParkingPlaces();
//        resultList.addAll(parkingLotTemp.getParkingPlaces());
//        return resultList;
//    }
//
//    public ParkingBill getParkBillById(Integer id) {
//        return null;
//    }
//
//    /**
//     * 在社区中添加临时停车场
//     *
//     * @param community 社区
//     * @return 临时停车场
//     */
//    @Transactional(readOnly = false)
//    public ParkingLot addTempParkingLot(Community community) {
//        ParkingLot tempParkingLot = new ParkingLot();
//        tempParkingLot.setType(ParkingLot.ParkingLotStatus.TEMP);
//        tempParkingLot.setFeeType("GradientParkingCalculator");
//        tempParkingLot.setCommunity(community);
//        tempParkingLot.getGradient().put(30, BigDecimal.valueOf(5));
//        tempParkingLot.getGradient().put(90, BigDecimal.valueOf(10));
//        tempParkingLot.getGradient().put(150, BigDecimal.valueOf(15));
//        tempParkingLot.getGradient().put(210, BigDecimal.valueOf(20));
//        tempParkingLot.setName(community.getName() + "临时停车场");
//        parkingLotDAO.persist(tempParkingLot);
//        return tempParkingLot;
//    }
//
//    /**
//     * 新建租用停车场
//     *
//     * @param community 社区
//     * @return 租用停车场
//     */
//    @Transactional(readOnly = false)
//    public ParkingLot addRentParkingLot(Community community) {
//        ParkingLot rentParkingLot = new ParkingLot();
//        rentParkingLot.setType(ParkingLot.ParkingLotStatus.RENT);
//        rentParkingLot.setCommunity(community);
//        rentParkingLot.setName(community.getName() + "租用停车场");
//        parkingLotDAO.persist(rentParkingLot);
//        return rentParkingLot;
//    }
//
//    /**
//     * 新建租用停车位
//     *
//     * @param community  社区
//     * @param monthlyFee 月费
//     * @param position   位置
//     * @return 新建车位
//     */
//    @Transactional(readOnly = false)
//    public ParkingPlace addRentParkPlace(Community community, BigDecimal monthlyFee, String position) {
//        ParkingLot parkingLot = getRentParkingLot(community);
//        ParkingPlace parkingPlace = new ParkingPlace(position, parkingLot, monthlyFee);
//        parkingPlaceDAO.persist(parkingPlace);
//        parkingLotDAO.merge(parkingLot);
//        return parkingPlace;
//    }
//
//    /**
//     * 新建临时停车位
//     *
//     * @param community 社区
//     * @param position  位置
//     * @return 新建车位
//     */
//    @Transactional(readOnly = false)
//    public ParkingPlace addTempParkPlace(Community community, String position) {
//        ParkingLot parkingLot = getTempParkingLot(community);
//        ParkingPlace parkingPlace = new ParkingPlace(position, parkingLot);
//        parkingPlaceDAO.persist(parkingPlace);
//        parkingLotDAO.merge(parkingLot);
//        return parkingPlace;
//    }
}
