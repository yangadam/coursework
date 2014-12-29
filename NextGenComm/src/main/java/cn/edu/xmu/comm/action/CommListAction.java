package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.dao.CommunityDAO;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.service.PropertyService;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.json.annotations.JSON;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Yiu-Wah WONG on 2014/12/24.
 */

@Controller
public class CommListAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    @Resource
    private CommunityDAO communityDAO;

    private Community comm;

    private int count;

    private JSONArray aaData;
    private String sEcho;
    private int iTotalRecords;
    private int iTotalDisplayRecords;
    private String commName;

    public String execute() {

        setAaData(new JSONArray());

        // 设置
        iTotalRecords = 1;
        iTotalDisplayRecords = 1;

        List<Community> communities = propertyService.getAllCommunities();

        for (Community community : communities) {
            JSONArray row = new JSONArray();

            row.add(community.getId());
            row.add(community.getName());

            row.add(community.getId());
            row.add(community.getId());
            row.add(community.getId());

            aaData.add(row);
        }

        return SUCCESS;
    }

    public String add() {

        try {
            propertyService.addCommunity(commName);
        } catch (ConstraintViolationException e) {

            return INPUT;
        }

        return SUCCESS;
    }

    public String edit() {

        comm = communityDAO.get(comm.getId());

        return SUCCESS;
    }


    public String getSEcho() {
        return sEcho;
    }

    public void setEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    @JSON(name = "iTotalRecords")
    public int getITotalRecords() {
        return iTotalRecords;
    }

    public void setITotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    @JSON(name = "iTotalDisplayRecords")
    public int getITotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setITotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public JSONArray getAaData() {
        return aaData;
    }

    public void setAaData(JSONArray aaData) {
        this.aaData = aaData;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCommName() {
        return commName;
    }

    public void setCommName(String commName) {
        this.commName = commName;
    }

    public Community getComm() {
        return comm;
    }

    public void setComm(Community comm) {
        this.comm = comm;
    }
}