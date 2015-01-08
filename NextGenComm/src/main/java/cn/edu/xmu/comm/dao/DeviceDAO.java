package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Device;
import cn.edu.xmu.comm.entity.Gradient;

import java.util.List;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/8/2015 0008
 */
public interface DeviceDAO extends BaseDAO<Device, Integer> {
    /**
     * 将梯度应用到所有类型相同的私有表
     *
     * @param gradient  梯度
     * @param community 小区
     */
    void applyPrivateGradient(Gradient gradient, Community community);

    /**
     * 将梯度应用到所有类型相同的公摊表
     *
     * @param gradient  梯度
     * @param community 小区
     */
    void applyShareGradient(Gradient gradient, Community community);

    /**
     * 获取已录入设备
     *
     * @param community 小区
     * @return 设备列表
     */
    List<Device> getInputedDevice(Community community);

    /**
     * 模糊搜索返回设备id，编号，类型，当前值
     *
     * @param term      关键字
     * @param community 小区
     * @return 设备id，编号，类型，当前值列表
     */
    @SuppressWarnings("unchecked")
    List<String[]> buzzSearch(String term, Community community);

    /**
     * 获取已录入设备数量
     *
     * @param community 小区
     * @return 已录入设备数量
     */
    Long getInputedCount(Community community);

    /**
     * 获取未录入设备数量
     *
     * @param community 小区
     * @return 未录入设备数量
     */
    Long getCount(Community community);

    /**
     * 获得所有设备
     *
     * @param community 小区
     * @return 设备列表
     */
    List<Device> getAll(Community community);
}
