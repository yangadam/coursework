package cn.edu.xmu.comm.repository;

import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    /**
     * 获得空闲房间号
     *
     * @param floorId 楼层号
     * @return 房间号列表
     */
    @Query("select id, no from Room where floor.id = :floorId and owner is null")
    List<String[]> getVacantRoomNos(Integer floorId);

    /**
     * 获得非空房间号
     *
     * @param floorId 楼层号
     * @return 房间号列表
     */
    @Query("select id, no from Room where floor.id = :floorId and owner is not null")
    List<String[]> getNonVacantRoomNos(Integer floorId);

    /**
     * 删除所有房间
     *
     * @param community 小区
     */
    void deleteByCommunity(Community community);
}
