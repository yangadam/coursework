import javax.persistence.Entity;
import java.util.List;

/**
 * Created by Roger on 2014/12/5 0005.
 */
@Entity
public class Room extends ScopedEntity {
    Community community;
    Floor floor;
    Double area;
    User user;
    List<Device> privateDevice;
    List<Device> shareDevice;

    /* 计算户水费，户电费，户物业管理费，户公摊，户垃圾费，户公维金
    * */
    public void generateRoom(List<BillItem> billItems) {
        generateEnergy(billItems);
        generateShare(billItems);
        generateManageFee(billItems);
        generateGarbageFee(billItems);
        generatePublicFund(billItems);
    }

    public void generateEnergy(List<BillItem> billItems) {
        for (Device device : this.privateDevice) {
            BillItem billItem = new BillItem();
            Double amount = device.calculate();
            billItem.amount = amount;
            billItems.add(billItem);
        }
    }

    public void generateShare(List<BillItem> billItems) {
        BillItem billItem = new BillItem();
        Double total = 0.0;
        for (Device device : this.shareDevice) {
            String poolType = device.shareType;
            IShareCalculator calculator = CalcutorFactory.getShareCalc(poolType);
            Double amount = device.calculate();
            Double shareAmount = calculator.calculateShare(this, device, amount);//
            total += shareAmount;
        }
        billItem.amount = total;
        billItems.add(billItem);
    }

    public void generateManageFee(List<BillItem> billItems) {
        BillItem billItem = new BillItem();
        Community community = this.community;
        String type = community.manageFeeType;
        IManageFeeCalculator calculator = CalcutorFactory.getManageFeeCalc(type);
        Double amount = calculator.calculate(this);
        billItem.amount = amount;
        billItems.add(billItem);
    }

    public void generateGarbageFee(List<BillItem> billItems) {
        BillItem billItem = new BillItem();
        Community community = this.community;
        String type = community.garbageFeeType;
        IGarbageFeeCalculator calculator = CalcutorFactory.getGarbageFeeCalc(type);
        Double amount = calculator.calculate(this);
        billItem.amount = amount;
        billItems.add(billItem);
    }

    public void generatePublicFund(List<BillItem> billItems) {

        Community community = this.community;
        PublicFund publicFund = community.publicFund;
        if (publicFund.isNeeded()) {
            BillItem billItem = new BillItem();
            billItem.amount = publicFund.chargePerRoom;
            billItems.add(billItem);
        }
    }

}
