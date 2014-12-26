package cn.edu.xmu.comm.commons.calc.impl;

import cn.edu.xmu.comm.commons.calc.IShareCalculator;
import cn.edu.xmu.comm.entity.Device;
import cn.edu.xmu.comm.entity.Property;
import cn.edu.xmu.comm.entity.Room;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 按户数公摊计算器
 * Created by Roger on 2014/12/8 0005.
 *
 * @author Mengmeng Yang
 * @version 2014-12-8
 */
@Component
@Lazy
public class CountShareCalculator implements IShareCalculator {

    /**
     * 按已入住用户计算分摊
     *
     * @param room 房间
     * @param device 设备
     * @param amount 金额
     * @return 费用
     */
    @Override
    public BigDecimal calculateShare(Room room, Device device, BigDecimal amount) {
        Property property = device.getProperty();
        Integer count = property.getUsedHouseCount();
        return amount.divide(BigDecimal.valueOf(count));
    }

}
