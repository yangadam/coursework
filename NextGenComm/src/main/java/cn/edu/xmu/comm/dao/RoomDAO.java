package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.entity.Floor;
import cn.edu.xmu.comm.entity.Room;
import org.springframework.stereotype.Repository;

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
     * 通过楼层号获取某小区楼宇
     *
     * @param no    房间号
     * @param floor 楼层
     * @return 房间
     */
    public Room getByNo(String no, Floor floor) {
        return getByQL("from Room where floor = :p1 and no = :p2", new Parameter(floor, no));
    }

}
