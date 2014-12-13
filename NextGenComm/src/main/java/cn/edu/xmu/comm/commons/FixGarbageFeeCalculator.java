package cn.edu.xmu.comm.commons;

import cn.edu.xmu.comm.domain.Community;
import cn.edu.xmu.comm.domain.Room;

import java.math.BigDecimal;

/**
 * Created by Roger on 2014/12/8 0008.
 */
public class FixGarbageFeeCalculator implements IGarbageFeeCalculator {

    /**
     * 固定垃圾费
     *
     * @param room
     * @return
     */
    @Override
    public BigDecimal calculate(Room room) {
        Community community = room.getCommunity();
        return community.getGarbageFee();
    }

}
