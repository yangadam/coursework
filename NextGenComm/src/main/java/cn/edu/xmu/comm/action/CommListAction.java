package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.service.PropertyService;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 小区管理Action
 *
 * @author Mengmeng Yang
 * @version 12/28/2014 0028
 */
@Controller
public class CommListAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    private Map<String, Object> data;
    private String commName;

    @SuppressWarnings("unchecked")
    public String list() throws Exception {
        List<Community> communities = propertyService.getAllCommunities();
        data = new HashMap<String, Object>();
        data.put("iTotalRecords", 1);
        data.put("iTotalDisplayRecords", 1);
        JSONArray aaData = new JSONArray();
        int i = 1;
        for (Community community : communities) {
            JSONArray row = new JSONArray();
            row.add(i++);
            row.add(community.getName());
            row.add(community.getChildCount());
            row.add(community.getId());
            row.add(community.getId());
            aaData.add(row);
        }
        data.put("aaData", aaData);
        return SUCCESS;
    }

    public String add() {
        propertyService.addCommunity(commName);
        return SUCCESS;
    }

    public String delete() {
        //propertyService.delCommunity(commId);
        return SUCCESS;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getCommName() {
        return commName;
    }

    public void setCommName(String commName) {
        this.commName = commName;
    }

}
