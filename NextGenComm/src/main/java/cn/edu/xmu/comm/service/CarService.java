package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.dao.*;
import cn.edu.xmu.comm.entity.*;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Roger on 2014/12/23 0023.
 * 车辆管理Service
 */
@Service
@Transactional(readOnly = true)
public class CarService {
    @Autowired
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
        Owner owner = (Owner) ActionContext.getContext().getSession().get("USER");
        //TODO
        return owner.getCommunity();
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
        Integer sizeTempParkingLot = getCommunityTempParkingLotSize(community);
        Integer sizeTempParking = getSizeOfUnpaidParkingBIll(community);
        return sizeTempParkingLot > sizeTempParking;
    }
    //endregion

    /**
     * 判断一辆车是否在指定小区是否有未完成的停车单
     * @param community 社区
     * @param license 车牌
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

    //region Generate Parking Bill

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
    public ParkBill finishParkBill(Community community, String license) {
        ParkBill parkBill = getParkBillByLicense(community, license);
        if (parkBill == null)
            return null;
        parkBill = generateParkBill(parkBill);
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
     * @param license 车牌
     * @return 该车是否拥有租用车位
     */
    public Boolean isRentCar(String license) {
        Community community = getCommunityFromActionContext();
        return isRentCar(community, license);
    }
    //endregion

    /**
     * 依据社区和姓名查找业主
     * @param community 社区
     * @param name 姓名
     * @return 业主
     */
    public List<Owner> getOwnerByName(Community community, String name) {
        return ownerDAO.getByName(community, name);
    }

}
