package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.commons.utils.Constants;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.ParkPlace;
import cn.edu.xmu.comm.entity.ParkingLot;
import cn.edu.xmu.comm.service.CarService;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionContext;
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
public class ParkListAction extends ActionSupport {

    @Resource
    private CarService carService;

    private Map<String, Object> data;

    private String type;

    @Override
    public String execute() {
        Community community = (Community) ActionContext.getContext()
                .getSession().get(Constants.COMMUNITY);
        ParkingLot parkingLot;
        if (type.equals("RENT")) {
            parkingLot = carService.getRentParkingLot(community);
        } else {
            parkingLot = carService.getTempParkingLot(community);
        }
        JSONArray aaData = new JSONArray();
        for (ParkPlace parkPlace : parkingLot.getParkPlaces()) {
            JSONArray row = new JSONArray();
            row.add(parkPlace.getId());
            row.add(parkPlace.getPosition());
            row.add(parkPlace.getParkPlaceStatus());
            aaData.add(row);
        }
        data = new HashMap<String, Object>();
        data.put("iTotalRecords", parkingLot.getParkPlaces().size());
        data.put("iTotalDisplayRecords", parkingLot.getParkPlaces().size());
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
