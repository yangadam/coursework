package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.commons.annotation.Required;
import cn.edu.xmu.comm.entity.ParkingBill;
import cn.edu.xmu.comm.service.ParkingService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by Yiu-Wah WONG on 2014/12/31.
 */
@Controller
public class TempParkBillDeleteAction extends ActionSupport {

    @Resource
    private ParkingService parkingService;

    private Integer parkBillId;

    private ParkingBill parkingBill;

    @Override
    @Required(name = "guard")
    public String execute() {
        parkingService.finishParkBill(parkBillId);
        return SUCCESS;
    }

    public Integer getParkBillId() {
        return parkBillId;
    }

    public void setParkBillId(Integer parkBillId) {
        this.parkBillId = parkBillId;
    }

    public ParkingBill getParkingBill() {
        return parkingBill;
    }

    public void setParkingBill(ParkingBill parkingBill) {
        this.parkingBill = parkingBill;
    }

}
