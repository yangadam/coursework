package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.persistence.DataEntity;

import javax.persistence.*;
import java.util.List;

/**
 * 车辆实体
 * Created by Roger on 2014/12/8 0005.
 *
 * @author Mengmeng Yang
 * @version 2014-12-8
 */
@Entity
public class Car extends DataEntity {

    //region Instance Variables
    /**
     * 生成的账单项的名称
     */
    public static final String NAME = "车位管理费";
    /**
     * 车辆状态，0：没有车位、1：租用的车位、2：购买的车位
     */
    public static final int NO = 0;
    public static final int RENT = 1;
    public static final int BUY = 2;
    //endregion
    /**
     * 车牌号
     */
    @Id
    private String license;
    //endregion

    //region Public Method
    /**
     * 拥有者
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Owner.class)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;
    //endregion
    /**
     * 车辆状态，0：没有车位、1：租用的车位、2：购买的车位
     * <li>{@link #NO}</li>
     * <li>{@link #RENT}</li>
     * <li>{@link #BUY}</li>
     */
    private int status;
    /**
     * 拥有的车位
     */
    @OneToOne(fetch = FetchType.EAGER, targetEntity = ParkPlace.class)
    @JoinColumn(name = "park_place_id", nullable = false)
    private ParkPlace parkPlace;

    //region Constructors
    public Car() {
        this.status = NO;
    }

    /**
     * 生成车位管理费账单项
     *
     * @param billItems 未支付账单
     */
    public void generateCar(List<BillItem> billItems) {
        BillItem billItem = new BillItem();
        billItem.setName(NAME);
        billItem.setDescription(license);
        billItem.setAmount(parkPlace.getMonthlyFee());
        billItem.setOwner(owner);
        billItems.add(billItem);
    }

    //region Getters and Setters
    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
    //endregion

    //region Constants

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ParkPlace getParkPlace() {
        return parkPlace;
    }

    public void setParkPlace(ParkPlace parkPlace) {
        this.parkPlace = parkPlace;
    }
    //endregion

}
