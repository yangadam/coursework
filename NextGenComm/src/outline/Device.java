import javax.persistence.Entity;
import java.util.*;

/**
 * Created by Roger on 2014/12/7 0007.
 */
@Entity
public class Device extends ScopedEntity {

    String no;
    String level;
    String type;//"电"
    List<DeviceVaule> values;
    TreeMap<Double, Double> gradient;
    String shareType;

    Integer shareRoomCount;
    Double shareTotalArea;

    Double getUsage() {
        Double lastValue = values.get(values.size() - 2).value;
        Double currentValue = values.get(values.size() - 1).value;
        return currentValue - lastValue;
    }

    public Double calculate() {
        Double amount = 0.0;
        Double lastValue = 0.0;
        Iterator it = gradient.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (getUsage() > (Double) entry.getKey()) {
                amount += (Double) entry.getValue() * ((Double) entry.getKey() - lastValue);
            } else {
                amount += (Double) entry.getValue() * (getUsage() - lastValue);
            }
            lastValue = (Double) entry.getKey();
        }
        return amount;
    }

}
