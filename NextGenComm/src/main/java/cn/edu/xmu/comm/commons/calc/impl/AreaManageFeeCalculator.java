package cn.edu.xmu.comm.commons.calc.impl;

import cn.edu.xmu.comm.commons.calc.IManageFeeCalculator;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Room;

import java.math.BigDecimal;

/**
 * Created by Roger on 2014/12/8 0008.
 */
public class AreaManageFeeCalculator implements IManageFeeCalculator {

    /**
     * 按面积计算物业管理费
     *
     * @param room
     * @return
     */
    @Override
    public BigDecimal calculate(Room room) {
        Community community = room.getCommunity();
        BigDecimal unitPrice = community.getManageFee();
        BigDecimal amount = unitPrice.multiply(BigDecimal.valueOf(room.getHouseArea()));
        return amount;
    }

}
