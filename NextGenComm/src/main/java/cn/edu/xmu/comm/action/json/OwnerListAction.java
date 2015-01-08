package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.entity.Owner;
import cn.edu.xmu.comm.entity.Room;
import cn.edu.xmu.comm.service.PropertyService;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/2/2015 0002
 */
@Controller
public class OwnerListAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    private Map<String, Object> data;

    @Override
    public String execute() {
        List<Owner> owners = propertyService.getAllOwners();
        int i = 1;
        JSONArray aaData = new JSONArray();
        for (Owner owner : owners) {
            JSONArray row = new JSONArray();
            row.add(i++);
            row.add(owner.getUsername());
            row.add(owner.getName());
            if (owner.getRooms().size() == 0) {
                row.add("未入住");
            } else {
                String roomsStr = parseRoomList(owner.getRooms());
                row.add(roomsStr);
            }
            aaData.add(row);
        }
        data = new HashMap<String, Object>();
        data.put("aaData", aaData);
        return SUCCESS;
    }

    /**
     * 将房间列表转化成字符串
     */
    private String parseRoomList(Set<Room> rooms) {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (Room room : rooms) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append("<br/>");
            }
            sb.append(room.getFullName());
        }
        return sb.toString();
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
