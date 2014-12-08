import javax.persistence.Entity;
import java.util.List;

/**
 * Created by Roger on 2014/12/5 0005.
 */
@Entity
public class Car extends ScopedEntity {

    String no;
    ParkPlace parkPlace;

    public void generateCar(List<BillItem> billItems) {
        BillItem billItem = new BillItem();
        billItem.amount = this.parkPlace.monthlyFee;
        billItems.add(billItem);
    }
}
