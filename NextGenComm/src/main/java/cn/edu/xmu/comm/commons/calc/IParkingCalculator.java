package cn.edu.xmu.comm.commons.calc;

import cn.edu.xmu.comm.entity.ParkingBill;

import java.math.BigDecimal;

/**
 * Created by Roger on 2014/12/25 0025.
 */
public interface IParkingCalculator {

    /**
     * 计算停车费用
     *
     * @param parkingBill 停车账单
     * @return 停车费用
     */
    public BigDecimal calculate(ParkingBill parkingBill);
}
