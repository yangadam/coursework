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
public class CommunityAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    private Map<String, Object> data;

    private Integer commId;

    private String commName;

    @SuppressWarnings("unchecked")
    public String list() throws Exception {
        List<Community> communities = propertyService.getAllCommunities();
        data = new HashMap<String, Object>();
        data.put("iTotalRecords", 1);
        data.put("iTotalDisplayRecords", 1);
        JSONArray aaData = new JSONArray();
        for (Community community : communities) {
            JSONArray row = new JSONArray();
            row.add(community.getName());
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
        propertyService.delCommunity(commId);
        return SUCCESS;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Integer getCommId() {
        return commId;
    }

    public void setCommId(Integer commId) {
        this.commId = commId;
    }

    public String getCommName() {
        return commName;
    }

    public void setCommName(String commName) {
        this.commName = commName;
    }

}
