package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.entity.Building;
import cn.edu.xmu.comm.entity.Floor;
import org.springframework.stereotype.Repository;

/**
 * 楼层DAO
 * Created by Roger on 2014/12/9 0009.
 *
 * @author Mengmeng Yang
 * @version 2014-12-22
 */
@Repository
public class FloorDAO extends BaseDAO<Floor, Integer> {

    /**
     * 通过楼层号获取某小区楼宇
     *
     * @param no       楼层号
     * @param building 所属楼宇
     * @return 楼层
     */
    public Floor getByNo(Integer no, Building building) {
        return getByQL("from Floor where building = :p1 and no = :p2", new Parameter(building, no));
    }

}
