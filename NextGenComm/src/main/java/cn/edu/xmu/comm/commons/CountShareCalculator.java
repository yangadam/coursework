package cn.edu.xmu.comm.commons;

import cn.edu.xmu.comm.domain.Device;
import cn.edu.xmu.comm.domain.Property;
import cn.edu.xmu.comm.domain.Room;

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
