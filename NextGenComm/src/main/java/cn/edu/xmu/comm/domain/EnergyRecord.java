package cn.edu.xmu.comm.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Yummy on 11/29/2014 0029.
 */
@Entity
@Table(name = "ENERGY_RECORD")
public class EnergyRecord {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "BUILDING_NO")
    private int buildingNo;

    @Column(name = "FLOOR_NO")
    private int floorNo;

    @Column(name = "ROOM_NO")
    private int roomNo;

    @Column(name = "CURRENT_WATER_VALUE")
    private int currentWaterValue;

    @Column(name = "CURRENT_ELECTRICITY_VALUE")
    private int currentElectricityValue;

    @Column(name = "CREATED_DATE")
    private Timestamp createdDate;


    //========== None-parameter Constructor ==========
    public EnergyRecord() {
        createdDate = new Timestamp(new Date().getTime());
    }


    //========== Getters and setters ==========
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(int buildingNo) {
        this.buildingNo = buildingNo;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public int getCurrentWaterValue() {
        return currentWaterValue;
    }

    public void setCurrentWaterValue(int currentWaterValue) {
        this.currentWaterValue = currentWaterValue;
    }

    public int getCurrentElectricityValue() {
        return currentElectricityValue;
    }

    public void setCurrentElectricityValue(int currentElectricityValue) {
        this.currentElectricityValue = currentElectricityValue;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

}
