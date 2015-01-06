package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.entity.ParkingLot;
import cn.edu.xmu.comm.entity.ParkingPlace;
import cn.edu.xmu.comm.service.ParkingService;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/4/2015 0004
 */
@Controller
public class ParkingListAction extends ActionSupport {

    @Resource
    private ParkingService parkingService;

    private Map<String, Object> data;

    private String type;

    @Override
    public String execute() {
        ParkingLot parkingLot;
        if (type.equals("RENT")) {
            parkingLot = parkingService.getRentParkingLot();
        } else {
            parkingLot = parkingService.getTempParkingLot();
        }
        JSONArray aaData = new JSONArray();
        for (ParkingPlace parkingPlace : parkingLot.getParkingPlaces()) {
            JSONArray row = new JSONArray();
            row.add(parkingPlace.getId());
            row.add(parkingPlace.getPosition());
            row.add(parkingPlace.getParkPlaceStatus().toString());
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
