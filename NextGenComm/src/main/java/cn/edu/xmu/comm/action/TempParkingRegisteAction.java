package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.service.CarService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by Roger on 2014/12/30 0030.
 */
@Controller
public class TempParkingRegisteAction extends ActionSupport {

    private static String resultRent = "rent";
    private static String resultTemp = "temp";
    @Resource
    CarService carService;
    private String license;

    @Override
    public String execute() {
        if (carService.isRentCar(license))
            return resultRent;
        else
            return resultTemp;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }
}
