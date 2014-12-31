package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Director;
import cn.edu.xmu.comm.service.PropertyService;
import cn.edu.xmu.comm.service.StaffService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 12/30/2014 0030
 */
@Controller
public class DirectorListAction extends ActionSupport {

    @Resource
    private StaffService staffService;

    @Resource
    private PropertyService propertyService;

    private Map<String, Object> data;
    private String username;
    private String password;
    private String name;
    private String commName;

    public String list() {
        List<Director> directors = staffService.getAllDirectors();
        data = new HashMap<String, Object>();
        data.put("iTotalRecords", 1);
        data.put("iTotalDisplayRecords", 1);
        JSONArray aaData = new JSONArray();
        int i = 1;
        for (Director director : directors) {
            JSONArray row = new JSONArray();
            row.add(i++);
            row.add(director.getUsername());
            row.add(director.getName());
            row.add(director.getCommunity().getName());
            row.add(director.getId());
            row.add(director.getId());
            aaData.add(row);
        }
        data.put("aaData", aaData);
        return SUCCESS;
    }

    public String add() {
        Community community = propertyService.getCommunity(commName);
        staffService.addDirector(username, password, name, community);
        return SUCCESS;
    }

    public String commNameList() {
        List<String> nameList = propertyService.getCommunityNames();
        data = new HashMap<String, Object>();
        JSONArray names = new JSONArray();
        for (String name : nameList) {
            names.add(name);
        }
        data.put("names", names);
        return SUCCESS;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommName() {
        return commName;
    }

    public void setCommName(String commName) {
        this.commName = commName;
    }
}
