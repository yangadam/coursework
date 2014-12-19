package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.service.PropertyService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by Yiu-Wah WONG on 2014/12/19.
 */
@Controller
public class CommunityForm extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    private Integer id;

    private Community community;

    public String execute() {
        if (id == null) {
            return INPUT;
        }
        community = propertyService.getCommunity(id);
        return SUCCESS;
    }

    public String save() {
        propertyService.updateCommunity(community);
        return SUCCESS;
    }

    public String remove() {
        propertyService.delCommunity(community);
        return "back";
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
