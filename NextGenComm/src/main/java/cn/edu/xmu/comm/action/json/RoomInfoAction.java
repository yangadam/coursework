package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.entity.Room;
import cn.edu.xmu.comm.service.PropertyService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/2/2015 0002
 */
@Controller
public class RoomInfoAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    private Map<String, Object> data;

    private Integer roomId;

    @Override
    public String execute() {
        data = new HashMap<String, Object>();
        if (roomId == -1) {
            return SUCCESS;
        }
        Room room = propertyService.getRoom(roomId);
        data = new HashMap<String, Object>();
        data.put("area", room.getHouseArea());
        if (room.getOwner() != null) {
            data.put("owner.id", room.getOwner().getId());
            data.put("owner.name", room.getOwner().getName());
            data.put("owner.username", room.getOwner().getUsername());
        }
        return SUCCESS;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

}
