package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.commons.annotation.Required;
import cn.edu.xmu.comm.entity.Floor;
import cn.edu.xmu.comm.service.PropertyService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by Yiu-Wah WONG on 2015/1/1.
 * @
 */
@Controller
public class RoomAddAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    private String roomNo;

    private Double houseArea;

    private Integer floorId;

    @Override
    @Required(name = "director,clerk")
    public String execute() {
        Floor floor = propertyService.getFloor(floorId);
        propertyService.addRoom(roomNo, houseArea, floor);
        return SUCCESS;
    }

    public Integer getFloorId() {
        return floorId;
    }

    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }

    public Double getHouseArea() {
        return houseArea;
    }

    public void setHouseArea(Double houseArea) {
        this.houseArea = houseArea;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }
}
