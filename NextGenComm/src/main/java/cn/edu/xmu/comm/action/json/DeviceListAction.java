package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.commons.utils.Constants;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Device;
import cn.edu.xmu.comm.service.FinanceService;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/3/2015 0003
 */
@Controller
public class DeviceListAction extends ActionSupport {

    @Resource
    private FinanceService financeService;

    private Map<String, Object> data;

    @Override
    public String execute() throws Exception {
        Community community = (Community) ActionContext.getContext()
                .getSession().get(Constants.COMMUNITY);
        List<Device> devices = financeService.getCanCalculateDevice(community);
        JSONArray aaData = new JSONArray();
        for (Device device : devices) {
            JSONArray row = new JSONArray();
            row.add(device.getNo());
            row.add(device.getCurrentValue());
            row.add(device.getLastValue());
            row.add(device.getType().toString());
            row.add(device.getId());
            aaData.add(row);
        }
        data = new HashMap<String, Object>();
        data.put("iTotalRecords", devices.size());
        data.put("iTotalDisplayRecords", devices.size());
        data.put("aaData", aaData);
        return SUCCESS;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

}
