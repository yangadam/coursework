package cn.edu.xmu.comm.repository;

import cn.edu.xmu.comm.entity.Building;
import cn.edu.xmu.comm.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BuildingRepository extends JpaRepository<Building, Integer> {
    /**
     * 获得小区的楼宇列表
     *
     * @param community 小区
     * @return 楼宇列表
     */
    List<Building> findByCommunity(Community community);

    /**
     * 通过楼宇号获取某小区楼宇
     *
     * @param community 小区
     * @param no        楼宇号
     * @return 楼宇
     */
    List<Building> findByCommunityAndNo(Community community, String no);

    /**
     * 获取楼宇id和楼宇号列表
     *
     * @param community 小区
     * @return 楼宇id和楼宇号列表
     */
    @Query("select id, no from Building where community = :community")
    List<String[]> getIdsAndNosByCommunity(Community community);
}
