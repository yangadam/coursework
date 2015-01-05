package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.commons.annotation.Required;
import cn.edu.xmu.comm.commons.exception.DeviceException;
import cn.edu.xmu.comm.service.FinanceService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/3/2015 0003
 */
@Controller
public class CalculateAction extends ActionSupport {

    @Resource
    private FinanceService financeService;

    @Override
    @Required(name = "director,clerk")
    public String execute() {
        try {
            financeService.generateAllBill();
        } catch (DeviceException e) {
            e.printStackTrace();
            return INPUT;
        }
        return SUCCESS;
    }

}
