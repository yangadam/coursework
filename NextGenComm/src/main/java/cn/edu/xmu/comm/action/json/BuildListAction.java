package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.entity.Building;
import cn.edu.xmu.comm.service.PropertyService;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 楼宇列表Action
 *
 * @author Mengmeng Yang
 * @version 12/31/2014 0031
 */
@Controller
public class BuildListAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    private Map<String, Object> data;

    @Override
    public String execute() {
        List<Building> buildings = propertyService.getAllBuildings();
        JSONArray aaData = new JSONArray();
        for (Building building : buildings) {
            JSONArray row = new JSONArray();
            row.add(building.getNo());
            row.add(building.getChildCount());
            row.add(building.getId());
            row.add(building.getId());
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
