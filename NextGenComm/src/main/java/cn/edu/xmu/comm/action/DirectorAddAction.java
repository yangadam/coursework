package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.commons.annotation.Required;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.service.PropertyService;
import cn.edu.xmu.comm.service.StaffService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 12/30/2014 0030
 */
@Controller
public class DirectorAddAction extends ActionSupport {

    @Resource
    private StaffService staffService;

    @Resource
    private PropertyService propertyService;

    private String username;
    private String password;
    private String name;
    private String commName;

    @Override
    @Required(name = "admin")
    public String execute() {
        Community community = propertyService.getCommunity(commName);
        staffService.addDirector(username, password, name, community);
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

    public String getCommName() {
        return commName;
    }

    public void setCommName(String commName) {
        this.commName = commName;
    }

}
