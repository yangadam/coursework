package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.entity.User;
import cn.edu.xmu.comm.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * Created by Yummy on 2014/12/14.
 */
@Controller
public class LoginAction extends ActionSupport {

    @Autowired
    private UserService userService;

    private String username;

    private String password;

    private String rememberMe;

    @Override
    public String execute() {
        if (username == null || password == null) {
            return LOGIN;
        }
        User user = userService.getUserByUsername(username);
        if (user == null) {
            addActionError("用户不存在");
            return LOGIN;
        }
        if (!user.checkPassword(password)) {
            addActionError("密码错误");
            return LOGIN;
        }

        Map session = ActionContext.getContext().getSession();
        session.put("USER", user);

        return user.getType();
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

    public String getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(String rememberMe) {
        this.rememberMe = rememberMe;
    }

}
