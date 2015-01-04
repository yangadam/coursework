package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.commons.annotation.Required;
import cn.edu.xmu.comm.service.FinanceService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Date;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/3/2015 0003
 */
@Controller
public class InputValueAction extends ActionSupport {

    @Resource
    private FinanceService financeService;

    private Integer deviceId;
    private Double curValue;

    @Override
    @Required(name = "director,clerk")
    public String execute() {
        financeService.addDeviceValue(deviceId, new Date(), curValue);
        return SUCCESS;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Double getCurValue() {
        return curValue;
    }

    public void setCurValue(Double curValue) {
        this.curValue = curValue;
    }
}
