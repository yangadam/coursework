package cn.edu.xmu.comm.action;

import org.springframework.stereotype.Controller;

/**
 * 因为AOP的需要
 *
 * @author Mengmeng Yang
 * @version 12/29/2014 0029
 */
@Controller
public class ActionSupport extends com.opensymphony.xwork2.ActionSupport {

    @Override
    public String execute() {
        return SUCCESS;
    }

}
