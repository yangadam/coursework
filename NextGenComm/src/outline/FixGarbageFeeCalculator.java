/**
 * Created by Roger on 2014/12/8 0008.
 */
public class FixGarbageFeeCalculator implements IGarbageFeeCalculator {

    @Override
    public Double calculate(Room room) {
        Community community = room.community;
        return community.garbageFee;
    }

}
