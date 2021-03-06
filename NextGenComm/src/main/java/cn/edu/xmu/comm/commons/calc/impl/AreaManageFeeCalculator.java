package cn.edu.xmu.comm.commons.calc.impl;

import cn.edu.xmu.comm.commons.calc.IManageFeeCalculator;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Room;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 按面积物业管理费计算器
 * Created by Roger on 2014/12/8 0005.
 *
 * @author Mengmeng Yang
 * @version 2014-12-8
 */
@Component
@Lazy
public class AreaManageFeeCalculator implements IManageFeeCalculator {

    /**
     * 按面积计算物业管理费
     *
     * @param room 房间
     * @return 费用
     */
    @Override
    public BigDecimal calculate(Room room) {
        Community community = room.getCommunity();
        BigDecimal unitPrice = community.getManageFee();
        BigDecimal result = unitPrice.multiply(BigDecimal.valueOf(room.getHouseArea()));
        return result;
    }

}
