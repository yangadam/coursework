package cn.edu.xmu.comm.action.system;

import cn.edu.xmu.comm.commons.utils.Constants;
import cn.edu.xmu.comm.commons.utils.CookieUtils;
import cn.edu.xmu.comm.commons.utils.SessionUtils;
import cn.edu.xmu.comm.entity.User;
import cn.edu.xmu.comm.service.SystemService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();

        String token = CookieUtils.getCookie(request, Constants.APP_NAME);
        if (token == null) {
            return LOGIN;
        }
        User user = systemService.rememberMeLogin(token);
        if (user == null) {
            CookieUtils.getCookie(request, response, Constants.APP_NAME);
            return LOGIN;
        }

        SessionUtils.putUser(user);
        SessionUtils.putCommunity(user.getCommunity());
        return user.getType();
    }

}
