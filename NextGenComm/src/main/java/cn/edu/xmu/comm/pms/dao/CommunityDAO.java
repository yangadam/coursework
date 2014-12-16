package cn.edu.xmu.comm.pms.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.pms.entity.Community;
import org.springframework.stereotype.Repository;

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

}
