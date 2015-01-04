package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.commons.utils.Constants;
import cn.edu.xmu.comm.entity.Car;
import cn.edu.xmu.comm.entity.Community;
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
 * Created by Yiu-Wah WONG on 2015/1/4.
 */
@Controller
public class CarListAction extends ActionSupport {

    @Resource
    private CarService carService;

    private Map<String, Object> data;

    @Override
    public String execute() {
        Community community = (Community) ActionContext
                .getContext().getSession().get(Constants.COMMUNITY);
        List<Car> cars = carService.getAllCar(community);
        int i = 1;
        JSONArray aaData = new JSONArray();
        for (Car car : cars) {
            JSONArray row = new JSONArray();
            row.add(i++);
            row.add(car.getLicense());
            row.add(car.getOwner().getName());
            row.add(car.getLicense());
            row.add(car.getLicense());
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
