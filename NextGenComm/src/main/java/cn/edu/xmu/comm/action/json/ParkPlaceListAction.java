package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.commons.utils.Constants;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.ParkPlace;
import cn.edu.xmu.comm.service.CarService;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Roger on 2015/1/3 0003.
 */
@Controller
public class ParkPlaceListAction extends ActionSupport {

    @Resource
    private CarService carService;

    private Map<String, Object> data;

    @Override
    public String execute() {
        Community community = (Community) ActionContext.getContext().getSession().get(Constants.COMMUNITY);
        List<ParkPlace> parkPlaceList = carService.getAllParkPlace(community);
        int i = 1;
        JSONArray aaData = new JSONArray();
        for (ParkPlace parkPlace : parkPlaceList) {
            JSONArray row = new JSONArray();
            row.add(i++);
            row.add(parkPlace.getPosition());
            if (parkPlace.getParkPlaceStatus().equals(ParkPlace.ParkPlaceStatus.FREE))
                row.add("可用");
            else if (parkPlace.getParkPlaceStatus().equals(ParkPlace.ParkPlaceStatus.LOCK))
                row.add("锁定");
            else if (parkPlace.getParkPlaceStatus().equals(ParkPlace.ParkPlaceStatus.RENT))
                row.add("已租用");
            row.add(parkPlace.getParkingLot().getName());
            row.add(parkPlace.getId());
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
