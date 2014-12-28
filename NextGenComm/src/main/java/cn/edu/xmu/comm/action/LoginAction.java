package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.commons.exception.PasswordIncorrectException;
import cn.edu.xmu.comm.commons.exception.UserNotFoundException;
import cn.edu.xmu.comm.commons.utils.CookieUtils;
import cn.edu.xmu.comm.entity.User;
import cn.edu.xmu.comm.service.SystemService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * 登录Action
 *
 * @author Mengmeng Yang
 * @version 2014-12-14
 */
@Controller
public class LoginAction extends ActionSupport {

    @Resource
    private SystemService systemService;

    private User user;
    private String username;//用户名
    private String password;//密码
    private Boolean rememberMe;//是否记住我

    @Override
    public String execute() {
        try {
            user = systemService.login(username, password);
        } catch (UserNotFoundException e) {
            addActionError("用户不存在");
            return LOGIN;
        } catch (PasswordIncorrectException e) {
            addActionError("密码错误");
            return LOGIN;
        }
        ActionContext.getContext().getSession().put("USER", user);
        checkRememberMe();
        return user.getType();
    }

    private void checkRememberMe() {
        if (rememberMe) {
            String token = systemService.makeRememberMeToken(user);
            CookieUtils.setCookie(ServletActionContext.getResponse(), "COMM", token);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

}
