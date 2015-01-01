package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.entity.ParkBill;
import cn.edu.xmu.comm.service.CarService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by Yiu-Wah WONG on 2014/12/31.
 */
@Controller
public class TempCarDeleteAction extends ActionSupport {

    @Resource
    private CarService carService;

    private Integer carBillId;

    private ParkBill parkBill;

    public String execute() {
//        carService.finishParkBill(carBillId);
        return SUCCESS;
    }

    public Integer getCarBillId() {
        return carBillId;
    }

    public void setCarBillId(Integer carBillId) {
        this.carBillId = carBillId;
    }

    public ParkBill getParkBill() {
        return parkBill;
    }

    public void setParkBill(ParkBill parkBill) {
        this.parkBill = parkBill;
    }
}
