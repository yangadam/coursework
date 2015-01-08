package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.entity.Token;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/8/2015 0008
 */
public interface TokenDAO extends BaseDAO<Token, String> {
    /**
     * 通过用户id删除token
     *
     * @param uid 用户id
     * @return 受影响的记录数
     */
    int deleteByUid(Integer uid);
}
