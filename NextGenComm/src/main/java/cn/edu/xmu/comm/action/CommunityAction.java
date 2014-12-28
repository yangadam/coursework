package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.entity.*;
import cn.edu.xmu.comm.service.CarService;
import cn.edu.xmu.comm.service.PropertyService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 12/22/2014 0022
 */
@Controller
public class CommunityAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    @Resource
    private CarService carService;

    private Community community;

    public String execute() {
        /*Community community = propertyService.getCommunityByName("五缘公寓");
        Building building = community.getBuilding(1);
        Floor floor = building.getFloor(1);
        for (Room room : floor.getRoomList()) {
            System.out.println(room.getFullName());
        }*/
        /*
        Community community = propertyService.getCommunityByName("五缘公寓");
        int a = carService.getSizeOfUnpaidBIll(community);
        */
        Community community = propertyService.getCommunityByName("五缘公寓");
        propertyService.addBuilding(1, community);
        return SUCCESS;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }
}
