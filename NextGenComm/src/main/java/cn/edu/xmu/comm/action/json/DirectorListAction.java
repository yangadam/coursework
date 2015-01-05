package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.entity.Director;
import cn.edu.xmu.comm.service.StaffService;
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
public class DirectorListAction extends ActionSupport {

    @Resource
    private StaffService staffService;

    private Map<String, Object> data;

    @Override
    public String execute() {
        List<Director> directors = staffService.getAllDirectors();
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
