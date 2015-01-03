package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.commons.annotation.Required;
import cn.edu.xmu.comm.commons.utils.Constants;
import cn.edu.xmu.comm.entity.Owner;
import cn.edu.xmu.comm.service.FinanceService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/4/2015 0004
 */
@Controller
public class MakePaymentAction extends ActionSupport {

    @Resource
    private FinanceService financeService;

    @Override
    @Required(name = "owner")
    public String execute() {
        Owner owner = (Owner) ActionContext.getContext()
                .getSession().get(Constants.USER);
        financeService.makePayment(owner.getId());
        return SUCCESS;
    }

}
