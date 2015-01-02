package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.service.PropertyService;
import com.alibaba.fastjson.JSONArray;
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
public class VacantRoomNoAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    private Map<String, Object> data;

    private Integer floorId;

    @Override
    public String execute() {
        List<Integer[]> list = propertyService.getVacantRoomNos(floorId);
        data = new HashMap<String, Object>();
        JSONArray ids = new JSONArray();
        JSONArray nos = new JSONArray();
        for (Integer[] idAndNo : list) {
            ids.add(idAndNo[0]);
            nos.add(idAndNo[1]);
        }
        data.put("id", ids);
        data.put("no", nos);

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
