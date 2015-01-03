package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.commons.utils.Constants;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Device;
import cn.edu.xmu.comm.service.FinanceService;
import cn.edu.xmu.comm.service.PropertyService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/3/2015 0003
 */
@Controller
public class DeviceValueAction extends ActionSupport {

    @Resource
    private FinanceService financeService;

    @Resource
    private PropertyService propertyService;

    private Map<String, Object> data;

    private String deviceNo;

    @Override
    public String execute() {
        Community community = (Community) ActionContext.getContext().
                getSession().get(Constants.COMMUNITY);
        Device device = financeService.getDeviceByNo(community, deviceNo);
        data = new HashMap<String, Object>();
        data.put("id", device.getId());
        data.put("lastValue", device.getCurrentValue());
        data.put("type", device.getType());
        return SUCCESS;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }
}
