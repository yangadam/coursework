package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.commons.exception.PasswordIncorrectException;
import cn.edu.xmu.comm.commons.exception.UserNotFoundException;
import cn.edu.xmu.comm.entity.User;
import cn.edu.xmu.comm.service.SystemService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 车辆实体
 *
 * @author Mengmeng Yang
 * @version 2014-12-14
 */
@Controller
public class LoginAction extends ActionSupport {

    @Resource
    private SystemService systemService;

    private String username;

    private String password;

    @Override
    public String execute() {

        User user = null;
        try {
            user = systemService.login(username, password);
        } catch (UserNotFoundException e) {
            addActionError("用户不存在");
            return LOGIN;
        } catch (PasswordIncorrectException e) {
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

}
