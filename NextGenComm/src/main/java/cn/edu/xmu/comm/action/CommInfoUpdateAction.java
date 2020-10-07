package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.commons.utils.FeeTypeUtils;
import cn.edu.xmu.comm.commons.utils.SessionUtils;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.service.PropertyService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Controller
public class CommInfoUpdateAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    private BigDecimal garbageFee;

    private BigDecimal manageFee;

    private BigDecimal overDueFeeRate;

    private String garbageFeeType;

    private String manageFeeType;

    private String overDueFeeType;

    @Override
    public String execute() {

        Community community = SessionUtils.getCommunity();

        community.setGarbageFee(garbageFee);
        community.setManageFee(manageFee);
        community.setOverDueFeeRate(overDueFeeRate);

        community.setGarbageFeeType(FeeTypeUtils.getFeeType(garbageFeeType));
        community.setManageFeeType(FeeTypeUtils.getFeeType(manageFeeType));
        community.setOverDueFeeType(FeeTypeUtils.getFeeType(overDueFeeType));
        propertyService.updateCommunity(community);
        return SUCCESS;
    }

    public BigDecimal getGarbageFee() {
        return garbageFee;
    }

    public void setGarbageFee(BigDecimal garbageFee) {
        this.garbageFee = garbageFee;
    }

    public BigDecimal getManageFee() {
        return manageFee;
    }

    public void setManageFee(BigDecimal manageFee) {
        this.manageFee = manageFee;
    }

    public BigDecimal getOverDueFeeRate() {
        return overDueFeeRate;
    }

    public void setOverDueFeeRate(BigDecimal overDueFeeRate) {
        this.overDueFeeRate = overDueFeeRate;
    }

    public String getGarbageFeeType() {
        return garbageFeeType;
    }

    public void setGarbageFeeType(String garbageFeeType) {
        this.garbageFeeType = garbageFeeType;
    }

    public String getManageFeeType() {
        return manageFeeType;
    }

    public void setManageFeeType(String manageFeeType) {
        this.manageFeeType = manageFeeType;
    }

    public String getOverDueFeeType() {
        return overDueFeeType;
    }

    public void setOverDueFeeType(String overDueFeeType) {
        this.overDueFeeType = overDueFeeType;
    }
}
