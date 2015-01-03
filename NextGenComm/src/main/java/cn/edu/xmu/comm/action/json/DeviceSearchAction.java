package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.commons.utils.Constants;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.service.FinanceService;
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
 * @version 1/3/2015 0003
 */
@Controller
public class DeviceSearchAction extends ActionSupport {

    @Resource
    private FinanceService financeService;

    @Resource
    private PropertyService propertyService;

    private Map<String, Object> data;

    private String term;

    @Override
    public String execute() {
        Community community = (Community) ActionContext.getContext()
                .getSession().get(Constants.COMMUNITY);
        List<String[]> list = financeService.searchDevice(term.toUpperCase(), community);
        JSONArray devices = new JSONArray();
        for (String[] aList : list) {
            Map<String, Object> row = new HashMap<String, Object>();
            row.put("id", aList[0]);
            row.put("no", aList[1]);
            row.put("type", aList[2]);
            row.put("currentValue", aList[3]);
            devices.add(row);
        }
        data = new HashMap<String, Object>();
        data.put("devices", devices);
        return SUCCESS;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

}
