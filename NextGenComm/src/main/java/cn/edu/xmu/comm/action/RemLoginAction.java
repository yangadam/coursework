package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.commons.utils.CookieUtils;
import cn.edu.xmu.comm.entity.User;
import cn.edu.xmu.comm.service.SystemService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * 记住我登录Action
 *
 * @author Mengmeng Yang
 * @version 12/28/2014 0028
 */
@Controller
public class RemLoginAction extends ActionSupport {

    @Resource
    private SystemService systemService;

    @Override
    public String execute() {
        String token = CookieUtils.getCookie(ServletActionContext.getRequest(), "COMM");
        if (token == null) {
            return LOGIN;
        }
        User user = systemService.rememberMeLogin(token);
        if (user == null) {
            CookieUtils.getCookie(ServletActionContext.getRequest(),
                    ServletActionContext.getResponse(), "COMM");
            return LOGIN;
        }
        ActionContext.getContext().getSession().put("USER", user);
        return user.getType();
    }

}
