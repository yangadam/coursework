package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.dao.CommunityDAO;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.service.PropertyService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by Yiu-Wah WONG on 2014/12/27.
 */

@Controller
public class CommEditAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    @Resource
    private CommunityDAO communityDAO;

    private Community comm;

    public String execute() {

        return SUCCESS;
    }

    public String list() {

        comm = communityDAO.get(comm.getId());

        return SUCCESS;
    }

    public Community getComm() {
        return comm;
    }

    public void setComm(Community comm) {
        this.comm = comm;
    }
}


