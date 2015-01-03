package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.service.CarService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Roger on 2014/12/30 0030.
 */
@Controller
public class TempParkingRegisteAction extends ActionSupport {

    @Resource
    CarService carService;

    private Map<String, Object> data;

    private String license;

    @Override
    public String execute() {
        try {
            license = URLDecoder.decode(license, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        data = new HashMap<String, Object>();
        if (carService.isRentCar(license))
            data.put("TYPE", "RENT");
        else
            data.put("TYPE", "TEMP");
        return SUCCESS;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

}
