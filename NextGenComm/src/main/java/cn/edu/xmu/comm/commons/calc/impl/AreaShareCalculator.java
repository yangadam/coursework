package cn.edu.xmu.comm.commons.calc.impl;

import cn.edu.xmu.comm.commons.calc.IShareCalculator;
import cn.edu.xmu.comm.entity.Device;
import cn.edu.xmu.comm.entity.Property;
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
public class AreaShareCalculator implements IShareCalculator {

    @Override
    public BigDecimal calculateShare(Room room, Device device, BigDecimal amount) {
        Property property = device.getProperty();
        /*
        Double area1 = property.getUsedHouseArea();
        Double area2 = room.getUsedHouseArea();
        */
        BigDecimal area1 = BigDecimal.valueOf(property.getUsedHouseArea());
        BigDecimal area2 = BigDecimal.valueOf(room.getUsedHouseArea());
        return amount.multiply(area2.divide(area1, 3, BigDecimal.ROUND_HALF_EVEN));
    }

}
