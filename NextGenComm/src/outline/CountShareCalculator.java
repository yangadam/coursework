/**
 * Created by Roger on 2014/12/8 0008.
 */
public class CountShareCalculator implements IShareCalculator {

    @Override
    public Double calculateShare(Room room, Device device, Double amount) {
        Integer count = device.shareRoomCount;
        return amount / count;
    }

}
