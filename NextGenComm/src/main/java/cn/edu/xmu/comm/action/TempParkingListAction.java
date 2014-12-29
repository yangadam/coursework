package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.service.CarService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class TempParkingListAction extends ActionSupport {

    @Resource
    CarService carService;

    private Community community;

    @Override
    public String execute() {

        community = community.getCommunity();

        return SUCCESS;
    }


}
