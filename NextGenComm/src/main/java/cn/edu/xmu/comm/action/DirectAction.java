package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.commons.annotation.Required;
import org.springframework.stereotype.Controller;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/4/2015 0004
 */
@Controller
public class DirectAction {

    @Required(name = "admin")
    public String admin() {
        return "success";
    }

    @Required(name = "director")
    public String director() {
        return "success";
    }

    @Required(name = "clerk")
    public String clerk() {
        return "success";
    }

    @Required(name = "guard")
    public String guard() {
        return "success";
    }

    @Required(name = "cashier")
    public String cashier() {
        return "success";
    }

    @Required(name = "owner")
    public String owner() {
        return "success";
    }

}
