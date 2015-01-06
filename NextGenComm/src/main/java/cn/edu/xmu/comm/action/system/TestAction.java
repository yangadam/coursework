package cn.edu.xmu.comm.action.system;

import cn.edu.xmu.comm.commons.calc.impl.AreaManageFeeCalculator;
import cn.edu.xmu.comm.commons.calc.impl.DateOverdueFineCalculator;
import cn.edu.xmu.comm.commons.calc.impl.FixGarbageFeeCalculator;
import cn.edu.xmu.comm.commons.exception.DeviceException;
import cn.edu.xmu.comm.commons.exception.DifferentCommunityException;
import cn.edu.xmu.comm.entity.*;
import cn.edu.xmu.comm.service.FinanceService;
import cn.edu.xmu.comm.service.ParkingService;
import cn.edu.xmu.comm.service.PropertyService;
import cn.edu.xmu.comm.service.StaffService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/3/2015 0003
 */
@Controller
public class TestAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    @Resource
    private FinanceService financeService;

    @Resource
    private StaffService staffService;

    @Resource
    private ParkingService parkingService;

    public String makePament() {
//        Community community = propertyService.getCommunity("五缘公寓");
//        community.setGarbageFee(BigDecimal.valueOf(4));
//        community.setGarbageFeeType(FixGarbageFeeCalculator.class.getSimpleName());
//        community.setManageFee(BigDecimal.valueOf(2));
//        community.setManageFeeType(AreaManageFeeCalculator.class.getSimpleName());
//        community.setOverDueFeeRate(BigDecimal.valueOf(0.5));
//        community.setOverDueFeeType(DateOverdueFineCalculator.class.getSimpleName());
//        PublicFund publicFund = new PublicFund(BigDecimal.valueOf(1000), "", BigDecimal.ZERO, BigDecimal.valueOf(40));
//        community.setPublicFund(publicFund);
//        propertyService.updateCommunity(community);
        try {
            financeService.generateAllBill();
        } catch (DeviceException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public String generateValue() {
        Random rand = new Random();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = null;
        try {
            dt = sdf.parse("2014-12-6");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Device> devices = financeService.getAllDevice();
        for (Device device : devices) {
            Integer base;
            if (device.getType().toString().equals("水表")) {
                base = 20;
            } else {
                base = 100;
            }
            Property property = device.getProperty();
            if (property instanceof Community) {
                base *= 16;
            } else if (property instanceof Building) {
                base *= 8;
            } else if (property instanceof Floor) {
                base *= 4;
            }
            Integer value = device.getCurrentValue().intValue();

            financeService.addDeviceValue(device.getId(), dt, (double) (value + rand.nextInt(base) + base / 2));
        }
        return SUCCESS;
    }

    public String addParkPlace() {
        for (int i = 1; i <= 30; i++) {
            parkingService.addParkingPlace(1, "地上停车场" + i);
        }
        for (int i = 1; i <= 30; i++) {
            parkingService.addParkingPlace(2, "地下停车场" + i);
        }
        return SUCCESS;
    }

    public String addOwner() {
        List<Room> rooms = propertyService.getAllRoom();
        int i = 1;
        for (Room room : rooms) {
            try {
                propertyService.addOwner("owner" + i, "123", "owner" + i, "pumpking2014@126.com", "18688888888", room);
                i++;
            } catch (DifferentCommunityException e) {
                e.printStackTrace();
            }
        }
        return SUCCESS;
    }

    public String testApllyGradients() {
        financeService.addGradient(BigDecimal.valueOf(0.6), Device.DeviceType.ELECTRICITY);
        financeService.addGradient(BigDecimal.valueOf(2.8), Device.DeviceType.WATER);
        financeService.addGradient(new Double[]{20.0}, new BigDecimal[]{BigDecimal.valueOf(0.6), BigDecimal.valueOf(0.7)}, Device.DeviceType.ELECTRICITY);
        financeService.addGradient(new Double[]{15.0}, new BigDecimal[]{BigDecimal.valueOf(2.8), BigDecimal.valueOf(3.3)}, Device.DeviceType.WATER);
        Community community = propertyService.getCommunity("五缘公寓");
        Set<Gradient> gradients = community.getGradients();
        for (Gradient gradient : gradients) {
            if (gradient.getId() == 1 || gradient.getId() == 2) {
                financeService.applyShareGradient(gradient.getId());
            }
        }
        for (Gradient gradient : gradients) {
            if (gradient.getId() == 3 || gradient.getId() == 4) {
                financeService.applyPrivateGradient(gradient.getId());
            }
        }
        return SUCCESS;
    }

    public String testBuildComm() {
        Community community = propertyService.addCommunity("五缘公寓");
        Building building2 = propertyService.addBuilding(2, 26, community);
        for (int i = 1; i <= building2.getChildCount(); i++) {
            Floor floor = building2.getFloor(i);
            propertyService.addRoom(i + "01", 90.0, floor);
            propertyService.addRoom(i + "02", 90.0, floor);
            propertyService.addRoom(i + "03", 120.0, floor);
            propertyService.addRoom(i + "04", 120.0, floor);
        }
        Building building3 = propertyService.addBuilding(3, 26, community);
        for (int i = 1; i <= building3.getChildCount(); i++) {
            Floor floor = building3.getFloor(i);
            propertyService.addRoom(i + "01", 90.0, floor);
            propertyService.addRoom(i + "02", 90.0, floor);
            propertyService.addRoom(i + "03", 120.0, floor);
            propertyService.addRoom(i + "04", 120.0, floor);
        }
        Building building4 = propertyService.addBuilding(4, 28, community);
        for (int i = 1; i <= building4.getChildCount(); i++) {
            Floor floor = building4.getFloor(i);
            propertyService.addRoom(i + "01", 90.0, floor);
            propertyService.addRoom(i + "02", 90.0, floor);
            propertyService.addRoom(i + "03", 120.0, floor);
            propertyService.addRoom(i + "04", 120.0, floor);
        }
        Building building5 = propertyService.addBuilding(5, 28, community);
        for (int i = 1; i <= building5.getChildCount(); i++) {
            Floor floor = building5.getFloor(i);
            propertyService.addRoom(i + "01", 90.0, floor);
            propertyService.addRoom(i + "02", 90.0, floor);
            propertyService.addRoom(i + "03", 120.0, floor);
            propertyService.addRoom(i + "04", 120.0, floor);
        }
        Building building6 = propertyService.addBuilding(6, 28, community);
        for (int i = 1; i <= building6.getChildCount(); i++) {
            Floor floor = building6.getFloor(i);
            propertyService.addRoom(i + "01", 84.0, floor);
            propertyService.addRoom(i + "02", 84.0, floor);
            propertyService.addRoom(i + "03", 84.0, floor);
            propertyService.addRoom(i + "04", 84.0, floor);
            propertyService.addRoom(i + "05", 84.0, floor);
            propertyService.addRoom(i + "06", 84.0, floor);
        }
        Building building7 = propertyService.addBuilding(7, 28, community);
        for (int i = 1; i <= building7.getChildCount(); i++) {
            Floor floor = building7.getFloor(i);
            propertyService.addRoom(i + "01", 84.0, floor);
            propertyService.addRoom(i + "02", 84.0, floor);
            propertyService.addRoom(i + "03", 84.0, floor);
            propertyService.addRoom(i + "04", 84.0, floor);
            propertyService.addRoom(i + "05", 84.0, floor);
            propertyService.addRoom(i + "06", 84.0, floor);
        }
        staffService.addDirector("director", "123", "五缘公寓物业主任", "18688888888", "pumpking2014@126.com", community);
        propertyService.initialDefaultDevice(community, null);
        return SUCCESS;
    }

}
