package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.service.CarService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by Roger on 2015/1/3 0003.
 *
 */
@Controller
public class ToTempParkingRegisteAction extends ActionSupport {

    @Resource
    private CarService carService;

    private String license;

    public String execute() {
        return SUCCESS;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

}
