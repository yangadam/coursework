package cn.edu.xmu.comm.repository;

import cn.edu.xmu.comm.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, Integer> {
    /**
     * 通过名字查找小区
     *
     * @param commName 名字
     * @return 小区
     */
    Community findByName(String name);

    /**
     * 获取所有小区的名字
     *
     * @return 小区名字的列表
     */
    @Query("select name from Community")
    List<String> getNames();
}
