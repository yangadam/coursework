package cn.edu.xmu.comm.action.json;

import cn.edu.xmu.comm.commons.utils.Constants;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.service.PropertyService;
import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
public class OwnerSearchAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    private Map<String, Object> data;

    private String term;

    @Override
    public String execute() {
        try {
            term = URLDecoder.decode(term, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Community community = (Community) ActionContext.getContext()
                .getSession().get(Constants.COMMUNITY);

        List<String[]> list = propertyService.searchOwner(term, community);
        JSONArray owners = new JSONArray();
        for (String[] aList : list) {
            Map<String, Object> row = new HashMap<String, Object>();
            row.put("id", aList[0]);
            row.put("name", aList[1]);
            row.put("username", aList[2]);
            owners.add(row);
        }
        data = new HashMap<String, Object>();
        data.put("owners", owners);
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
