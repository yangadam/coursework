package cn.edu.xmu.comm.commons.calc;

import cn.edu.xmu.comm.entity.Room;

import java.math.BigDecimal;

/**
 * Created by Roger on 2014/12/8 0008.
 */
public interface IGarbageFeeCalculator {

    /**
     * 计算垃圾费
     *
     * @param room 房间
     * @return 费用
     */
    BigDecimal calculate(Room room);

}
