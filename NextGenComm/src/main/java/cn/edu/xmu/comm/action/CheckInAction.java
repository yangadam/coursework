package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.commons.exception.DifferentCommunityException;
import cn.edu.xmu.comm.service.PropertyService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/2/2015 0002
 */
@Controller
public class CheckInAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    private Integer roomId;
    private Integer ownerId;

    @Override
    public String execute() {
        try {
            propertyService.addOwnerToRoom(ownerId, roomId);
        } catch (DifferentCommunityException e) {
            e.printStackTrace();
            return INPUT;
        }
        return SUCCESS;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
}
