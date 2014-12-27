package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.service.PropertyService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class CommunityAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    private List<Community> communities;

    private String commName;

    public String list() {
        communities = propertyService.getAllCommunities();
        ActionContext.getContext().getSession().put("COMMS", communities);
        return SUCCESS;
    }

    public String add() {
        propertyService.addCommunity(commName);
        return SUCCESS;
    }

    public List<Community> getCommunities() {
        return communities;
    }

    public void setCommunities(List<Community> communities) {
        this.communities = communities;
    }

    public String getCommName() {
        return commName;
    }

    public void setCommName(String commName) {
        this.commName = commName;
    }
}
