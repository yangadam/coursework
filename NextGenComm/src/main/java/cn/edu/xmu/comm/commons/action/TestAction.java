package cn.edu.xmu.comm.commons.action;

import cn.edu.xmu.comm.pms.entity.Building;
import cn.edu.xmu.comm.pms.entity.Community;
import cn.edu.xmu.comm.pms.entity.Floor;
import cn.edu.xmu.comm.pms.entity.Room;
import cn.edu.xmu.comm.pms.service.PropertyService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Yummy on 12/16/2014 0016.
 */
@Controller
public class TestAction extends ActionSupport {

    @Autowired
    private PropertyService propertyService;

    public String execute() {
        Community community = propertyService.getCommunityByName("海韵公寓");
        Building building = community.getBuildingByNo(13);
        Floor floor = building.getFloorByNo(4);
        Room room = newRoom(floor);
        propertyService.addFloor(floor);
        return SUCCESS;
    }

    private Room newRoom(Floor floor) {
        Room room = new Room();
        room.setCommunity(floor.getBuilding().getCommunity());
        room.setBuilding(floor.getBuilding());
        room.setFloor(floor);
        room.setNo("407");
        return null;
    }


}
