package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.persistence.DataEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
@DynamicInsert
@DynamicUpdate
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"license"})
})
public class Car extends DataEntity {

    //region Constants
    /**
     * 生成的账单项的名称
     */
    public static final String NAME = "车位管理费";
    //endregion

    //region Public Methods
    //region Private Instance Variables
    private Integer id;
    //endregion

    //region Constructors
    private String license;
    private Owner owner;
    private CarStatus status;
    //endregion

    //region Getters
    private ParkingPlace parkingPlace;

    /**
     * 无参构造函数
     */
    public Car() {
        this.status = CarStatus.NO;
    }

    /**
     * 构造函数
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
     * 构造函数
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
    public void generateCar(List<BillItem> billItems) {
        BillItem billItem = new BillItem();
        billItem.setName(NAME);
        billItem.setDescription(license);
        billItem.setAmount(parkingPlace.getMonthlyFee());
        billItem.setOwner(owner);
        billItems.add(billItem);
    }
    //endregion

    /**
     * 获得主键
     *
     * @return 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    //region Setters
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获得车牌号
     *
     * @return 车牌号
     */
    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    /**
     * 获得业主
     *
     * @return 业主
     */
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Owner.class)
    @JoinColumn(name = "owner_id", nullable = false)
    public Owner getOwner() {
        return owner;
    }
    //endregion

    //region Inner Enum

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
    //endregion

    /**
     * 获得车辆状态
     *
     * @return 车辆状态
     * @see cn.edu.xmu.comm.entity.Car.CarStatus
     */
    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

    /**
     * 获得拥有的车位
     *
     * @return 拥有的车位
     */
    @OneToOne(fetch = FetchType.EAGER, targetEntity = ParkingPlace.class)
    @JoinColumn(name = "park_place_id", nullable = true)
    public ParkingPlace getParkingPlace() {
        return parkingPlace;
    }

    public void setParkingPlace(ParkingPlace parkingPlace) {
        this.parkingPlace = parkingPlace;
    }
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
    //endregion

}
