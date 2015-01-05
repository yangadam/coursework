package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.entity.Community;

import java.util.List;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/5/2015 0005
 */
public interface CommunityDAO extends BaseDAO<Community, Integer> {

    /**
     * 获取所有小区的名字
     *
     * @return 小区名字的列表
     */
    @SuppressWarnings("unchecked")
    List<String> getNames();

    Community getByName(String commName);

}
