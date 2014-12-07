import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Roger on 2014/12/7 0007.
 */
public class PublicFund {
    Double threshold;
    String account;
    //
    Double balance;
    //公维金收入支出记录，定义一个类



    boolean isNeeded(){
        return balance < threshold;
    }

}
