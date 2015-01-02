package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.commons.exception.MailException;
import cn.edu.xmu.comm.commons.utils.MailUtils;
import cn.edu.xmu.comm.entity.*;
import cn.edu.xmu.comm.service.CarService;
import cn.edu.xmu.comm.service.FinanceService;
import cn.edu.xmu.comm.service.PropertyService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 测试用的Action
 *
 * @author Mengmeng Yang
 * @version 12/24/2014
 */
@Controller
public class TestAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    @Resource
    private CarService carService;

    @Resource
    private FinanceService financeService;

    @Override
    public String execute() {
        Owner owner = propertyService.getOwner("陆垚杰");
        ActionContext.getContext().getSession().put("USER", owner);
        //addProperty();
        //AddDeviceValue();
        //testDelDeviceValue();
        //testAddDeviceValue();
        //testAddDeviceValue();
        //testUpdateDevice();
        //testOrder();
        //addBillItems(owner);
        //addParkingLot();
        financeService.addBillItem();
        SendMail();
        return SUCCESS;
    }

    public void SendMail() {
        MailUtils mailUtils = new MailUtils();
        Owner owner = propertyService.getOwner("陆垚杰");
        try {
            financeService.sendOverDueMail(owner, mailUtils);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }

//    //region Device Input
//    public void testAddDeviceValue() {
//        financeService.addDeviceValue(589, new Date(), BigDecimal.valueOf(10));
//    }
//
//    public void testUpdateDevice() {
//        Device device = financeService.getDeviceById(589);
//        financeService.updateDeviceValue(589, device.getLastTime(), BigDecimal.valueOf(50));
//        financeService.updateDeviceValue(589, new Date(), BigDecimal.valueOf(10));
//    }
//
//    public void testOrder() {
//        Device device = financeService.getDeviceById(589);
//    }
//
//    public void AddDeviceValue() {
//        for (long i = 0;i != 10;i++) {
//            financeService.addDeviceValue(589, new Date(System.currentTimeMillis()+i*10000), BigDecimal.valueOf(i * 10));
//        }
//    }
//
//    public void testDelDeviceValue() {
//        for (long i = 0;i != 10;i++) {
//            financeService.delDeviceValue(589);
//        }
//    }
//
//    public void addProperty() {
//        Community community = propertyService.getCommunity("五缘公寓");
//        List<Building> buildingList = addBuildings(community);
//        for (Building building: buildingList) {
//            List<Floor> floorList = addFloors(building);
//            for (Floor floor : floorList) {
//                List<Room> roomList = addRooms(floor);
//            }
//        }
//        propertyService.initialDefaultDevice(community, CountShareCalculator.class.getSimpleName());
//
//    }
//
//    public List<Building> addBuildings(Community community) {
//        return propertyService.addBuilding(1, 10, community);
//    }
//
//    public List<Floor> addFloors(Building building) {
//        return propertyService.addFloorBatch(1, 5, building);
//    }
//
//    public List<Room> addRooms(Floor floor) {
//        return propertyService.addRoomBatch(1, 5, 50.0, floor);
//    }
//    //endregion

    //region Test CarService
    public void testIsRentCar() {
        Community community = propertyService.getCommunity("五缘公寓");
        String license = "浙AJ9225";
        carService.isRentCar(license);
    }

    public void testFinishParkBill() {
        Community community = propertyService.getCommunity("五缘公寓");
        String license = "浙AH2828";
        carService.finishParkBill(community, license);
    }

    public void testHasFreeTempParkPlace() {
        Community community = propertyService.getCommunity("五缘公寓");
        System.out.print(carService.hasFreeTempParkPlace(community));
    }

//    private void addData() {
//        Community community = propertyService.addCommunity("五缘公寓");
//        Building building = propertyService.addBuilding(1, "一号楼", community);
//        Floor floor = propertyService.addFloor(1, building);
//        Room room = propertyService.addRoom("101", 100.0, floor);
//        try {
//            Owner owner = propertyService.addOwner("lyj", "123", "陆垚杰", room);
//        } catch (DifferentCommunityException e) {
//            e.printStackTrace();
//        }
//        addParkingLot();
//    }

    public void addParkingLot() {
        Community community = propertyService.getCommunity("五缘公寓");
        Owner owner = propertyService.getOwner("陆垚杰");

        ParkingLot parkingLotTemp = new ParkingLot();
        ParkingLot parkingLotRent = new ParkingLot();
        ParkPlace temp = newGroundParkPlace();
        ParkPlace rent = newUnderParkPlace();
        ParkBill parkBill = newParkBill(owner, community);
        //Car car = newCar(owner, rent);

        temp.setParkingLot(parkingLotTemp);
        rent.setParkingLot(parkingLotRent);
        parkingLotTemp.setCommunity(community);
        parkingLotRent.setCommunity(community);
        parkingLotTemp.setType(ParkingLot.ParkingLotStatus.TEMP);
        parkingLotRent.setType(ParkingLot.ParkingLotStatus.RENT);
        parkingLotTemp.setFeeType("GradientParkingCalculator");
        parkingLotTemp.getGradient().put(30, BigDecimal.valueOf(5));
        parkingLotTemp.getGradient().put(90, BigDecimal.valueOf(10));
        parkingLotTemp.getGradient().put(150, BigDecimal.valueOf(15));
        parkingLotTemp.getGradient().put(210, BigDecimal.valueOf(20));
        parkBill.setCommunity(community);

        carService.addParkBill(parkBill);
        carService.addParkingLot(parkingLotTemp);
        carService.addParkingLot(parkingLotRent);
        carService.addParkPlace(temp);
        carService.addParkPlace(rent);
        //carService.addCar(car);
    }

    public ParkBill newParkBill(Owner owner, Community community) {
        ParkBill parkBill = new ParkBill("浙AH2828", community, owner, new Date(System.currentTimeMillis()));
        return parkBill;
    }

    private ParkPlace newGroundParkPlace() {
        ParkPlace parkPlace = new ParkPlace();
        parkPlace.setPosition("地上");
        parkPlace.setMonthlyFee(new BigDecimal(10.0));
        return parkPlace;
    }

    private ParkPlace newUnderParkPlace() {
        ParkPlace parkPlace = new ParkPlace();
        parkPlace.setPosition("地下");
        parkPlace.setMonthlyFee(new BigDecimal(10.0));
        return parkPlace;
    }

    private Car testAddCarLockParkPlace() {
        Owner owner = propertyService.getOwner("陆垚杰");
        ParkPlace parkPlace = carService.getRentParkPlaceByPosition("地下");
        Car car = carService.addCarLockParkPlace("浙AJ9225", owner, parkPlace);
        return car;
    }

    private void testRemoCarFreeParkPlace() {
        carService.removeCarFreeParkPlace("浙AJ9225");
    }

    private void testconfirmCarRentParkPlace() {
        carService.confirmCarRentParkPlace("浙AJ9225");
    }
    //endregion
}
