package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.commons.utils.Constants;
import cn.edu.xmu.comm.entity.*;
import cn.edu.xmu.comm.service.PropertyService;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/3/2015 0003
 */
@Controller
public class ShareListAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    private Map<String, Object> data;

    @Override
    public String execute() {
        Community community = (Community) ActionContext.getContext().
                getSession().get(Constants.COMMUNITY);
        List<Owner> owners = propertyService.getAllOwners(community);
        JSONArray aaData = new JSONArray();
        for (Owner owner : owners) {
            JSONArray row = new JSONArray();
            row.add(owner.getName());
            row.add(owner.getUsername());
            if (owner.getRoomList() == null) {
                row.add(0);
                row.add(0.0);
                row.add("");
            } else {
                row.add(owner.getRoomList().size());
                buildRow(owner.getUnpaidBills(), owner.getRoomList(), row);
            }
            aaData.add(row);
        }
        data = new HashMap<String, Object>();
        data.put("iTotalRecords", owners.size());
        data.put("iTotalDisplayRecords", owners.size());
        data.put("aaData", aaData);
        return SUCCESS;
    }

    private void buildRow(List<BillItem> unpaidBills, List<Room> rooms, JSONArray row) {
        BigDecimal amount = BigDecimal.ZERO;
        for (BillItem billItem : unpaidBills) {
            if (billItem.getName().equals(Room.SHARE)) {
                amount = amount.add(billItem.getAmount());
            }
        }
        row.add(amount);
        StringBuilder deviceList = new StringBuilder();
        boolean isFirst = true;
        for (Room room : rooms) {
            Set<Device> devices = room.getDeviceList();
            for (Device device : devices) {
                if (!isFirst) {
                    deviceList.append("<br/>");
                } else {
                    isFirst = false;
                }
                deviceList.append(device.getNo());
            }
        }
        row.add(deviceList);
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

}
