package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.service.PropertyService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/6/2015 0006
 */
@Controller
public class CommInfoAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    private String commName;

    @Override
    public String execute(){

        return SUCCESS;
    }
}
