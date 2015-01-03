package cn.edu.xmu.comm.commons.calc;

import cn.edu.xmu.comm.entity.BillItem;

import java.math.BigDecimal;

/**
 * Created by Roger on 2015/1/2 0002.
 * 滞纳金计算接口
 */
public interface IOverdueFineCalculator {

    /**
     * 计算滞纳金
     * @param billItem 账单项
     */
    BigDecimal calculate(BillItem billItem);
}
