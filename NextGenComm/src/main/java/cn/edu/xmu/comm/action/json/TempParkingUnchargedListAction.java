package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.entity.ParkingBill;
import cn.edu.xmu.comm.service.ParkingService;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TempParkingUnchargedListAction extends ActionSupport {

    @Resource
    private ParkingService parkingService;

    private Map<String, Object> data;

    @Override
    public String execute() {
        List<ParkingBill> parkingBills = parkingService.getAllUnfinishParkBill();
        JSONArray aaData = new JSONArray();
        for (ParkingBill parkingBill : parkingBills) {
            JSONArray row = new JSONArray();
            row.add(parkingBill.getLicense());
            row.add(parkingBill.getOwner().getName());
            row.add(parkingBill.getStartTime());
            row.add(parkingBill.getId());
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
