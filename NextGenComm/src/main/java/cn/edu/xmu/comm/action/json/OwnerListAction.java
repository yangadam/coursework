package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.commons.utils.Constants;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Owner;
import cn.edu.xmu.comm.entity.Room;
import cn.edu.xmu.comm.service.PropertyService;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Community community = (Community) ActionContext
                .getContext().getSession().get(Constants.COMMUNITY);
        List<Owner> owners = propertyService.getAllOwners(community);
        int i = 1;
        JSONArray aaData = new JSONArray();
        for (Owner owner : owners) {
            JSONArray row = new JSONArray();
            row.add(i++);
            row.add(owner.getUsername());
            row.add(owner.getName());
            if (owner.getRoomList().size() == 0) {
                row.add("未入住");
            } else {
                StringBuilder sb = new StringBuilder();
                List<Room> rooms = owner.getRoomList();
                for (int j = 0; j < rooms.size(); j++) {
                    sb.append(rooms.get(j).getFullName());
                    if (j != rooms.size() - 1) {
                        sb.append("<br/>");
                    }
                }
                row.add(sb.toString());
            }
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
