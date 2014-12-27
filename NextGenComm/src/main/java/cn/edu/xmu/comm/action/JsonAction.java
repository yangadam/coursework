package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.service.PropertyService;
import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class JsonAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    private List<Community> communities;

    private Community community;

    private String result;

    public String execute() throws Exception {

        community = propertyService.getCommunity(2);

        String result = JSON.toJSONString(community, true);


        return SUCCESS;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
