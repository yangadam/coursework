package cn.edu.xmu.comm.action.json;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

/**
 * Created by Roger on 2015/1/3 0003.
 * @
 */
@Controller
public class ToTempParkingRegisteAction extends ActionSupport {

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
