package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.service.ParkingService;
import cn.edu.xmu.comm.service.PropertyService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Roger on 2015/1/3 0003.
 *
 * @
 */
@Controller
public class TempParkBillAddAction extends ActionSupport {

    @Resource
    private ParkingService parkingService;

    @Resource
    private PropertyService propertyService;

    private Integer ownerId;

    private String license;

    private Map<String, Object> data;

    public String execute() {
        try {
            license = URLDecoder.decode(license, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        data = new HashMap<String, Object>();
        Boolean hasFreeParkPlace = parkingService.hasFreeTempParkPlace();
        Boolean hasOwner = propertyService.hasOwner(ownerId);
        data.put("hasFreeParkPlace", hasFreeParkPlace ? "true" : "false");
        data.put("hasOwner", hasOwner ? "true" : "false");
        if (hasFreeParkPlace && hasOwner) {
            Integer parkBillId = parkingService.addParkBill(ownerId, license).getId();
            data.put("parkBillId", parkBillId.toString());
        }
        return SUCCESS;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
