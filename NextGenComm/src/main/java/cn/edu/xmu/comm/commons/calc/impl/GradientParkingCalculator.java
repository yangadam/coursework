package cn.edu.xmu.comm.commons.calc.impl;

import cn.edu.xmu.comm.commons.calc.IParkingCalculator;
import cn.edu.xmu.comm.entity.ParkBill;
import cn.edu.xmu.comm.entity.ParkingLot;

import java.math.BigDecimal;

/**
 * Created by Roger on 2014/12/25 0025.
 */
public class GradientParkingCalculator implements IParkingCalculator {

    @Override
    public BigDecimal calculate(ParkBill parkBill) {
        /**
         * TimeStamp.getTime() in Minutes
         * Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Timestamp object.
         */
        Integer startTime = (int) (parkBill.getStartTime().getTime() / 1000 / 60);
        Integer endTime = (int) (parkBill.getEndTime().getTime() / 1000 / 60);
        ParkingLot parkingLot = parkBill.getCommunity().getParkingLot(ParkingLot.ParkingLotStatus.TEMP);
        BigDecimal fee = parkingLot.calculateTempParkingFee(endTime - startTime);
        parkBill.setFee(fee);
        return fee;
    }

}
