package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.service.PropertyService;
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
public class CommDelAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    private Integer commId;

    @Override
    public String execute() {
//        Community community =propertyService.getCommunity(commId);
//        propertyService.delAllRoom(community);
//        propertyService.delAllOwner(community);
//        propertyService.delAllStaff(community);
        propertyService.delCommunity(commId);
        return SUCCESS;
    }

    public Integer getCommId() {
        return commId;
    }

    public void setCommId(Integer commId) {
        this.commId = commId;
    }
}
