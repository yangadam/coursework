package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.ParkPlace;
import cn.edu.xmu.comm.service.CarService;
import cn.edu.xmu.comm.service.FinanceService;
import cn.edu.xmu.comm.service.PropertyService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

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
    private CarService carService;

    @Override
    public String execute() {
        Community community = propertyService.getCommunity(1);
        ParkPlace parkPlace = new ParkPlace();
        return SUCCESS;
    }
//    @Override
//    public String execute() {
//        Community community = propertyService.getCommunity(1);
//        propertyService.initialDefaultDevice(community, null);
//        Gradient gradient1 = financeService.addGradient(community, BigDecimal.valueOf(0.6), Device.DeviceType.ELECTRICITY);
//        Gradient gradient2 = financeService.addGradient(community, BigDecimal.valueOf(2.8), Device.DeviceType.WATER);
//        Gradient gradient3 = financeService.addGradient(community, new Double[]{20.0}, new BigDecimal[]{BigDecimal.valueOf(0.6), BigDecimal.valueOf(0.7)}, Device.DeviceType.ELECTRICITY);
//        Gradient gradient4 = financeService.addGradient(community, new Double[]{15.0}, new BigDecimal[]{BigDecimal.valueOf(2.8), BigDecimal.valueOf(3.3)}, Device.DeviceType.WATER);
//        Set<Gradient> gradients = community.getGradients();
//
//        for (Gradient gradient : gradients) {
//            if (gradient.getId() == 1||gradient.getId() == 2) {
//                financeService.applyShareGradient(gradient, community);
//            }
//        }
//        for (Gradient gradient : gradients) {
//            if (gradient.getId() == 3||gradient.getId() == 4) {
//                financeService.applyPrivateGradient(gradient, community);
//            }
//        }
//
//        return SUCCESS;
//    }

//    @Override
//    public String execute() {
//        Community community = propertyService.getCommunity(1);
//        Building building2 = community.getBuilding(2);
//        for (int i = 1; i <= building2.getChildCount(); i++) {
//            Floor floor = building2.getFloor(i);
//            propertyService.addRoom(i + "01", 90.0, floor);
//            propertyService.addRoom(i + "02", 90.0, floor);
//            propertyService.addRoom(i + "03", 120.0, floor);
//            propertyService.addRoom(i + "04", 120.0, floor);
//        }
//        Building building3 = community.getBuilding(3);
//        for (int i = 1; i <= building3.getChildCount(); i++) {
//            Floor floor = building3.getFloor(i);
//            propertyService.addRoom(i + "01", 90.0, floor);
//            propertyService.addRoom(i + "02", 90.0, floor);
//            propertyService.addRoom(i + "03", 120.0, floor);
//            propertyService.addRoom(i + "04", 120.0, floor);
//        }
//        Building building4 = community.getBuilding(4);
//        for (int i = 1; i <= building4.getChildCount(); i++) {
//            Floor floor = building4.getFloor(i);
//            propertyService.addRoom(i + "01", 90.0, floor);
//            propertyService.addRoom(i + "02", 90.0, floor);
//            propertyService.addRoom(i + "03", 120.0, floor);
//            propertyService.addRoom(i + "04", 120.0, floor);
//        }
//        Building building5 = community.getBuilding(5);
//        for (int i = 1; i <= building5.getChildCount(); i++) {
//            Floor floor = building5.getFloor(i);
//            propertyService.addRoom(i + "01", 90.0, floor);
//            propertyService.addRoom(i + "02", 90.0, floor);
//            propertyService.addRoom(i + "03", 120.0, floor);
//            propertyService.addRoom(i + "04", 120.0, floor);
//        }
//        Building building6 = community.getBuilding(6);
//        for (int i = 1; i <= building6.getChildCount(); i++) {
//            Floor floor = building6.getFloor(i);
//            propertyService.addRoom(i + "01", 84.0, floor);
//            propertyService.addRoom(i + "02", 84.0, floor);
//            propertyService.addRoom(i + "03", 84.0, floor);
//            propertyService.addRoom(i + "04", 84.0, floor);
//            propertyService.addRoom(i + "05", 84.0, floor);
//            propertyService.addRoom(i + "06", 84.0, floor);
//        }
//        Building building7 = community.getBuilding(7);
//        for (int i = 1; i <= building7.getChildCount(); i++) {
//            Floor floor = building7.getFloor(i);
//            propertyService.addRoom(i + "01", 84.0, floor);
//            propertyService.addRoom(i + "02", 84.0, floor);
//            propertyService.addRoom(i + "03", 84.0, floor);
//            propertyService.addRoom(i + "04", 84.0, floor);
//            propertyService.addRoom(i + "05", 84.0, floor);
//            propertyService.addRoom(i + "06", 84.0, floor);
//        }
//        return SUCCESS;
//    }

}
