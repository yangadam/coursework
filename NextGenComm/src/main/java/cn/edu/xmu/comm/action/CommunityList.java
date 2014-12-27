package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.service.PropertyService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Yiu-Wah WONG on 2014/12/19.
 */
@Controller
public class CommunityList extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    private List<Community> communities;

    public String execute() {
        communities = propertyService.getAllCommunities();
        return SUCCESS;
    }

    public List<Community> getCommunities() {
        return communities;
    }

    public void setCommunities(List<Community> communities) {
        this.communities = communities;
    }
}
