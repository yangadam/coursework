package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.commons.annotation.Required;
import cn.edu.xmu.comm.entity.ParkBill;
import cn.edu.xmu.comm.service.CarService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by Yiu-Wah WONG on 2014/12/31.
 */
@Controller
public class TempParkBillDeleteAction extends ActionSupport {

    @Resource
    private CarService carService;

    private Integer parkBillId;

    private ParkBill parkBill;

    @Override
    @Required(name = "guard")
    public String execute() {
        carService.finishParkBill(parkBillId);
        return SUCCESS;
    }

    public Integer getParkBillId() {
        return parkBillId;
    }

    public void setParkBillId(Integer parkBillId) {
        this.parkBillId = parkBillId;
    }

    public ParkBill getParkBill() {
        return parkBill;
    }

    public void setParkBill(ParkBill parkBill) {
        this.parkBill = parkBill;
    }
}
