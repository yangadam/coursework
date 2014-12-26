package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.entity.Community;
import org.springframework.stereotype.Repository;

/**
 * 小区DAO
 * Created by Roger on 2014/12/9 0009.
 *
 * @author Mengmeng Yang
 * @version 2014-12-22
 */
@Repository
public class CommunityDAO extends BaseDAO<Community, Integer> {

    /**
     * 通过名字获得小区
     *
     * @param name 小区名字
     * @return 小区
     */
    public Community getByName(String name) {
        return getByQL("select c from Community c where c.name = :p1", new Parameter(name));
    }

}
