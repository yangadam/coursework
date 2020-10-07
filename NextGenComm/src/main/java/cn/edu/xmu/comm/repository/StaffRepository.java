package cn.edu.xmu.comm.repository;

import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    /**
     * 获得所有员工
     *
     * @param community 小区
     * @return 员工列表
     */
    List<Staff> findByCommunity(Community community);

    /**
     * 删除员工
     *
     * @param community 小区
     */
    void deleteByCommunity(Community community);
}
