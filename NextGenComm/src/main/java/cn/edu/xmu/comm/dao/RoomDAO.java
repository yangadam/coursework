package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Floor;
import cn.edu.xmu.comm.entity.Room;

import java.util.List;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/5/2015 0005
 */
public interface RoomDAO extends BaseDAO<Room, Integer> {
    /**
     * 通过房间号号获取某楼层的房间
     *
     * @param no    房间号
     * @param floor 楼层
     * @return 房间
     */
    Room getByNo(String no, Floor floor);

    /**
     * 获取某个小区所有房间
     *
     * @param community 小区
     * @return 房间列表
     */
    List<Room> getAll(Community community);

    List<String[]> getVacantRoomNos(Integer floorId);

    List<String[]> getNonVacantRoomNos(Integer floorId);

    void delete(Community community);
}
