package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.commons.utils.Constants;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.ParkBill;
import cn.edu.xmu.comm.service.CarService;
import cn.edu.xmu.comm.service.PropertyService;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TempParkingUnchargedListAction extends ActionSupport {

    @Resource
    private CarService carService;

    @Resource
    private PropertyService propertyService;

    private Map<String, Object> data;

    @Override
    public String execute() {
        Community community = (Community) ActionContext.getContext().getSession().get(Constants.COMMUNITY);
        List<ParkBill> parkBills = carService.getAllUnfinishParkBill(community);
        JSONArray aaData = new JSONArray();
        for (ParkBill parkBill : parkBills) {
            JSONArray row = new JSONArray();
            row.add(parkBill.getLicense());
            row.add(parkBill.getOwner().getName());
            row.add(parkBill.getStartTime());
            row.add(parkBill.getId());
            aaData.add(row);
        }
        data = new HashMap<String, Object>();
        data.put("iTotalRecords", parkBills.size());
        data.put("iTotalDisplayRecords", parkBills.size());
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
