package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.entity.Building;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.service.PropertyService;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 楼宇Action
 *
 * @author Mengmeng Yang
 * @version 12/28/2014 0028
 */
@Controller
public class BuildingAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    private Map<String, Object> data;

    private Community community;
    private Integer buildId;
    private Integer buildNo;
    private String buildName;
    private Integer start;
    private Integer length;

    @Override
    public String execute() {
        community = propertyService.getCommunity(community.getId());
        return SUCCESS;
    }

    @SuppressWarnings("unchecked")
    public String list() throws Exception {
        community = propertyService.getCommunity(community.getId());
        data = new HashMap<String, Object>();
        data.put("iTotalRecords", 1);
        data.put("iTotalDisplayRecords", 1);
        JSONArray aaData = new JSONArray();
        for (Building building : community.getBuildingList()) {
            JSONArray row = new JSONArray();
            row.add(building.getName());
            row.add(building.getNo());
            aaData.add(row);
        }
        data.put("aaData", aaData);
        return SUCCESS;
    }

    public String add() {
        propertyService.addBuilding(buildNo, buildName, community);
        return SUCCESS;
    }

    public String addBatch() {
        propertyService.addBuilding(start, length, community);
        return SUCCESS;
    }

    public String delete() {
        propertyService.delBuilding(community.getBuilding(buildId));
        return SUCCESS;
    }

    //region Getters and Setters
    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public Integer getBuildId() {
        return buildId;
    }

    public void setBuildId(Integer buildId) {
        this.buildId = buildId;
    }

    public Integer getBuildNo() {
        return buildNo;
    }

    public void setBuildNo(Integer buildNo) {
        this.buildNo = buildNo;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
    //endregion
}
