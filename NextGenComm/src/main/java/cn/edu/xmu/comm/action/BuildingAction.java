package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.entity.Building;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.service.PropertyService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
public class BuildingAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    private Integer selectedComm;

    private Community community;

    private Integer no;

    private String name;

    private List<Building> buildings;

    public String list() {
        fetchComm();
        buildings = propertyService.searchBuildingsByCommunity(community);
        return SUCCESS;
    }

    public String add() {
        propertyService.addBuilding(no, name, community);
        return SUCCESS;
    }

    private void fetchComm() {
        Map map = ActionContext.getContext().getSession();
        List<Community> communities = (List<Community>) map.get("COMMS");
        if (communities != null) {
            community = communities.get(selectedComm - 1);
        }
    }

    public Integer getSelectedComm() {
        return selectedComm;
    }

    public void setSelectedComm(Integer selectedComm) {
        this.selectedComm = selectedComm;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }
}
