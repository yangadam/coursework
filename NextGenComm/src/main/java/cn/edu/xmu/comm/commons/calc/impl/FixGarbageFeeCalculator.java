package cn.edu.xmu.comm.commons.calc.impl;

import cn.edu.xmu.comm.commons.calc.IGarbageFeeCalculator;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Room;

import java.math.BigDecimal;

/**
 * 固定垃圾处理费计算器
 * Created by Roger on 2014/12/8 0005.
 *
 * @author Mengmeng Yang
 * @version 2014-12-8
 */
public class FixGarbageFeeCalculator implements IGarbageFeeCalculator {

    /**
     * 固定垃圾费
     *
     * @param room 房间
     * @return 费用
     */
    @Override
    public BigDecimal calculate(Room room) {
        Community community = room.getCommunity();
        return community.getGarbageFee();
    }

}
