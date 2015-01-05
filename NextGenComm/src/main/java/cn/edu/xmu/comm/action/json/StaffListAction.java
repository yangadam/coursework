package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.entity.Staff;
import cn.edu.xmu.comm.service.StaffService;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yiu-Wah WONG on 2015/1/1.
 *
 * @
 */
@Controller
public class StaffListAction extends ActionSupport {

    @Resource
    private StaffService staffService;

    private Map<String, Object> data;

    @Override
    public String execute() {
        List<Staff> staffs = staffService.getAllStaff();
        JSONArray aaData = new JSONArray();
        int i = 1;
        for (Staff staff : staffs) {
            JSONArray row = new JSONArray();
            row.add(i++);
            row.add(staff.getUsername());
            row.add(staff.getName());
            row.add(staff.getStaffType().getPosition());
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
