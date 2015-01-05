package cn.edu.xmu.comm.dao.impl;

import cn.edu.xmu.comm.commons.persistence.BaseDaoImpl;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.commons.utils.CastUtils;
import cn.edu.xmu.comm.dao.FloorDAO;
import cn.edu.xmu.comm.entity.Building;
import cn.edu.xmu.comm.entity.Floor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 楼层DAO
 * Created by Roger on 2014/12/9 0009.
 *
 * @author Mengmeng Yang
 * @version 2014-12-22
 */
@Repository
public class FloorDaoImpl extends BaseDaoImpl<Floor, Integer> implements FloorDAO {

    /**
     * 通过楼层号获取某小区楼宇
     *
     * @param no       楼层号
     * @param building 所属楼宇
     * @return 楼层
     */
    @Override
    public Floor getByNo(Integer no, Building building) {
        String ql = "select f from Floor f where f.building = :p1 and f.no = :p2";
        return getByQL(ql, new Parameter(building, no));
    }

    /**
     * @param buildId
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<String[]> getIdsAndNos(Integer buildId) {
        String ql = "select f.id, f.no from Floor f where f.building.id = :p1";
        List list = getAttrsByQL(ql, new Parameter(buildId));
        return CastUtils.castToListStringArray(list);
    }
}
