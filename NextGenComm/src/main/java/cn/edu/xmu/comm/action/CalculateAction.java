package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.domain.BillItem;
import cn.edu.xmu.comm.domain.Owner;
import cn.edu.xmu.comm.service.FinanceService;
import cn.edu.xmu.comm.service.OwnerService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;


@Controller
public class CalculateAction extends ActionSupport {

    @Resource
    private FinanceService financeService;

    @Resource
    private OwnerService ownerService;

    private Integer ownerid;

    private Owner owner;

    private List<BillItem> bills;

    public String execute() {
        financeService.generateBill();
        return SUCCESS;
    }

    public String search() {
        owner = ownerService.getById(ownerid);
        bills = owner.getUnpaidBills();
        return SUCCESS;
    }

    //region Getters and Setters
    public FinanceService getFinanceService() {
        return financeService;
    }

    public void setFinanceService(FinanceService financeService) {
        this.financeService = financeService;
    }

    public OwnerService getOwnerService() {
        return ownerService;
    }

    public void setOwnerService(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    public Integer getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(Integer ownerid) {
        this.ownerid = ownerid;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<BillItem> getBills() {
        return bills;
    }

    public void setBills(List<BillItem> bills) {
        this.bills = bills;
    }
    //endregion

}
