package cn.edu.xmu.comm.dao.impl;

import cn.edu.xmu.comm.commons.persistence.BaseDaoImpl;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.dao.TokenDAO;
import cn.edu.xmu.comm.entity.Token;
import org.springframework.stereotype.Repository;

/**
 * 临时密码DAO
 *
 * @author Mengmeng Yang
 * @version 12/22/2014
 */
@Repository
public class TokenDaoImpl extends BaseDaoImpl<Token, String> implements TokenDAO {

    /**
     * 通过用户id删除token
     *
     * @param uid 用户id
     * @return ？
     */
    @Override
    public int deleteByUid(Integer uid) {
        String ql = "delete Token t where t.uid = :p1";
        return createQuery(ql, new Parameter(uid)).executeUpdate();
    }

}
