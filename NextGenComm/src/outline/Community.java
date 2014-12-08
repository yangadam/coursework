import javax.persistence.Entity;
import java.util.List;

/**
 * Created by Roger on 2014/12/7 0007.
 */
@Entity
public class Community extends DataEntity {

    String manageFeeType;
    String garbageFeeType;
    Double garbageFee;// per room or per m^2
    PublicFund publicFund;
    Double manageFeePerM2;

    List<Building> buildingList;
    List<Device> deviceList;
    List<ParkPlace> parkingLot;

}
