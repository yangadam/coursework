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
 * @version 1/5/2015 0005
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

    List<Device> getInputedDevice(Community community);

    Device get(Community community, String deviceNo);

    List<String[]> buzzSearch(String term, Community community);

    Long getInputedCount(Community community);

    Long getCount(Community community);

    List<Device> getAll(Community community);
}
