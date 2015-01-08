package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.entity.Owner;
import cn.edu.xmu.comm.entity.Room;
import cn.edu.xmu.comm.service.FinanceService;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 欠费用户Action
 * 序号、业主姓名、业主房间、业主id
 *
 * @author Yiu-Wah WONG
 * @version 2015/1/4
 */
@Controller
public class ArrearageListAction extends ActionSupport {

    @Resource
    private FinanceService financeService;

    private Map<String, Object> data;

    @Override
    public String execute() {
        List<Owner> owners = financeService.getArrearageOwner();
        int i = 1;
        JSONArray aaData = new JSONArray();
        for (Owner owner : owners) {
            JSONArray row = new JSONArray();
            row.add(i++);
            row.add(owner.getName());
            String roomsStr = parseRoomList(owner.getRooms());
            row.add(roomsStr);
            row.add(owner.getId());
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
