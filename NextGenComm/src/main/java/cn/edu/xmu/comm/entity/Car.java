package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.persistence.DataEntity;

import javax.persistence.*;
import java.util.Set;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 车牌号
     */
    private String license;
    //endregion

    //region Public Method
    /**
     * 拥有者
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Owner.class)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    /**
     * 车辆状态
     * <p/>
     * CarStatus.NO   没有车位
     * CarStatus.RENT 租用的车位
     * CarStatus.BUY  购买的车位
     */
    private CarStatus status;

    /**
     * 拥有的车位
     */
    @OneToOne(fetch = FetchType.EAGER, targetEntity = ParkingPlace.class)
    @JoinColumn(name = "park_place_id", nullable = true)
    private ParkingPlace parkingPlace;
    //endregion

    //region Constructors
    public Car() {
        this.status = CarStatus.NO;
    }

    /**
     * 汽车构造函数
     *
     * @param license 车牌
     * @param owner   业主
     */
    public Car(String license, Owner owner) {
        this.license = license;
        this.owner = owner;
        this.status = CarStatus.NO;
    }

    /**
     * 汽车构造函数
     *
     * @param license      车牌
     * @param owner        业主
     * @param status       状态: 租用车位 购买车位
     * @param parkingPlace 停车位
     */
    public Car(String license, Owner owner, CarStatus status, ParkingPlace parkingPlace) {
        this.license = license;
        this.owner = owner;
        this.status = status;
        this.parkingPlace = parkingPlace;
    }

    /**
     * 生成车位管理费账单项
     *
     * @param billItems 未支付账单
     */
    public void generateCar(Set<BillItem> billItems) {
        BillItem billItem = new BillItem();
        billItem.setName(NAME);
        billItem.setDescription(license);
        billItem.setAmount(parkingPlace.getMonthlyFee());
        billItem.setOwner(owner);
        billItems.add(billItem);
    }

    //region Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

    public ParkingPlace getParkingPlace() {
        return parkingPlace;
    }

    public void setParkingPlace(ParkingPlace parkingPlace) {
        this.parkingPlace = parkingPlace;
    }
    //endregion

    /**
     * 车位状态
     */
    public enum CarStatus {
        NO("没有车位"), RENT("租用的车位"), BUY("购买的车位");

        private String typeName;

        private CarStatus(String typeName) {
            this.typeName = typeName;
        }

        @Override
        public String toString() {
            return typeName;
        }
    }

}
