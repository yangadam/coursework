package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.commons.utils.CastUtils;
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
public class RoomDAO extends BaseDAO<Room, Integer> {

    /**
     * 通过房间号号获取某楼层的房间
     *
     * @param no    房间号
     * @param floor 楼层
     * @return 房间
     */
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
    public List<Room> getAll(Community community) {
        String ql = "select r from Room r where r.floor.building.community = :p1";
        return searchByQL(ql, new Parameter(community));
    }

    public List<String[]> getVacantRoomNos(Integer floorId) {
        String ql = "select r.id, r.no from Room r where r.floor.id = :p1 and r.owner is null";
        List list = getAttrsByQL(ql, new Parameter(floorId));
        return CastUtils.castToListStringArray(list);
    }

    public List<String[]> getNonVacantRoomNos(Integer floorId) {
        String ql = "select r.id, r.no from Room r where r.floor.id = :p1 and r.owner is not null";
        List list = getAttrsByQL(ql, new Parameter(floorId));
        return CastUtils.castToListStringArray(list);
    }

    public void delete(Community community) {
        String ql = "select r from Room r where r.floor.building.community = :p1";
        List<Room> list = searchByQL(ql, new Parameter(community));
        for (Room room : list) {
            delete(room);
        }
    }
}
