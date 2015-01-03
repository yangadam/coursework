package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.commons.utils.Constants;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.service.CarService;
import cn.edu.xmu.comm.service.PropertyService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Roger on 2015/1/3 0003.
 */
@Controller
public class TempParkBillAddAction extends ActionSupport {

    @Resource
    private CarService carService;

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
        Community community = (Community) ActionContext.getContext().getSession().get(Constants.COMMUNITY);
        data = new HashMap<String, Object>();
        Boolean hasFreeParkPlace = carService.hasFreeTempParkPlace(community);
        Boolean hasOwner = propertyService.hasOwner(community, ownerId);
        data.put("hasFreeParkPlace", hasFreeParkPlace ? "true" : "false");
        data.put("hasOwner", hasOwner ? "true" : "false");
        if (hasFreeParkPlace && hasOwner) {
            Integer parkBillId = carService.addParkBill(community, ownerId, license).getId();
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
