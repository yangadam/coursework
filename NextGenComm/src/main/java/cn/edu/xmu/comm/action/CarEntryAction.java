package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.service.CarService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by Roger on 2014/12/28 0028.
 * 车辆进入Action
 */
@Controller
public class CarEntryAction extends ActionSupport {

    @Resource
    CarService carService;

    private String license;

    @Override
    public String execute() {
        Boolean isRentCarInCommunity = carService.isRentCar("浙AH2828");
        if (isRentCarInCommunity)
            return "rent";
        else
            return "temp";
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

}
