package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.commons.utils.Constants;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.ParkingLot;
import cn.edu.xmu.comm.service.CarService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by Roger on 2015/1/3 0003.
 */
@Controller
public class ParkPlaceAddAction extends ActionSupport {

    @Resource
    private CarService carService;

    private String position;

    private String type;

    @Override
    public String execute() {
        ParkingLot parkingLot;
        Community community = (Community) ActionContext.getContext().getSession().get(Constants.COMMUNITY);
        if (type.equals(Constants.RENT))
            parkingLot = carService.getRentParkingLot(community);
        else
            parkingLot = carService.getTempParkingLot(community);
        carService.addParkPlace(parkingLot.getId(), position);
        return SUCCESS;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String carPosition) {
        this.position = carPosition;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
