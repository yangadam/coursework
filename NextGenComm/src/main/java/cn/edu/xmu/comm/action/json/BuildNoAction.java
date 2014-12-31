package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.commons.utils.Constants;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.service.PropertyService;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionContext;
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
public class BuildNoAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    private Map<String, Object> data;

    @Override
    public String execute() {
        Community community = (Community) ActionContext.getContext().getSession().get(Constants.COMMUNITY);
        List<Integer> noList = propertyService.getBuildingNos(community);
        data = new HashMap<String, Object>();
        JSONArray jsonArray = new JSONArray();
        for (Integer no : noList) {
            jsonArray.add(no);
        }
        data.put("no", jsonArray);
        return SUCCESS;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
