package cn.edu.xmu.comm.action.system;

import cn.edu.xmu.comm.commons.utils.Constants;
import cn.edu.xmu.comm.commons.utils.CookieUtils;
import cn.edu.xmu.comm.entity.User;
import cn.edu.xmu.comm.service.SystemService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Map;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 12/28/2014 0028
 */
@Controller
public class LogoutAction extends ActionSupport {

    @Resource
    private SystemService systemService;

    @Override
    public String execute() {
        CookieUtils.getCookie(ServletActionContext.getRequest(),
                ServletActionContext.getResponse(), Constants.APP_NAME);
        Map<String, Object> session = ActionContext.getContext().getSession();
        systemService.logout((User) session.get(Constants.USER));
        session.remove(Constants.USER);
        return LOGIN;
    }

}
