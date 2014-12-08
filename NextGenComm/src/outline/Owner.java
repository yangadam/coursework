import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.List;

/**
 * Created by Roger on 2014/12/8 0008.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Owner extends User {

    List<Room> roomList;
    List<Car> carList;
    //公维金是单独交的，但是一起算，交到不同的账户。
    List<BillItem> unpaidBills;

    public void generateBill() {

        for (Room room : this.roomList) {
            room.generateRoom(this.unpaidBills);
        }

        for (Car car : this.carList) {
            car.generateCar(this.unpaidBills);
        }
    }
}
