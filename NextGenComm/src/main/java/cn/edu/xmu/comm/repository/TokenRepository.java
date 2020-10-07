package cn.edu.xmu.comm.repository;

import cn.edu.xmu.comm.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, String> {
    /**
     * 通过用户id删除token
     *
     * @param uid 用户id
     * @return 受影响的记录数
     */
    int deleteByUid(Integer uid);
}
