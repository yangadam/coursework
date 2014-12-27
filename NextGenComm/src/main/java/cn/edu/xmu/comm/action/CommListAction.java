package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.service.PropertyService;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.json.annotations.JSON;
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

    private int count;

    private JSONArray aaData;
    private String sEcho;
    private int iTotalRecords;
    private int iTotalDisplayRecords;


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

       /* JSONArray array = new JSONArray();
        community = propertyService.getCommunity(10);
        array.add(community.getName());
        array.add(community.getId());
        aaData.add(array);

        array = new JSONArray();
        community = propertyService.getCommunity(2);
        array.add(community.getName());
        array.add(community.getId());
        aaData.add(array);*/

        return SUCCESS;
    }

    private String commName;

    public String add() {

        propertyService.addCommunity(commName);
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


    public void setAaData(JSONArray aaData) {
        this.aaData = aaData;
    }

    public JSONArray getAaData() {
        return aaData;
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
}
