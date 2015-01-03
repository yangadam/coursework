package cn.edu.xmu.comm.commons.calc.impl;

import cn.edu.xmu.comm.commons.calc.IShareCalculator;
import cn.edu.xmu.comm.entity.Device;
import cn.edu.xmu.comm.entity.Room;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/3/2015 0003
 */
@Component
@Lazy
public class FloorShareCalculator implements IShareCalculator {

    @Override
    public BigDecimal calculateShare(Room room, Device device, BigDecimal amount) {
        int floorCount = room.getBuilding().getChildCount();
        int floorNo = room.getFloor().getNo();
        int base = (1 + floorCount) * floorCount / 2;
        return amount.multiply(BigDecimal.valueOf(floorNo)).divide(BigDecimal.valueOf(base));
    }

}
