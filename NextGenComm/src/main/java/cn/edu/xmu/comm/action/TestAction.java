package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.service.FinanceService;
import cn.edu.xmu.comm.service.PropertyService;
import cn.edu.xmu.comm.service.SystemService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * 测试用的Action
 *
 * @author Mengmeng Yang
 * @version 12/24/2014
 */
@Controller
public class TestAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    @Resource
    private SystemService systemService;

    @Resource
    private FinanceService financeService;

    @Override
    public String execute() {
        return SUCCESS;
    }

}
