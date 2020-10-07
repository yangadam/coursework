package cn.edu.xmu.comm.repository;

import cn.edu.xmu.comm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * 通过用户名获得用户
     *
     * @param username 用户名
     * @return 用户
     */
    User findByUsername(String username);
}
