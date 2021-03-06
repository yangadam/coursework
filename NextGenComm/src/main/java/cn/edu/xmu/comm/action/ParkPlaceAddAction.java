package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.commons.annotation.Required;
import cn.edu.xmu.comm.commons.utils.Constants;
import cn.edu.xmu.comm.entity.ParkingLot;
import cn.edu.xmu.comm.service.ParkingService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by Roger on 2015/1/3 0003.
 *
 * @version 2015/1/3 0003
 */
@Controller
public class ParkPlaceAddAction extends ActionSupport {

    @Resource
    private ParkingService parkingService;

    private String position;

    private String type;

    @Override
    @Required(name = "director,clerk")
    public String execute() {
        ParkingLot parkingLot;
        if (type.equals(Constants.RENT))
            parkingLot = parkingService.getRentParkingLot();
        else
            parkingLot = parkingService.getTempParkingLot();
        parkingService.addParkingPlace(parkingLot.getId(), position);
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
