package cn.edu.xmu.comm.repository;

import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Gradient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradientRepsitory extends JpaRepository<Gradient, Integer> {
    /**
     * 获取所有梯度
     *
     * @param community 小区
     * @return 梯度列表
     */
    List<Gradient> findByCommunity(Community community);
}
