package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.entity.*;
import cn.edu.xmu.comm.service.CarService;
import cn.edu.xmu.comm.service.PropertyService;
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

    @Override
    public String execute() {
        //addData();
        testIsRentCar();
        // testFinishParkBill();
        //testHasFreeTempParkPlace();
        //addData();
        //testFinishParkBill();
        return SUCCESS;
    }

    public void testIsRentCar() {
        Community community = propertyService.getCommunityByName("五缘公寓");
        String license = "浙AJ9225";
        carService.isRentCar(community, license);
    }

    public void testFinishParkBill() {
        Community community = propertyService.getCommunityByName("五缘公寓");
        String license = "浙AH2828";
        carService.finishParkBill(community, license);
    }

    public void testHasFreeTempParkPlace() {
        Community community = propertyService.getCommunityByName("五缘公寓");
        System.out.print(carService.hasFreeTempParkPlace(community));
    }

    private void addData() {
        Community community = propertyService.addCommunity("五缘公寓");
        Building building = propertyService.addBuilding(1, community);
        Floor floor = propertyService.addFloor(1, building);
        Room room = propertyService.addRoom("101", 100.0, floor);
        Owner owner = propertyService.addOwner("lyj", "123", "陆垚杰", room);
        addParkingLot();
    }

    public void addParkingLot() {
        Community community = propertyService.getCommunityByName("五缘公寓");
        Owner owner = propertyService.getOwnerByName("陆垚杰");

        ParkingLot parkingLotTemp = new ParkingLot();
        ParkingLot parkingLotRent = new ParkingLot();
        ParkPlace temp = newGroundParkPlace();
        ParkPlace rent = newUnderParkPlace();
        ParkBill parkBill = newParkBill(owner, community);
        Car car = newCar(owner, rent);

        temp.setParkingLot(parkingLotTemp);
        rent.setParkingLot(parkingLotRent);
        parkingLotTemp.setCommunity(community);
        parkingLotRent.setCommunity(community);
        parkingLotTemp.setType(ParkingLot.TEMP);
        parkingLotRent.setType(ParkingLot.RENT);
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
        carService.addCar(car);
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

    private Car newCar(Owner owner, ParkPlace parkPlace) {
        Car car = new Car();
        car.setLicense("浙AJ9225");
        car.setOwner(owner);
        car.setParkPlace(parkPlace);
        car.setStatus(Car.RENT);
        return car;
    }
}
