package cn.edu.xmu.comm.commons.calc;

import cn.edu.xmu.comm.entity.Device;
import cn.edu.xmu.comm.entity.Room;

import java.math.BigDecimal;

/**
 * Created by Roger on 2014/12/8 0008.
 *
 * @author Mengmeng Yang
 * @version 2014/12/8 0008
 */
public interface IShareCalculator {

    /**
     * 计算公摊费用
     *
     * @param room   房间
     * @param device 设备
     * @param amount 总费用
     * @return 分摊费用
     */
    public BigDecimal calculateShare(Room room, Device device, BigDecimal amount);

}
