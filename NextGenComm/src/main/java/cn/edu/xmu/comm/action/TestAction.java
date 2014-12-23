package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.commons.exception.DifferentCommunityException;
import cn.edu.xmu.comm.entity.Building;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Floor;
import cn.edu.xmu.comm.entity.Room;
import cn.edu.xmu.comm.service.FinanceService;
import cn.edu.xmu.comm.service.PropertyService;
import cn.edu.xmu.comm.service.SystemService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * 测试用的Action
 *
 * @author Mengmeng Yang
 * @version 12/24/2014
 */
@Controller
public class TestAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    @Resource
    private SystemService systemService;

    @Resource
    private FinanceService financeService;

    @Override
    public String execute() {
        Community community = propertyService.getCommunityByName("五缘公寓");
        Building building = propertyService.getBuildingByNo(1, community);
        Floor floor = propertyService.getFloorByNo(1, building);
        Room room = propertyService.getRoomByNo("102", floor);
        try {
            propertyService.addOwner("lyj", "123", "陆垚杰", room);
        } catch (DifferentCommunityException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

}
