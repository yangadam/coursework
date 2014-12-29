package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.dao.CommunityDAO;
import cn.edu.xmu.comm.entity.Building;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.service.PropertyService;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Yiu-Wah WONG on 2014/12/27.
 *
 * @author yaohua
 */
@Controller
public class BuildingListAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;
    @Resource
    private CommunityDAO communityDAO;

    private Community comm;
    private Building building;

    private JSONArray aaData;
    private String sEcho;
    private int iTotalRecords;
    private int iTotalDisplayRecords;
    private String buildingName;
    private Integer buildingNo;

    public String execute() {

        comm = communityDAO.get(comm.getId());
//        comm = propertyService.getCommunity(comm.getId());
        setAaData(new JSONArray());

        // 设置
        iTotalRecords = 1;
        iTotalDisplayRecords = 1;

        List<Building> buildings = propertyService.getAllBuildings(comm);

        for (Building building : buildings) {
            JSONArray row = new JSONArray();

            row.add(building.getName());
            row.add(building.getId());
            row.add(building.getNo());

            row.add(building.getId());
            row.add(building.getId());
            row.add(building.getId());

            aaData.add(row);
        }

        return SUCCESS;
    }

    public String fetchComm() {

        comm = propertyService.getCommunity(comm.getId());
        return SUCCESS;
    }

    public String add() {

        comm = propertyService.getCommunity(comm.getId());

        try {
            propertyService.addBuilding(buildingNo, buildingName, comm);
        } catch (ConstraintViolationException e) {

            return INPUT;
        }

        return SUCCESS;
    }

    public String edit() {

        building = propertyService.getBuilding(building.getId());

        return SUCCESS;
    }

    public Community getComm() {
        return comm;
    }

    public void setComm(Community comm) {
        this.comm = comm;
    }

    public JSONArray getAaData() {
        return aaData;
    }

    public void setAaData(JSONArray aaData) {
        this.aaData = aaData;
    }

    public int getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public int getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public String getsEcho() {
        return sEcho;
    }

    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    /*Get and Set Building Info*/

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Integer getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(Integer buildingNo) {
        this.buildingNo = buildingNo;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
