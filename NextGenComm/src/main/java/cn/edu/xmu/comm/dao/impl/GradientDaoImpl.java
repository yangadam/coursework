package cn.edu.xmu.comm.dao.impl;

import cn.edu.xmu.comm.commons.persistence.BaseDaoImpl;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.dao.GradientDAO;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Gradient;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 梯度DAO
 *
 * @author Mengmeng Yang
 * @version 12/26/2014
 */
@Repository
public class GradientDaoImpl extends BaseDaoImpl<Gradient, Integer> implements GradientDAO {

    @Override
    public List<Gradient> getAll(Community community) {
        String ql = "select g from Gradient g where g.community = :p1";
        return searchByQL(ql, new Parameter(community));
    }
}
