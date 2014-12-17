package cn.edu.xmu.comm.hql;

import cn.edu.xmu.comm.entity.*;
import cn.edu.xmu.comm.service.FinanceService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Yummy on 11/29/2014 0029.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
@TransactionConfiguration
@Transactional
public class TestHql {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private FinanceService financeService;

    @Test
    public void CalculateTest() {
        financeService.generateBill();
    }

    public PublicFund newPublicFund() {
        PublicFund publicFund = new PublicFund();
        publicFund.setAccount("luyaojie1993@foxmail.com");
        publicFund.setBalance(BigDecimal.valueOf(100));
        publicFund.setChargePerRoom(BigDecimal.valueOf(10));
        publicFund.setThreshold(BigDecimal.valueOf(150));
        return publicFund;
    }

    @Test
    public void InsertCommunity() {
        Session session = sessionFactory.openSession();

        PublicFund publicFund = newPublicFund();
        Community community = newCommunity(publicFund);
        Building building = newBuilding(community);
        Floor floor = newFloor(building);
        Owner owner = newOwner();
        Room room = newRoom(floor, owner);
        ParkPlace parkPlace = newParkPlace(community);
        Car car = newCar(owner, parkPlace);
        Device device1 = newDevice1(floor);
        Device device2 = newDevice2(room);

        session.saveOrUpdate(community);
        session.saveOrUpdate(owner);
        session.saveOrUpdate(parkPlace);
        session.saveOrUpdate(car);
        session.saveOrUpdate(building);
        session.saveOrUpdate(floor);
        session.saveOrUpdate(room);
        session.saveOrUpdate(device1);
        session.saveOrUpdate(device2);

        session.flush();
        session.close();
        System.out.print("DONE");
    }

    public Device newDevice1(Floor floor) {
        Device device = new Device();
        device.setNo("1");
        device.setProperty(floor);
        device.setShareType("cn.edu.xmu.comm.commons.CountShareCalculator");
        device.setType(Device.WATER);

        List<DeviceVaule> deviceVaules = new ArrayList<DeviceVaule>();
        DeviceVaule d1 = new DeviceVaule();
        DeviceVaule d2 = new DeviceVaule();
        d1.setDate(new Date());
        d2.setDate(new Date());
        d1.setValue(BigDecimal.valueOf(0));
        d2.setValue(BigDecimal.valueOf(100));
        deviceVaules.add(d1);
        deviceVaules.add(d2);
        device.getGradient().put(BigDecimal.valueOf(1000000), BigDecimal.valueOf(100));
        device.setValues(deviceVaules);
        return device;
    }

    public Device newDevice2(Room room) {
        Device device = new Device();
        device.setNo("1");
        device.setProperty(room);
        device.setShareType("cn.edu.xmu.comm.commons.CountShareCalculator");
        device.setType(Device.WATER);

        List<DeviceVaule> deviceVaules = new ArrayList<DeviceVaule>();
        DeviceVaule d1 = new DeviceVaule();
        DeviceVaule d2 = new DeviceVaule();
        d1.setDate(new Date());
        d2.setDate(new Date());
        d1.setValue(BigDecimal.valueOf(0));
        d2.setValue(BigDecimal.valueOf(100));
        deviceVaules.add(d1);
        deviceVaules.add(d2);
        device.getGradient().put(BigDecimal.valueOf(1000000), BigDecimal.valueOf(100));
        device.setValues(deviceVaules);
        return device;
    }

    public ParkPlace newParkPlace(Community community) {
        ParkPlace parkPlace = new ParkPlace();
        parkPlace.setCommunity(community);
        parkPlace.setMonthlyFee(BigDecimal.valueOf(10.0));
        parkPlace.setPosition("A1");
        return parkPlace;
    }

    public Community newCommunity(PublicFund publicFund) {
        Community community = new Community();
        community.setName("NextGenComm");
        community.setManageFeeType("cn.edu.xmu.comm.commons.AreaManageFeeCalculator");
        community.setManageFee(BigDecimal.valueOf(10.0));
        community.setGarbageFeeType("cn.edu.xmu.comm.commons.FixGarbageFeeCalculator");
        community.setGarbageFee(BigDecimal.valueOf(5.0));
        community.setPublicFund(publicFund);
        return community;
    }

    public Building newBuilding(Community community) {
        Building building = new Building();
        building.setNo(13);
        building.setName("海韵十三");
        building.setHouseArea(22.0);
        building.setHouseCount(1);
        building.setUsedHouseArea(22.0);
        building.setUsedHouseCount(1);
        building.setCommunity(community);
        return building;
    }

    public Room newRoom(Floor floor, Owner owner) {
        Room room = new Room();
        room.setFloor(floor);
        room.setBuilding(floor.getBuilding());
        room.setCommunity(floor.getBuilding().getCommunity());
        room.setFullName("310");
        room.setOwner(owner);
        room.setHouseArea(22.0);
        room.setHouseCount(1);
        room.setUsedHouseArea(22.0);
        room.setUsedHouseCount(1);
        return room;
    }

    public Floor newFloor(Building building) {
        Floor floor = new Floor();
        floor.setNo(3);
        floor.setHouseArea(22.0);
        floor.setHouseCount(1);
        floor.setUsedHouseArea(22.0);
        floor.setUsedHouseCount(1);
        floor.setBuilding(building);
        return floor;
    }

    public Owner newOwner() {
        Owner owner = new Owner();
        owner.setName("陆垚杰");
        owner.setPassword("luyaojie");
        owner.setUsername("lyj");
        owner.setSalt("1");
        return owner;
    }

    public Car newCar(Owner owner, ParkPlace parkPlace) {
        Car car = new Car();
        car.setLicense("浙A.SN253");
        car.setStatus(2);
        car.setParkPlace(parkPlace);
        car.setOwner(owner);
        return car;
    }

}
