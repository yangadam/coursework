import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roger on 2014/12/5 0005.
 */
//当系统在计算时，所有的支付都禁止
public class CalculateService {
    UserDAO userDAO;
    Calculator calculator;

    void calcuate() {
        List<User> allOwner = userDAO.getAllOwner();
        for (User user : allOwner) {
            calculateBill(user);
        }
    }

    private void calculateBill(User user) {
        //List<BillItem> allList = new ArrayList<BillItem>();

        for (House house : user.houseList) {
            calculateHouse(house, user.unpaidBills);
        }

        for (Car car : user.carList) {
            calculator.calculateCar(car, user.unpaidBills);
        }
    }

    /* 计算户水费，户电费，户物业管理费，户公摊，户垃圾费，户公维金
    * */
    private void calculateHouse(House house, List<BillItem> billItems) {
        calculator.calculateHouseEnergy(house, billItems);
        calculator.calculateHouseShare(house, billItems);
        calculator.calculateManagementFee(house, billItems);
        calculator.calculateGarbageFee(house, billItems);
        calculator.calculatePublicFund(house, billItems);
    }


}
