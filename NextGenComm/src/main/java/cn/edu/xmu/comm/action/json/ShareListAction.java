package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.entity.BillItem;
import cn.edu.xmu.comm.entity.Device;
import cn.edu.xmu.comm.entity.Owner;
import cn.edu.xmu.comm.entity.Room;
import cn.edu.xmu.comm.service.PropertyService;
import com.alibaba.fastjson.JSONArray;
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
        List<Owner> owners = propertyService.getAllOwners();
        JSONArray aaData = new JSONArray();
        for (Owner owner : owners) {
            JSONArray row = new JSONArray();
            row.add(owner.getName());
            row.add(owner.getUsername());
            if (owner.getRooms() == null) {
                row.add(0);
                row.add(0.0);
                row.add("");
            } else {
                row.add(owner.getRooms().size());
                buildRow(owner.getUnpaidBills(), owner.getRooms(), row);
            }
            aaData.add(row);
        }
        data = new HashMap<String, Object>();
        data.put("iTotalRecords", owners.size());
        data.put("iTotalDisplayRecords", owners.size());
        data.put("aaData", aaData);
        return SUCCESS;
    }

    private void buildRow(Set<BillItem> unpaidBills, Set<Room> rooms, JSONArray row) {
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
            List<Device> devices = room.getSharedDevice();
            for (Device device : devices) {
                if (!isFirst) {
                    deviceList.append("<br/>");
                } else {
                    isFirst = false;
                }
                deviceList.append(device.getNo());
            }
        }
        row.add(deviceList.toString());
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

}
