package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Gradient;
import cn.edu.xmu.comm.service.FinanceService;
import cn.edu.xmu.comm.service.PropertyService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Set;

/**
 * 测试用的Action
 *
 * @author Mengmeng Yang
 * @version 12/24/2014
 */
@Controller
public class TestAction extends ActionSupport {

    @Resource
    private FinanceService financeService;

    @Resource
    private PropertyService propertyService;

    @Override
    public String execute() {
        Community community = propertyService.getCommunityByName("五缘公寓");
        Set<Gradient> gradients = community.getGradients();
        financeService.applyShareGradient(gradients.toArray(new Gradient[gradients.size()])[1], community);
        return SUCCESS;
    }

}
