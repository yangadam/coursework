package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.commons.utils.SessionUtils;
import cn.edu.xmu.comm.entity.Community;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/6/2015 0006
 */
@Controller
public class CommInfoAction extends ActionSupport {

    private String commName;

    private BigDecimal garbageFee;

    private BigDecimal manageFee;

    private BigDecimal overDueFeeRate;

    @Override
    public String execute(){

        Community community = SessionUtils.getCommunity();
        commName = community.getName();
        garbageFee = community.getGarbageFee();
        manageFee = community.getManageFee();
        overDueFeeRate = community.getOverDueFeeRate();

        return SUCCESS;
    }

    public String getCommName() {
        return commName;
    }

    public void setCommName(String commName) {
        this.commName = commName;
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
}
