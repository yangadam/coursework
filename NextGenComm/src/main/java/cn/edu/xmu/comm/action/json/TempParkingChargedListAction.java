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

/**
 * Created by Roger on 2015/1/3 0003.
 *
 * @version 2015/1/3 0003
 */
@Controller
public class TempParkingChargedListAction extends ActionSupport {

    @Resource
    private ParkingService parkingService;

    private Map<String, Object> data;

    @Override
    public String execute() {
        List<ParkingBill> parkingBills = parkingService.getAllFinishParkBill();
        JSONArray aaData = new JSONArray();
        for (ParkingBill parkingBill : parkingBills) {
            JSONArray row = new JSONArray();
            row.add(parkingBill.getLicense());
            row.add(parkingBill.getOwner().getName());
            row.add(parkingBill.getStartTime());
            row.add(parkingBill.getEndTime());
            row.add(parkingBill.getFee());
            row.add(parkingBill.getId());
            aaData.add(row);
        }
        data = new HashMap<String, Object>();
        data.put("iTotalRecords", parkingBills.size());
        data.put("iTotalDisplayRecords", parkingBills.size());
        data.put("aaData", aaData);
        System.out.println(data);
        return SUCCESS;

    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }


}
