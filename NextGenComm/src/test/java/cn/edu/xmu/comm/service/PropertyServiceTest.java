package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.entity.*;
import junit.framework.TestCase;
import org.apache.commons.lang3.Validate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 测试PropertyService
 *
 * @author Mengmeng Yang
 * @version 2014-12-24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-context.xml"})
public class PropertyServiceTest extends TestCase {

    @Resource
    private PropertyService propertyService;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAddCommunity() throws Exception {
        propertyService.addCommunity("五缘公寓");
        propertyService.addCommunity("公寓1");
        propertyService.addCommunity("公寓2");
        propertyService.addCommunity("公寓3");
        propertyService.addCommunity("公寓4");
        propertyService.addCommunity("公寓5");
        propertyService.addCommunity("公寓6");
        propertyService.addCommunity("公寓7");
        propertyService.addCommunity("公寓8");
        propertyService.addCommunity("公寓9");
        propertyService.addCommunity("公寓10");
        propertyService.addCommunity("公寓11");
        propertyService.addCommunity("公寓12");
        propertyService.addCommunity("公寓13");

    }

    @Test
    public void testAddBuilding() throws Exception {
        Community community = propertyService.getCommunity("五缘公寓");
//        propertyService.addBuilding(1, community);
    }

    @Test
    public void testAddBuilding1() throws Exception {
        Community community = propertyService.getCommunity("五缘公寓");
//        propertyService.addBuilding(2, "二号楼", community);
    }

    @Test
    public void testAddBuildingBatch() throws Exception {
        Community community = propertyService.getCommunity("五缘公寓");
        propertyService.addBuilding(3, 14, community);
    }

    @Test
    public void testAddFloor() throws Exception {
        Community community = propertyService.getCommunity("五缘公寓");
        Building building = propertyService.getBuildingByNo(1, community);
//        propertyService.addFloor(1, building);
    }

    @Test
    public void testAddFloorBatch() throws Exception {
        Community community = propertyService.getCommunity("五缘公寓");
        Building building = propertyService.getBuildingByNo(1, community);
        //propertyService.addFloorBatch(2, 10, building);
    }

    @Test
    public void testAddRoom() throws Exception {
        Community community = propertyService.getCommunity("五缘公寓");
        Building building = propertyService.getBuildingByNo(3, community);
        Floor floor = propertyService.getFloorByNo(1, building);
        propertyService.addRoom("101", 100.0, floor);
    }

    @Test
    public void testAddRoomBatch() throws Exception {
        Community community = propertyService.getCommunity("五缘公寓");
        Building building = propertyService.getBuildingByNo(1, community);
        Floor floor = propertyService.getFloorByNo(1, building);
        propertyService.addRoomBatch(102, 110, 100.0, floor);
    }

    @Test
    public void testLazyLoad() {
        Community community = propertyService.getCommunity("五缘公寓");
        Building building = propertyService.getBuildingByNo(1, community);
        Floor floor = propertyService.getFloorByNo(1, building);
        for (Room room : floor.getRoomList()) {
            System.out.println(room.getFullName());
        }
    }

    @Test
    public void testAddOwner() {
        Community community = propertyService.getCommunity("五缘公寓");
        //Building building = propertyService.getBuildingByNo(1, community);
        //Floor floor = propertyService.getFloorByNo(1, building);
        //Room room1 = propertyService.getRoomByNo("101", floor);
        //Room room2 = propertyService.getRoomByNo("102", floor);
        Owner owner = propertyService.addOwner("wyh", "123", "王耀华", community);
        /*try {
            owner.addRoom(room1);
        } catch (DifferentCommunityException e) {
            e.printStackTrace();
        }*/
        propertyService.addOwner("lyj", "123", "陆垚杰", community);
    }

    @Test
    public void testValidate() {
        foo();
    }

    public void foo() {
        Validate.isTrue(1 == 2, "1!=2");
    }

}