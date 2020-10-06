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
 * @version 1/8/2015 0008
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

    /**
     * 获得空闲房间号
     *
     * @param floorId 楼层号
     * @return 房间号列表
     */
    @SuppressWarnings("unchecked")
    List<String[]> getVacantRoomNos(Integer floorId);

    /**
     * 获得非空房间号
     *
     * @param floorId 楼层号
     * @return 房间号列表
     */
    @SuppressWarnings("unchecked")
    List<String[]> getNonVacantRoomNos(Integer floorId);

    /**
     * 删除所有房间
     *
     * @param community 小区
     */
    void delete(Community community);
}
