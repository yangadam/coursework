package cn.edu.xmu.comm.dao.impl;

import cn.edu.xmu.comm.commons.persistence.BaseDaoImpl;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.dao.RoomDAO;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Floor;
import cn.edu.xmu.comm.entity.Room;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 房间DAO
 * Created by Roger on 2014/12/7 0007.
 *
 * @author Mengmeng Yang
 * @version 2014-12-22
 */
@Repository
public class RoomDaoImpl extends BaseDaoImpl<Room, Integer> implements RoomDAO {

    /**
     * 通过房间号号获取某楼层的房间
     *
     * @param no    房间号
     * @param floor 楼层
     * @return 房间
     */
    @Override
    public Room getByNo(String no, Floor floor) {
        String ql = "select r from Room r where r.floor = :p1 and r.no = :p2";
        return getByQL(ql, new Parameter(floor, no));
    }

    /**
     * 获取某个小区所有房间
     *
     * @param community 小区
     * @return 房间列表
     */
    @Override
    public List<Room> getAll(Community community) {
        String ql = "select r from Room r where r.floor.building.community = :p1";
        return searchByQL(ql, new Parameter(community));
    }

    /**
     * 获得空闲房间号
     *
     * @param floorId 楼层号
     * @return 房间号列表
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<String[]> getVacantRoomNos(Integer floorId) {
        String ql = "select r.id, r.no from Room r where r.floor.id = :p1 and r.owner is null";
        return getAttrsByQL(ql, new Parameter(floorId));
    }

    /**
     * 获得非空房间号
     *
     * @param floorId 楼层号
     * @return 房间号列表
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<String[]> getNonVacantRoomNos(Integer floorId) {
        String ql = "select r.id, r.no from Room r where r.floor.id = :p1 and r.owner is not null";
        return getAttrsByQL(ql, new Parameter(floorId));
    }

    /**
     * 删除所有房间
     *
     * @param community 小区
     */
    @Override
    public void delete(Community community) {
        String ql = "delete from Room r where r.floor.building.community = :p1";
        createQuery(ql, new Parameter(community)).executeUpdate();
    }

}
