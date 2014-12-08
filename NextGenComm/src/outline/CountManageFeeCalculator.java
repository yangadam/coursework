/**
 * Created by Roger on 2014/12/8 0008.
 */
public class CountManageFeeCalculator implements IManageFeeCalculator {
    @Override
    public Double calculate(Room room) {
        Community community = room.community;
        return room.area * community.manageFeePerM2;
    }
}
