package cn.edu.xmu.comm.commons.calc.impl;

import cn.edu.xmu.comm.commons.calc.IShareCalculator;
import cn.edu.xmu.comm.entity.Device;
import cn.edu.xmu.comm.entity.Property;
import cn.edu.xmu.comm.entity.Room;

import java.math.BigDecimal;

/**
 * Created by Roger on 2014/12/8 0008.
 */
public class CountShareCalculator implements IShareCalculator {

    /**
     * 按已入住用户计算分摊
     *
     * @param room
     * @param device
     * @param amount
     * @return
     */
    @Override
    public BigDecimal calculateShare(Room room, Device device, BigDecimal amount) {
        Property property = device.getProperty();
        Integer count = property.getUsedHouseCount();
        BigDecimal shareAmount = amount.divide(BigDecimal.valueOf(count));
        return shareAmount;
    }

}
