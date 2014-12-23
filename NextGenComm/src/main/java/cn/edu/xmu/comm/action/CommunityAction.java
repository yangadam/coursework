package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.entity.Building;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Floor;
import cn.edu.xmu.comm.entity.Room;
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

    private Community community;

    public String execute() {
        Community community = propertyService.getCommunityByName("五缘公寓");
        Building building = community.getBuilding(1);
        Floor floor = building.getFloor(1);
        for (Room room : floor.getRoomList()) {
            System.out.println(room.getFullName());
        }
        return SUCCESS;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }
}
