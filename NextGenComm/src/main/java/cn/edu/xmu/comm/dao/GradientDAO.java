package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Gradient;

import java.util.List;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/8/2015 0008
 */
public interface GradientDAO extends BaseDAO<Gradient, Integer> {
    /**
     * 获取所有梯度
     *
     * @param community 小区
     * @return 梯度列表
     */
    List<Gradient> getAll(Community community);
}
