package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.commons.utils.CastUtils;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Device;
import cn.edu.xmu.comm.entity.Gradient;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 设备DAO
 * Created by Roger on 2014/12/9 0009.
 *
 * @author Mengmeng Yang
 * @version 2014-12-9
 */
@Repository
public class DeviceDAO extends BaseDAO<Device, Integer> {

    /**
     * 将梯度应用到所有类型相同的私有表
     *
     * @param gradient  梯度
     * @param community 小区
     */
    public void applyPrivateGradient(Gradient gradient, Community community) {
        String ql = "update Device d set d.gradient = :p1 where " +
                "d.community = :p2 and d.shareType = null and d.type = :p3";
        Parameter param = new Parameter(gradient, community, gradient.getType());
        createQuery(ql, param).executeUpdate();
    }

    /**
     * 将梯度应用到所有类型相同的公摊表
     *
     * @param gradient  梯度
     * @param community 小区
     */
    public void applyShareGradient(Gradient gradient, Community community) {
        String ql = "update Device d set d.gradient = :p1 where " +
                "d.community = :p2 and d.shareType != null and d.type = :p3";
        Parameter param = new Parameter(gradient, community, gradient.getType());
        createQuery(ql, param).executeUpdate();
    }

    public List<Device> getCanCalculateDevice(Community community) {
        String ql = "select new Device(d.id, d.currentValue, d.lastValue, d.type) " +
                "from Device d where d.community = :p1 and d.isCalculated = :p2";
        return searchByQL(ql, new Parameter(community, false));
    }

    public Device getByNo(Community community, String deviceNo) {
        String ql = "select d from Device d where d.community = :p1 and d.deviceNo = :p2 and d.isCalculated = :p3";
        return getByQL(ql, new Parameter(community, deviceNo, true));
    }

    public List<String[]> buzzSearch(String term, Community community) {
        String ql = "select d.id, d.no, d.type, d.currentValue from Device d " +
                "where d.community = :p1 and d.no like :p2 and d.isCalculated = :p3";
        List list = getAttrsByQL(ql, new Parameter(community, '%' + term + '%', Boolean.TRUE));
        return CastUtils.castToListStringArray(list);
    }

    public Long getInputCount(Community community) {
        String ql = "from Device d where d.community = :p1 and d.isCalculated = :p2";
        return count(ql, new Parameter(community, Boolean.FALSE));
    }

    public Long getCount(Community community) {
        String ql = "from Device d where d.community = :p1";
        return count(ql, new Parameter(community));
    }

}
