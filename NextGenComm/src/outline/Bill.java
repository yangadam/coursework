import java.util.List;

/**
 * Created by Roger on 2014/12/5 0005.
 */
public class Bill {
    /* Bill 同时存储在两个表(Bill, BillHistory)中，利用触发器保证数据一致性
    * */
    List<BillItem> billItemList;
    int total;
}
