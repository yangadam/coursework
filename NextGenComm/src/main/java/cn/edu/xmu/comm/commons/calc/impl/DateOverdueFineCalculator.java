package cn.edu.xmu.comm.commons.calc.impl;

import cn.edu.xmu.comm.commons.calc.IOverdueFineCalculator;
import cn.edu.xmu.comm.entity.BillItem;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Owner;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by Roger on 2015/1/2 0002.
 *
 * @version 2015/1/2 0002
 */
@Component
@Lazy
public class DateOverdueFineCalculator implements IOverdueFineCalculator {

    /**
     * 计算滞纳金
     *
     * @param billItem 账单项
     * @return 滞纳金
     */
    @Override
    public BigDecimal calculate(BillItem billItem) {
        Owner owner = billItem.getOwner();
        Community community = owner.getCommunity();
        BigDecimal rate = community.getOverDueFeeRate();
        BigDecimal diffDays = BigDecimal.valueOf(billItem.getOverDueDays());
        return diffDays.multiply(rate).multiply(billItem.getAmount());
    }

}
