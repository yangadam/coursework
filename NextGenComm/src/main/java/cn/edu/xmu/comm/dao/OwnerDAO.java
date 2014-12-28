package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Owner;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Roger on 2014/12/8 0008.
 */
@Repository
public class OwnerDAO extends BaseDAO<Owner, Integer> {

    /**
     * 获得某小区的所有业主
     *
     * @param community 所属小区
     * @return 业主列表
     */
    public List<Owner> getAll(Community community) {
        return searchByQL("from Owner where community = :p1", new Parameter(community));
    }

    /**
     * 依据id获得某业主
     *
     * @param name 业主姓名
     * @return 单个业主
     */
    public Owner getById(String name) {
        return getByQL("from Owner where name = :p1", new Parameter(name));
    }

    /**
     * 依据姓名获得某业主
     *
     * @param community 社区
     * @param name 业主姓名
     * @return 业主列表
     */
    public List<Owner> getByName(Community community, String name) {
        return searchByQL("from Owner where name = :p1 and community = :p2", new Parameter(name, community));
    }
}
