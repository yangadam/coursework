package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.commons.utils.Constants;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.service.PropertyService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/2/2015 0002
 */
@Controller
public class OwnerAddAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    private String username;
    private String password;
    private String name;

    @Override
    public String execute() {
        Community community = (Community) ActionContext.getContext()
                .getSession().get(Constants.COMMUNITY);
        propertyService.addOwner(username, password, name, community);
        return SUCCESS;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
