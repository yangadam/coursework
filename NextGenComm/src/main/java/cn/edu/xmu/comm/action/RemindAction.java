package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.commons.exception.MailException;
import cn.edu.xmu.comm.commons.utils.MailUtils;
import cn.edu.xmu.comm.service.FinanceService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by Yiu-Wah WONG on 2015/1/4.
 */
@Controller
public class RemindAction extends ActionSupport {

    @Resource
    private FinanceService financeService;

    private Integer ownerId;

    @Override
    public String execute() {
        try {
            financeService.sendOverDueMail(ownerId, new MailUtils());
        } catch (MailException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
}
