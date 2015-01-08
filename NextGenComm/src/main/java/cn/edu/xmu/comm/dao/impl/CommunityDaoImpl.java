package cn.edu.xmu.comm.dao.impl;

import cn.edu.xmu.comm.commons.persistence.BaseDaoImpl;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.dao.CommunityDAO;
import cn.edu.xmu.comm.entity.Community;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 小区DAO
 * Created by Roger on 2014/12/9 0009.
 *
 * @author Mengmeng Yang
 * @version 2014-12-22
 */
@Repository
public class CommunityDaoImpl extends BaseDaoImpl<Community, Integer> implements CommunityDAO {

    /**
     * 获取所有小区的名字
     *
     * @return 小区名字的列表
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<String> getNames() {
        String ql = "select c.name from Community c where c.director is null";
        return getAttrsByQL(ql, null);
    }

    /**
     * 通过名字查找小区
     *
     * @param commName 名字
     * @return 小区
     */
    @Override
    public Community getByName(String commName) {
        String ql = "select c from Community c where c.name = :p1";
        return getByQL(ql, new Parameter(commName));
    }

}
