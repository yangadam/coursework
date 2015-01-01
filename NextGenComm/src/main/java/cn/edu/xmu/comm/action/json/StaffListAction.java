package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.commons.utils.Constants;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Staff;
import cn.edu.xmu.comm.service.StaffService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Yiu-Wah WONG on 2015/1/1.
 */
@Controller
public class StaffListAction extends ActionSupport {

    @Resource
    private StaffService staffService;


    private Map<String, Object> data;

    @Override
    public String execute() {
        Community community = (Community) ActionContext.getContext()
                .getSession().get(Constants.COMMUNITY);

        List<Staff> staffs = staffService.getAll(community);


        return SUCCESS;
    }

}
