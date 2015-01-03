package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.entity.*;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Roger on 2014/12/23 0023.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class CarServiceTest extends TestCase {

    @Resource
    private PropertyService propertyService;

    @Resource
    private CarService carService;

    @Test
    public void testHasFreeTempParkPlace() {
        Community community = propertyService.getCommunity("五缘公寓");
        carService.hasFreeTempParkPlace(community);
    }

    @Test
    public void testGetTempParkingLot() {
        Community community = propertyService.getCommunity("五缘公寓");
        System.out.println(community.getName());
        ParkingLot tempParkingLot = carService.getTempParkingLot(community);
    }

    @Test
    public void newParkingLot() {
        Community community = propertyService.getCommunity("五缘公寓");
        Owner owner = propertyService.getOwner("陆垚杰");

        ParkingLot parkingLotTemp = new ParkingLot();
        ParkingLot parkingLotRent = new ParkingLot();
        ParkPlace temp = newGroundParkPlace();
        ParkPlace rent = newUnderParkPlace();
        ParkBill parkBill = newParkBill(owner, community);

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
}
