import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Roger on 2014/12/7 0007.
 */
public class Device {
    Integer id;
    String no;
    List<DeviceVaule> values;
    String level;
    String type;//"电"
    TreeMap<Double, Double> gradient;
    String poolType;//费用计算方式的类名，用SimpleFactory创建；

    Double getUsage(){
        Double lastValue = values.get(values.size()-2).value;
        Double currentValue = values.get(values.size()-1).value;
        return currentValue - lastValue;
    }

}
