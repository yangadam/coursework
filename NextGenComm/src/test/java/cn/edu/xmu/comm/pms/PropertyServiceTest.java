package cn.edu.xmu.comm.pms;

import cn.edu.xmu.comm.pms.entity.Building;
import cn.edu.xmu.comm.pms.entity.Community;
import cn.edu.xmu.comm.pms.entity.Floor;
import cn.edu.xmu.comm.pms.service.PropertyService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Yummy on 12/16/2014 0016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring-context*.xml"})
public class PropertyServiceTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private PropertyService propertyService;

    @Test
    public void testLazy() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Community community = (Community) session.createQuery("from Community where name = :p1").setParameter("p1", "海韵公寓")
                .uniqueResult();

        tx.commit();
        List<Building> buildings = community.getBuildingList();

        session.close();
        Building building = buildings.get(0);

    }

    @Test
    public void testAddFloor() {
        Community community = propertyService.getCommunityByName("海韵公寓");
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Building building = community.getBuildingByNo(13);
        Floor floor = newFloor(building);
        tx.commit();
        session.close();
        propertyService.addFloor(floor);
    }

    @Test
    public void testAddBuilding() {
        Community community = propertyService.getCommunityByName("海韵公寓");
        Building building = newBuilding(community);
        propertyService.addBuilding(building);
    }

    @Test
    public void testAddCommunity() {
        Community community = newCommunity();
        propertyService.addCommunity(community);
    }

    private Community newCommunity() {
        Community community = new Community();
        community.setName("海韵公寓");
        return community;
    }

    private Building newBuilding(Community community) {
        Building building = new Building();
        building.setNo(13);
        building.setName("海韵4");
        building.setCommunity(community);
        return building;
    }

    private Floor newFloor(Building building) {
        Floor floor = new Floor();
        floor.setBuilding(building);
        floor.setNo(4);
        return floor;
    }

}
