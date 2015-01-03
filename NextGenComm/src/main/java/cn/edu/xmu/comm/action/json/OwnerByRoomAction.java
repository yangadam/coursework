package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.entity.Owner;
import cn.edu.xmu.comm.entity.Room;
import cn.edu.xmu.comm.service.PropertyService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Roger on 2015/1/3 0003.
 */
@Controller
public class OwnerByRoomAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    private Map<String, Object> data;

    private Integer roomId;

    public String execute() {
        Room room = propertyService.getRoom(roomId);
        Owner owner = room.getOwner();
        data = new HashMap<String, Object>();
        data.put("owner_name", owner.getName());
        data.put("community_name", owner.getCommunity().getName());
        data.put("building_num", room.getBuilding().getNo());
        data.put("room_num", room.getFullName());
        data.put("phone_num", owner.getPhoneNumber());
        data.put("owner_id", owner.getId());
        return SUCCESS;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
