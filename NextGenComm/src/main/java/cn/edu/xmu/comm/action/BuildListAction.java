package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.entity.Building;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.service.PropertyService;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionContext;
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
public class BuildListAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    private Map<String, Object> data;
    private Integer buildNo;
    private Integer floorCount;

    public String list() {
        Community community = (Community) ActionContext.getContext().getSession().get("COMMUNITY");
        List<Building> buildings = propertyService.getAllBuildings(community);
        data = new HashMap<String, Object>();
        data.put("iTotalRecords", 1);
        data.put("iTotalDisplayRecords", 1);
        JSONArray aaData = new JSONArray();
        for (Building building : buildings) {
            JSONArray row = new JSONArray();
            row.add(building.getNo());
            row.add(building.getChildCount());
            row.add(building.getId());
            row.add(building.getId());
            aaData.add(row);
        }
        data.put("aaData", aaData);
        return SUCCESS;
    }

    public String add() {
        Community community = (Community) ActionContext.getContext().getSession().get("COMMUNITY");
        propertyService.addBuilding(buildNo, floorCount, community);
        return SUCCESS;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Integer getBuildNo() {
        return buildNo;
    }

    public void setBuildNo(Integer buildNo) {
        this.buildNo = buildNo;
    }

    public Integer getFloorCount() {
        return floorCount;
    }

    public void setFloorCount(Integer floorCount) {
        this.floorCount = floorCount;
    }
}
