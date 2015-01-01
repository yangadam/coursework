package cn.edu.xmu.comm.action.json;

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
 * description
 *
 * @author Mengmeng Yang
 * @version 12/31/2014 0031
 */
@Controller
public class CommListAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    private Map<String, Object> data;

    @Override
    public String execute() throws Exception {
        List<Community> communities = propertyService.getAllCommunities();
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
        data = new HashMap<String, Object>();
        data.put("iTotalRecords", communities.size());
        data.put("iTotalDisplayRecords", communities.size());
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
