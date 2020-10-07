package cn.edu.xmu.comm.repository;

import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    /**
     * 删除业主
     *
     * @param community 小区
     */
    void deleteByCommunity(Community community);

    /**
     * 获取所有业主
     *
     * @param community 小区
     * @return 业主列表
     */
    List<Owner> findByCommunity(Community community);

    /**
     * 搜索业主
     *
     * @param term      关键字
     * @param community 小区
     * @return 业主
     */
    @Query("select id, name, username from Owner " +
            "where community = :community and (name like '%:term%' or username like '%:term%')")
    List<String[]> fuzzSearch(String term, Community community);

    /**
     * 获得某小区的所有业主
     *
     * @param community 所属小区
     * @return 业主列表
     */
    @Query("select id from Owner where community = :community")
    List<Integer> getIds(Community community);

    /**
     * 获取欠费用户
     *
     * @param community 小区
     * @return 欠费用户列表
     */
    @Query("select distinct o from Owner o inner join BillItem bi where o.community = :community and bi.status = 'OVERDUE'")
    List<Owner> arrearageOwner(Community community);
}
