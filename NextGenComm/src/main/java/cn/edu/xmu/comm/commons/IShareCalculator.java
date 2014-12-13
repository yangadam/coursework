package cn.edu.xmu.comm.commons;

import cn.edu.xmu.comm.domain.Device;
import cn.edu.xmu.comm.domain.Room;

import java.math.BigDecimal;

/**
 * Created by Roger on 2014/12/8 0008.
 */
public interface IShareCalculator {

    /**
     * 计算公摊费用
     *
     * @param room
     * @param device
     * @param amount
     * @return
     */
    public BigDecimal calculateShare(Room room, Device device, BigDecimal amount);

}
