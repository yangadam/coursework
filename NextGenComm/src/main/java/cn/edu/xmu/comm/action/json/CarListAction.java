package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.entity.Car;
import cn.edu.xmu.comm.service.ParkingService;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yiu-Wah WONG
 * @version 2015/1/4
 */
@Controller
public class CarListAction extends ActionSupport {

    @Resource
    private ParkingService parkingService;

    private Map<String, Object> data;

    @Override
    public String execute() {
        List<Car> cars = parkingService.getAllCars();
        int i = 1;
        JSONArray aaData = new JSONArray();
        for (Car car : cars) {
            JSONArray row = new JSONArray();
            row.add(i++);
            row.add(car.getLicense());
            row.add(car.getOwner().getName());
            row.add(car.getId());
            row.add(car.getId());
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
