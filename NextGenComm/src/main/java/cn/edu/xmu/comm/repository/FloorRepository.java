package cn.edu.xmu.comm.repository;

import cn.edu.xmu.comm.entity.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FloorRepository extends JpaRepository<Floor, Integer> {
    /**
     * 通过楼宇id获得楼层
     *
     * @param buildId 楼宇id
     * @return 楼层号列表
     */
    @Query("select id, no from Floor where building.id = :buildingId")
    List<String[]> getIdsAndNos(Integer buildingId);
}
