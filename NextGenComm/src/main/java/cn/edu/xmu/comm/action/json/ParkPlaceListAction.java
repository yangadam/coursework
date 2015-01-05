package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.entity.ParkingPlace;
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
 * @
 */
@Controller
public class ParkPlaceListAction extends ActionSupport {

    @Resource
    private ParkingService parkingService;

    private Map<String, Object> data;

    @Override
    public String execute() {
        List<ParkingPlace> parkingPlaceList = parkingService.getAllParkingPlace();
        int i = 1;
        JSONArray aaData = new JSONArray();
        for (ParkingPlace parkingPlace : parkingPlaceList) {
            JSONArray row = new JSONArray();
            row.add(i++);
            row.add(parkingPlace.getPosition());
            row.add(parkingPlace.getParkPlaceStatus().toString());
            row.add(parkingPlace.getParkingLot().getName());
            row.add(parkingPlace.getId());
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
