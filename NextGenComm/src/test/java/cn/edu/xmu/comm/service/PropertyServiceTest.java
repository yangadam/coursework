package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.entity.Building;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Floor;
import cn.edu.xmu.comm.entity.Room;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


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
    }

    @Test
    public void testAddBuilding() throws Exception {
        Community community = propertyService.getCommunityByName("五缘公寓");
        propertyService.addBuilding(1, community);
    }

    @Test
    public void testAddBuilding1() throws Exception {
        Community community = propertyService.getCommunityByName("五缘公寓");
        propertyService.addBuilding(2, "二号楼", community);
    }

    @Test
    public void testAddBuildingBatch() throws Exception {
        Community community = propertyService.getCommunityByName("五缘公寓");
        propertyService.addBuildingBatch(3, 14, community);
    }

    @Test
    public void testAddFloor() throws Exception {
        Community community = propertyService.getCommunityByName("五缘公寓");
        Building building = propertyService.getBuildingByNo(1, community);
        propertyService.addFloor(1, building);
    }

    @Test
    public void testAddFloorBatch() throws Exception {
        Community community = propertyService.getCommunityByName("五缘公寓");
        Building building = propertyService.getBuildingByNo(1, community);
        propertyService.addFloorBatch(2, 10, building);
    }

    @Test
    public void testAddRoom() throws Exception {
        Community community = propertyService.getCommunityByName("五缘公寓");
        Building building = propertyService.getBuildingByNo(1, community);
        Floor floor = propertyService.getFloorByNo(1, building);
        propertyService.addRoom("101", 100.0, floor);
    }

    @Test
    public void testAddRoomBatch() throws Exception {
        Community community = propertyService.getCommunityByName("五缘公寓");
        Building building = propertyService.getBuildingByNo(1, community);
        Floor floor = propertyService.getFloorByNo(1, building);
        propertyService.addRoomBatch(102, 110, 100.0, floor);
    }

    @Test
    public void testLazyLoad() {
        Community community = propertyService.getCommunityByName("五缘公寓");
        Building building = community.getBuilding(1);
        Floor floor = propertyService.getFloorByNo(1, building);
        for (Room room : floor.getRoomList()) {
            System.out.println(room.getFullName());
        }
    }

}