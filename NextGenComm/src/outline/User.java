import java.util.List;

/**
 * Created by Roger on 2014/12/5 0005.
 */
public class User {
    String name;
    List<House> houseList;
    List<Car> carList;

    //公维金是单独交的，但是一起算，交到不同的账户。
    List<BillItem> unpaidBills;
}
