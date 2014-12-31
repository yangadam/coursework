package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.service.PropertyService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * 小区管理Action
 *
 * @author Mengmeng Yang
 * @version 12/28/2014 0028
 */
@Controller
public class CommAddAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    private String commName;

    @Override
    public String execute() {
        propertyService.addCommunity(commName);
        return SUCCESS;
    }

    public String getCommName() {
        return commName;
    }

    public void setCommName(String commName) {
        this.commName = commName;
    }

}
