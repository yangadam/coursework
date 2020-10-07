package cn.edu.xmu.comm.repository;

import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.ParkingBill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingBillRepository extends JpaRepository<ParkingBill, Integer> {

    /**
     * 查询社区中临时停车的数量
     *
     * @param community 指定社区
     * @return 该社区中未完成的停车单，即临时停车数量
     */
    Integer countByCommunityAndEndTimeIsNull(Community community);

    /**
     * 获得所有未完成停车单
     *
     * @param community 小区
     * @return 停车单列表
     */
    List<ParkingBill> findByCommunityAndEndTimeIsNull(Community community);

    /**
     * 获得所有已完成停车单
     *
     * @param community 小区
     * @return 停车单列表
     */
    List<ParkingBill> findByCommunityAndEndTimeIsNotNull(Community community);
}
