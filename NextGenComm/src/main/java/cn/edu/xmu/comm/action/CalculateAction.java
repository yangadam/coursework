package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.commons.exception.DeviceException;
import cn.edu.xmu.comm.commons.utils.Constants;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.service.FinanceService;
import com.opensymphony.xwork2.ActionContext;
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
    public String execute() {
        Community community = (Community) ActionContext.getContext().getSession().get(Constants.COMMUNITY);
        try {
            financeService.generateBill(community);
        } catch (DeviceException e) {
            e.printStackTrace();
            return INPUT;
        }
        return SUCCESS;
    }

}
