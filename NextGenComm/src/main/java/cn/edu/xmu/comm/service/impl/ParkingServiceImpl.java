package cn.edu.xmu.comm.service.impl;

import cn.edu.xmu.comm.commons.annotation.Required;
import cn.edu.xmu.comm.commons.utils.SessionUtils;
import cn.edu.xmu.comm.entity.*;
import cn.edu.xmu.comm.repository.*;
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

    @Resource
    private CarRepository carRepository;
    @Resource
    private CommunityRepository communityRepository;
    @Resource
    private OwnerRepository ownerRepository;

    @Resource
    private ParkingBillRepository parkingBillRepository;
    @Resource
    private ParkingLotRepository parkingLotRepository;
    @Resource
    private ParkingPlaceRepository parkingPlaceRepository;

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
        carRepository.save(car);
        return car;
    }

    /**
     * 获得所有车辆
     *
     * @return 车辆列表
     */
    @Override
    @Required(name = "clerk,guard")
    public List<Car> getAllCars() {
        Community community = SessionUtils.getCommunity();
        return carRepository.findByCommunity(community);
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
        Car car = carRepository.findByLicense(license);
        if (car == null) {
            return false;
        }
        ParkingPlace parkingPlace = car.getParkingPlace();
        ParkingLot parkingLotRent = getRentParkingLot();
        return parkingPlaceRepository.existsByParkingLotAndId(parkingLotRent, parkingPlace.getId());
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
        ParkingLot tempParkingLot = parkingLotRepository.getTempParkingLot(community);
        Integer sizeTempParkingLot = tempParkingLot.getSize();
        Integer sizeTempParking = parkingBillRepository.countByCommunityAndEndTimeIsNull(community);
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
        ParkingLot parkingLot = parkingLotRepository.getRentParkingLot(community);
        return parkingPlaceRepository.findByParkingLotAndStatus(parkingLot, ParkingPlace.ParkingPlaceStatus.FREE);
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
        Owner owner = ownerRepository.getOne(ownerId);
        ParkingPlace parkingPlace = parkingPlaceRepository.getOne(parkPlaceId);
        Car car = new Car(license, owner, Car.CarStatus.RENT, parkingPlace);
        parkingPlace.rentParkPlace();
        carRepository.save(car);
        parkingPlaceRepository.save(parkingPlace);
    }

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
        ParkingLot parkingLot = parkingLotRepository.getOne(parkingLotId);
        ParkingPlace parkingPlace = new ParkingPlace(position, parkingLot);
        parkingLot.getParkingPlaces().add(parkingPlace);
        parkingPlace.setParkingLot(parkingLot);
        parkingPlaceRepository.save(parkingPlace);
        parkingLotRepository.save(parkingLot);
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
        Owner owner = ownerRepository.getOne(ownerId);
        ParkingBill parkingBill = new ParkingBill(license, community, owner, new Date(System.currentTimeMillis()));
        parkingBillRepository.save(parkingBill);
        communityRepository.save(parkingBill.getCommunity());
        ownerRepository.save(parkingBill.getOwner());
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
        return parkingBillRepository.findByCommunityAndEndTimeIsNotNull(community);
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
        return parkingBillRepository.findByCommunityAndEndTimeIsNull(community);
    }

    /**
     * 依据id获取停车单
     *
     * @param id 编号
     * @return 停车单
     */
    @Override
    @Required(name = "clerk,guard")
    public ParkingBill getParkBill(Integer id) {
        return parkingBillRepository.getOne(id);
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
        ParkingBill parkBill = parkingBillRepository.getOne(parkBillId);
        generateParkBill(parkBill);
        parkingBillRepository.save(parkBill);
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
        return parkingLotRepository.getRentParkingLot(community);
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
        return parkingLotRepository.getTempParkingLot(community);
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
        return parkingPlaceRepository.findByParkingLotCommunity(community);
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
}
