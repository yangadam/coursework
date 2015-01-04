package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.commons.utils.Constants;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Owner;
import cn.edu.xmu.comm.service.FinanceService;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yiu-Wah WONG on 2015/1/4.
 */
@Controller
public class ArrearageListAction extends ActionSupport {

    @Resource
    private FinanceService financeService;

    private Map<String, Object> data;

    @Override
    public String execute() {

        Community community = (Community) ActionContext
                .getContext().getSession().get(Constants.COMMUNITY);
        List<Owner> owners = financeService.getOwnerWithOverDue(community);
        int i = 1;
        JSONArray aaData = new JSONArray();
        for (Owner owner : owners) {
            JSONArray row = new JSONArray();
            row.add(i++);
            row.add(owner.getName());
            row.add(owner.getRoomList());
            row.add(owner.getId());

            aaData.add(row);
        }
        data = new HashMap<String, Object>();
        data.put("aaData", aaData);

        return SUCCESS;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
