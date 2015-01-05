package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.service.FinanceService;
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
public class DeviceCountAction extends ActionSupport {

    @Resource
    private FinanceService financeService;

    private Map<String, Object> data;

    @Override
    public String execute() {
        Long inputCount = financeService.getInputedDeviceCount();
        Long total = financeService.getDeviceCount();
        data = new HashMap<String, Object>();
        data.put("inputCount", inputCount);
        data.put("total", total);
        return SUCCESS;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

}
