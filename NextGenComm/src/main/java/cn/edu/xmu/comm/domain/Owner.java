package cn.edu.xmu.comm.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Roger on 2014/12/8 0008.
 */
@Entity
public class Owner extends User {

    //region Instance Variables
    /**
     * 拥有的房间列表
     */
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Room.class, mappedBy = "owner")
    private List<Room> roomList;

    /**
     * 拥有的车辆列表
     */
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Car.class, mappedBy = "owner")
    private List<Car> carList;

    /**
     * 未支付的账单项列表
     * （注意：公维金是单独交的，但是一起算，交到不同的账户。）
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            targetEntity = BillItem.class, mappedBy = "owner")
    private List<BillItem> unpaidBills;
    //endregion

    //region Public Methods

    /**
     * 生成账单
     */
    public void generateBill() {

        for (Room room : this.roomList) {
            room.generateRoom(this.unpaidBills);
        }

        for (Car car : this.carList) {
            car.generateCar(this.unpaidBills);
        }

    }
    //endregion

    //region Getters and Setters
    public List<Room> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public List<BillItem> getUnpaidBills() {
        return unpaidBills;
    }

    public void setUnpaidBills(List<BillItem> unpaidBills) {
        this.unpaidBills = unpaidBills;
    }
    //endregion

}
