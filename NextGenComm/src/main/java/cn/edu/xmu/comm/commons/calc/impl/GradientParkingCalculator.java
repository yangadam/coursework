package cn.edu.xmu.comm.commons.calc.impl;

import cn.edu.xmu.comm.commons.calc.IParkingCalculator;
import cn.edu.xmu.comm.entity.ParkingBill;
import cn.edu.xmu.comm.entity.ParkingLot;

import java.math.BigDecimal;

/**
 * Created by Roger on 2014/12/25 0025.
 *
 */
public class GradientParkingCalculator implements IParkingCalculator {

    @Override
    public BigDecimal calculate(ParkingBill parkingBill) {
        /**
         * TimeStamp.getTime() in Minutes
         * Returns the number of milliseconds since January 1, 1970, 00:00:00 GMT represented by this Timestamp object.
         */
        Integer startTime = (int) (parkingBill.getStartTime().getTime() / 1000 / 60);
        Integer endTime = (int) (parkingBill.getEndTime().getTime() / 1000 / 60);
        ParkingLot parkingLot = parkingBill.getCommunity().getParkingLot(ParkingLot.ParkingLotStatus.TEMP);
        BigDecimal fee = parkingLot.calculateTempParkingFee(endTime - startTime);
        parkingBill.setFee(fee);
        return fee;
    }

}
