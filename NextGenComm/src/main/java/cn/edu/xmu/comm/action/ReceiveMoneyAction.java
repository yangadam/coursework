package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.service.FinanceService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yiu-Wah WONG on 2015/1/7.
 *
 * @version 2014/1/7 0008
 */
@Controller
public class ReceiveMoneyAction extends ActionSupport {

    @Resource
    private FinanceService financeService;

    private Map<String, Object> data;

    private Integer ownerId;

    public String execute() {
        financeService.charge(ownerId);
        data = new HashMap<String, Object>();
        data.put("success", "success");
        return SUCCESS;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

}
