package cn.edu.xmu.comm.domain;

import javax.persistence.*;

/**
 * Created by Roger on 2014/12/8 0008.
 */
@Entity
public class Owner extends User {

    @Column
    private String name;

//    List<Room> roomList;
//    List<Car> carList;
//    //公维金是单独交的，但是一起算，交到不同的账户。
//    List<BillItem> unpaidBills;
//
//    public void generateBill() {
//
//        for (Room room : this.roomList) {
//            room.generateRoom(this.unpaidBills);
//        }
//
//        for (Car car : this.carList) {
//            car.generateCar(this.unpaidBills);
//        }
//    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
