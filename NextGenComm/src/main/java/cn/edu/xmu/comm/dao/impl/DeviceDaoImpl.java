package cn.edu.xmu.comm.dao.impl;

import cn.edu.xmu.comm.commons.persistence.BaseDaoImpl;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.dao.DeviceDAO;
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
public class DeviceDaoImpl extends BaseDaoImpl<Device, Integer> implements DeviceDAO {

    /**
     * 将梯度应用到所有类型相同的私有表
     *
     * @param gradient  梯度
     * @param community 小区
     */
    @Override
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
    @Override
    public void applyShareGradient(Gradient gradient, Community community) {
        String ql = "update Device d set d.gradient = :p1 where " +
                "d.community = :p2 and d.shareType != null and d.type = :p3";
        Parameter param = new Parameter(gradient, community, gradient.getType());
        createQuery(ql, param).executeUpdate();
    }

    /**
     * 获取已录入设备
     *
     * @param community 小区
     * @return 设备列表
     */
    @Override
    public List<Device> getInputedDevice(Community community) {
        String ql = "select new Device(d.id, d.no, d.currentValue, d.lastValue, d.type) " +
                "from Device d where d.community = :p1 and d.isCalculated = :p2";
        return searchByQL(ql, new Parameter(community, false));
    }

    /**
     * 模糊搜索返回设备id，编号，类型，当前值
     *
     * @param term      关键字
     * @param community 小区
     * @return 设备id，编号，类型，当前值列表
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<String[]> buzzSearch(String term, Community community) {
        String ql = "select d.id, d.no, d.type, d.currentValue from Device d " +
                "where d.community = :p1 and d.no like :p2 and d.isCalculated = :p3";
        return getAttrsByQL(ql, new Parameter(community, '%' + term + '%', Boolean.TRUE));
    }

    /**
     * 获取已录入设备数量
     *
     * @param community 小区
     * @return 已录入设备数量
     */
    @Override
    public Long getInputedCount(Community community) {
        String ql = "from Device d where d.community = :p1 and d.isCalculated = :p2";
        return count(ql, new Parameter(community, Boolean.FALSE));
    }

    /**
     * 获取未录入设备数量
     *
     * @param community 小区
     * @return 未录入设备数量
     */
    @Override
    public Long getCount(Community community) {
        String ql = "from Device d where d.community = :p1";
        return count(ql, new Parameter(community));
    }

    /**
     * 获得所有设备
     *
     * @param community 小区
     * @return 设备列表
     */
    @Override
    public List<Device> getAll(Community community) {
        String ql = "select from Device d where d.community = :p1";
        return searchByQL(ql, new Parameter(community));
    }

}
