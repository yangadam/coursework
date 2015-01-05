package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.service.PropertyService;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 楼宇号Action
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
        List<String[]> list = propertyService.getBuildNos();
        data = new HashMap<String, Object>();
        JSONArray ids = new JSONArray();
        JSONArray nos = new JSONArray();
        for (String[] idAndNo : list) {
            ids.add(idAndNo[0]);
            nos.add(idAndNo[1]);
        }
        data.put("id", ids);
        data.put("no", nos);
        return SUCCESS;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

}
