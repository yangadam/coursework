package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.service.PropertyService;
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
public class OwnerChange extends ActionSupport {

    @Resource
    private PropertyService propertyService;

}
