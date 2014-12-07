import java.util.List;

/**
 * Created by Roger on 2014/12/5 0005.
 */
public interface ICalculator {
    List<BillItem> calculateHouse(House house);
    List<BillItem> calculateCar(Car car);
}
