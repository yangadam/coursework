import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roger on 2014/12/5 0005.
 */
//当系统在计算时，所有的支付都禁止
public class FinanceService {
    UserDAO userDAO;

    void generateBill() {
        List<User> allOwner = userDAO.getAllOwner();
        for (User user : allOwner) {
            user.generateBill();
        }
    }

}
