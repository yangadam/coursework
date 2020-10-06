package cn.edu.xmu.comm.service.impl;

import cn.edu.xmu.comm.commons.annotation.Required;
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
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Roger on 2014/12/23 0023.
 * 车位管理模块Service实现
 */
@Service
@Transactional(readOnly = true)
public class ParkingServiceImpl implements ParkingService {

    //region Car Service

    //region DAO
    @Resource
    private CarDaoImpl carDAO;
    @Resource
    private CommunityDaoImpl communityDAO;
    @Resource
    private OwnerDAO ownerDAO;
    //endregion

    //region ParkingPlace Service
    @Resource
    private ParkingBillDAO parkingBillDAO;
    @Resource
    private ParkingLotDAO parkingLotDAO;
    @Resource
    private ParkingPlaceDAO parkingPlaceDAO;

    /**
     * 添加租用车位的车辆
     *
     * @param license 车牌
     * @param owner   业主
     * @return 新添加的汽车
     */
    @Override
    @Required(name = "clerk,guard")
    @Transactional(readOnly = false)
    public Car addCar(String license, Owner owner) {
        Car car = new Car(license, owner);
        carDAO.persist(car);
        return car;
    }
    //endregion

    //region ParkingBill Service

    /**
     * 获得所有车辆
     *
     * @return 车辆列表
     */
    @Override
    @Required(name = "clerk,guard")
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
    @Required(name = "clerk,guard")
    public Boolean isRentCar(String license) {
        Car car = carDAO.get(license);
        if (car == null) {
            return false;
        }
        ParkingPlace parkingPlace = car.getParkingPlace();
        ParkingLot parkingLotRent = getRentParkingLot();
        return parkingPlaceDAO.hasParkingPlace(parkingLotRent, parkingPlace);
    }

    /**
     * 判断社区Community是否有空车位
     *
     * @return Boolean 是否有空车位 True 有空车位
     */
    @Override
    @Required(name = "clerk,guard")
    public Boolean hasFreeTempParkPlace() {
        Community community = SessionUtils.getCommunity();
        ParkingLot tempParkingLot = parkingLotDAO.getTempParkingLot(community);
        Integer sizeTempParkingLot = tempParkingLot.getSize();
        Integer sizeTempParking = parkingBillDAO.getSizeOfUnfinishedBill(community);
        return sizeTempParkingLot > sizeTempParking;
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
        return parkingPlaceDAO.getRentParkPlace(parkingLot, ParkingPlace.ParkingPlaceStatus.FREE);
    }

    /**
     * 确认租车
     *
     * @param license     车牌号
     * @param ownerId     业主编号
     * @param parkPlaceId 停车场编号
     */
    @Override
    @Required(name = "clerk,guard")
    @Transactional(readOnly = false)
    public void confirmCarRentParkPlace(String license, Integer ownerId, Integer parkPlaceId) {
        Owner owner = ownerDAO.get(ownerId);
        ParkingPlace parkingPlace = parkingPlaceDAO.get(parkPlaceId);
        Car car = new Car(license, owner, Car.CarStatus.RENT, parkingPlace);
        parkingPlace.rentParkPlace();
        carDAO.persist(car);
        parkingPlaceDAO.merge(parkingPlace);
    }
    //endregion

    //region ParkingLot Service

    /**
     * 添加车位
     *
     * @param parkingLotId 停车场编号
     * @param position     位置
     */
    @Override
    @Required(name = "clerk,guard")
    @Transactional(readOnly = false)
    public void addParkingPlace(Integer parkingLotId, String position) {
        ParkingLot parkingLot = parkingLotDAO.get(parkingLotId);
        ParkingPlace parkingPlace = new ParkingPlace(position, parkingLot);
        parkingLot.getParkingPlaces().add(parkingPlace);
        parkingPlace.setParkingLot(parkingLot);
        parkingPlaceDAO.persist(parkingPlace);
        parkingLotDAO.merge(parkingLot);
    }

    /**
     * 新建临时停车单
     *
     * @param ownerId 业主id
     * @param license 车牌
     * @return parkBill 临时停车单
     */
    @Override
    @Required(name = "clerk,guard")
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
    @Required(name = "clerk,guard")
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
    @Required(name = "clerk,guard")
    public List<ParkingBill> getAllUnfinishParkBill() {
        Community community = SessionUtils.getCommunity();
        return parkingBillDAO.getAllUnfinishedParkBill(community);
    }
    //endregion

    /**
     * 依据id获取停车单
     *
     * @param id 编号
     * @return 停车单
     */
    @Override
    @Required(name = "clerk,guard")
    public ParkingBill getParkBill(Integer id) {
        return parkingBillDAO.get(id);
    }

    /**
     * 完成临时停车单的缴费
     *
     * @param parkBillId 停车单id
     * @return 停车单
     */
    @Override
    @Transactional(readOnly = false)
    public ParkingBill finishParkBill(Integer parkBillId) {
        ParkingBill parkBill = parkingBillDAO.get(parkBillId);
        generateParkBill(parkBill);
        parkingBillDAO.merge(parkBill);
        return parkBill;
    }

    /**
     * 查询租用停车车位
     *
     * @return ParkingLot rentParkingLot
     */
    @Override
    @Required(name = "clerk,guard")
    public ParkingLot getRentParkingLot() {
        Community community = SessionUtils.getCommunity();
        return parkingLotDAO.getRentParkingLot(community);
    }

    /**
     * 获取临时停车场
     *
     * @return 临时停车场
     */
    @Override
    @Required(name = "clerk,guard")
    public ParkingLot getTempParkingLot() {
        Community community = SessionUtils.getCommunity();
        return parkingLotDAO.getTempParkingLot(community);
    }

    /**
     * 获得所有停车位
     *
     * @return 停车位列表
     */
    @Override
    @Required(name = "clerk,guard")
    public List<ParkingPlace> getAllParkingPlace() {
        Community community = SessionUtils.getCommunity();
        return parkingPlaceDAO.getAll(community);
    }

    /**
     * 根据临时停车单生成停车单
     *
     * @param parkingBill 未完成账单
     * @return 停车单
     */
    @Override
    @Transactional(readOnly = false)
    public ParkingBill generateParkBill(ParkingBill parkingBill) {
        parkingBill.setEndTime(new Timestamp(System.currentTimeMillis()));
        parkingBill.generateParkBill();
        return parkingBill;
    }
    //endregion
}
