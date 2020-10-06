package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.entity.BillItem;
import cn.edu.xmu.comm.entity.Owner;
import cn.edu.xmu.comm.service.PropertyService;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/7/2015 0007
 */
@Controller
public class OwnerBillAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    private Map<String, Object> data;

    private Integer ownerId;

    @Override
    public String execute() {
        Owner owner = propertyService.getOwner(ownerId);
        JSONArray aaData = new JSONArray();
        for (BillItem billItem : owner.getUnpaidBills()) {
            JSONArray row = new JSONArray();
            row.add(billItem.getName() + "(" + billItem.getDescription() + ")");
            row.add(billItem.getUsage());
            row.add(billItem.getAmount());
            row.add(billItem.getOverDueDays());
            row.add(billItem.getOverDueFee());

            aaData.add(row);
        }
        data = new HashMap<String, Object>();
        data.put("aaData", aaData);
        data.put("total", owner.getTotal());

        return SUCCESS;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
}
