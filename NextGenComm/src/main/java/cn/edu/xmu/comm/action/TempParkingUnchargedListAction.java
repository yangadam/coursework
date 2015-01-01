package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.ParkBill;
import cn.edu.xmu.comm.service.CarService;
import cn.edu.xmu.comm.service.PropertyService;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class TempParkingUnchargedListAction extends ActionSupport {

    @Resource
    CarService carService;

    @Resource
    PropertyService propertyService;

    private Community community;

    private JSONArray aaData;
    private String sEcho;
    private int iTotalRecords;
    private int iTotalDisplayRecords;

    @Override
    public String execute() {

        community = propertyService.getCommunity(1);
        List<ParkBill> parkBills = carService.getAllUnfinishParkBill(community);

        setAaData(new JSONArray());

        // 设置
        iTotalRecords = 1;
        iTotalDisplayRecords = 1;

        for (ParkBill parkBill : parkBills) {
            JSONArray row = new JSONArray();

            row.add(parkBill.getLicense());
            row.add(parkBill.getOwner().getName());

            row.add(parkBill.getStartTime());
            row.add(parkBill.getId());

            aaData.add(row);
        }

        return SUCCESS;

    }


    public JSONArray getAaData() {
        return aaData;
    }

    public void setAaData(JSONArray aaData) {
        this.aaData = aaData;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public int getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public int getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(int iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public String getsEcho() {
        return sEcho;
    }

    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }


}
