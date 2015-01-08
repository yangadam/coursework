package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.entity.Building;
import cn.edu.xmu.comm.entity.Floor;

import java.util.List;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/8/2015 0008
 */
public interface FloorDAO extends BaseDAO<Floor, Integer> {
    /**
     * 通过楼层号获取某小区楼宇
     *
     * @param no       楼层号
     * @param building 所属楼宇
     * @return 楼层
     */
    Floor getByNo(Integer no, Building building);

    /**
     * 通过楼宇id获得楼层
     *
     * @param buildId 楼宇id
     * @return 楼层号列表
     */
    @SuppressWarnings("unchecked")
    List<String[]> getIdsAndNos(Integer buildId);
}
