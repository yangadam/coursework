package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.entity.Token;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/5/2015 0005
 */
public interface TokenDAO extends BaseDAO<Token, String> {
    /**
     * 通过用户id删除token
     *
     * @param uid 用户id
     * @return ？
     */
    int deleteByUid(Integer uid);

}
