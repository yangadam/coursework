package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.entity.Community;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Roger on 2014/12/9 0009.
 */
@Repository
public class CommunityDAO extends BaseDAO<Community, Integer> {

    public Community getByName(String name) {
        Community community = getByHql("from Community where name = :p1", new Parameter(name));
        //Hibernate.initialize(community.getBuildingList());
        return community;
    }

    public List<Community> getAll() {
        return searchByHql("from Community ", new Parameter());
    }

}
