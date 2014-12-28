package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.ParkBill;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Roger on 2014/12/23 0023.
 */
@Repository
public class ParkBillDAO extends BaseDAO<ParkBill, Integer> {

    /**
     * 查询社区中临时停车的数量
     *
     * @param community 指定社区
     * @return 该社区中未完成的停车单，即临时停车数量
     */
    public Integer getSizeOfUnfinishedBill(Community community) {
        String hql = "from ParkBill where community = :p1 and endTime is null";
        return count(hql, new Parameter(community)).intValue();
    }

    /**
     * 依据社区和车辆号查找未完成的停车账单
     *
     * @param community 社区
     * @param license 车牌
     * @return 指定的停车单
     */
    public ParkBill getUnfinishedParkBill(Community community, String license) {
        String hql = "from ParkBill where community = :p1 and license = :p2 and endTime is null";
        return getByQL(hql, new Parameter(community, license));
    }

    /**
     * 判断一辆车是否在指定小区是否有未完成的停车单
     *
     * @param community 社区
     * @param license 车牌
     * @return true 有 false 无
     */
    public boolean carHasUnfinishBill(Community community, String license) {
        String hql = "from ParkBill where community = :p1 and license = :p2 and endTime is null";
        return count(hql, new Parameter(community, license)) > 0;
    }
}
