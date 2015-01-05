package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.entity.Room;
import cn.edu.xmu.comm.service.PropertyService;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yiu-Wah WONG on 2015/1/1.
 *
 * @author yaohua
 */
@Controller
public class RoomListAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    private Map<String, Object> data;

    private Integer floorId;

    @Override
    public String execute() {
        List<Room> roomList = propertyService.getAllRooms(floorId);
        JSONArray aaData = new JSONArray();
        for (Room room : roomList) {
            JSONArray row = new JSONArray();
            row.add(room.getNo());
            row.add(room.getHouseArea());
            if (room.getOwner() != null) {
                row.add(room.getOwner().getName());
            } else {
                row.add("无");
            }
            row.add(room.getId());
            row.add(room.getId());
            aaData.add(row);
        }
        data = new HashMap<String, Object>();
        data.put("iTotalRecords", roomList.size());
        data.put("iTotalDisplayRecords", roomList.size());
        data.put("aaData", aaData);
        return SUCCESS;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Integer getFloorId() {
        return floorId;
    }

    public void setFloorId(Integer floorId) {
        this.floorId = floorId;
    }

}
