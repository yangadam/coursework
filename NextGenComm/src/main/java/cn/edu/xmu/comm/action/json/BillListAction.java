package cn.edu.xmu.comm.action;

import cn.edu.xmu.comm.commons.annotation.Required;
import cn.edu.xmu.comm.commons.utils.Constants;
import cn.edu.xmu.comm.entity.BillItem;
import cn.edu.xmu.comm.entity.Owner;
import cn.edu.xmu.comm.entity.User;
import cn.edu.xmu.comm.service.PropertyService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/4/2015 0004
 */
@Controller
public class BillListAction extends ActionSupport {

    @Resource
    private PropertyService propertyService;

    private List<BillItem> billItems;

    private BigDecimal total;

    @Override
    @Required(name = "owner")
    public String execute() {
        User user = (User) ActionContext.getContext().getSession().get(Constants.USER);
        Owner owner = propertyService.loadOwner(user);
        billItems = owner.getUnpaidBills();
        total = owner.getTotal();
        return SUCCESS;
    }

    public List<BillItem> getBillItems() {
        return billItems;
    }

    public void setBillItems(List<BillItem> billItems) {
        this.billItems = billItems;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}