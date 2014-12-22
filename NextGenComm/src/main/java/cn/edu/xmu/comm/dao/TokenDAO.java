package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.entity.Token;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 * 临时密码DAO
 *
 * @author Mengmeng Yang
 * @version 12/22/2014
 */
@Repository
public class TokenDAO extends BaseDAO<Token, String> {

    /**
     * 通过用户id删除token
     *
     * @param uid 用户id
     * @return ？
     */
    public int deleteByUid(Integer uid) {
        Query query = createQuery("delete Token where uid = :p1", new Parameter(uid));
        return query.executeUpdate();
    }

}
