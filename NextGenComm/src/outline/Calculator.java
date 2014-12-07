import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Roger on 2014/12/5 0005.
 */
public class Calculator {
    
    public void calculateHouseEnergy(House house,List<BillItem> billItems) {
        for (Device device:house.privateDevice){
            BillItem item = new BillItem();
            Double usage = device.getUsage();
            TreeMap<Double,Double> gradient = device.gradient;
            Double amount = calculateByUsage(usage, gradient);

            billItems.add(item);
        }
    }

    private Double calculateByUsage(Double usage, TreeMap<Double, Double> gradient) {
        Double amount = 0.0;
        Double lastValue = 0.0;
        Iterator it = gradient.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            if (usage > (Double)entry.getKey()){
                amount += (Double)entry.getValue()*((Double)entry.getKey()-lastValue);
            }
            else {
                amount += (Double)entry.getValue()*(usage-lastValue);
            }
            lastValue = (Double)entry.getKey();
        }
        return amount;
    }

    public void calculateHouseShare(House house, List<BillItem> billItems) {
        BillItem billItem = new BillItem();
        Double total = 1.0;
        for (Device device:house.shareDevice){
            Double usage = device.getUsage();
            String poolType = device.poolType;
            //用工厂实现计算器
            //SimpleFactory.create(poolType);
            //SimpleFactory.create(shareType);
            Double amount = 0.0;//
            total += amount;
        }
        billItem.amount = total;
        billItems.add(billItem);
    }

    public void calculateManagementFee(House house, List<BillItem> billItems) {
        BillItem billItem = new BillItem();
        Community community = house.community;
        String type = community.managementFeeType;
        //factory.create(type);
        //传入House
        billItems.add(billItem);
    }

    public void calculateGarbageFee(House house, List<BillItem> billItems) {
        BillItem billItem = new BillItem();
        Community community = house.community;
        String type = community.managementFeeType;
        //factory.create(type);
        //传入House
        billItems.add(billItem);
    }

    public void calculatePublicFund(House house, List<BillItem> billItems) {

        Community community = house.community;
        if (community.publicFund.isNeeded()){
            String type = community.publicFundFeeType;
            BillItem billItem = new BillItem();
            //factory.create(type);
            //传入House
            billItems.add(billItem);
        }
    }

    public void calculateCar(Car car, List<BillItem> billItems) {
        BillItem billItem = new BillItem();
        billItem.amount = car.parkPlace.fee;
        billItems.add(billItem);
    }



}
