package cn.edu.xmu.comm.repository;

import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Device;
import cn.edu.xmu.comm.entity.Gradient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Integer> {
    /**
     * 获取已录入设备
     *
     * @param community 小区
     * @return 设备列表
     */
    @Query("from Device where community = :community and isCalculated = false")
    List<Device> getInputedDevice(Community community);

    /**
     * 获取已录入设备数量
     *
     * @param community 小区
     * @return 已录入设备数量
     */
    @Query("select count(1) from Device where community = :community")
    Long getInputedCount(Community community);

    /**
     * 获取未录入设备数量
     *
     * @param community 小区
     * @return 未录入设备数量
     */
    Long countByCommunity(Community community);

    /**
     * 模糊搜索返回设备id，编号，类型，当前值
     *
     * @param term      关键字
     * @param community 小区
     * @return 设备id，编号，类型，当前值列表
     */
    @Query("select id, no, type, currentValue from Device " +
            "where community = :community and no like '%:term%' and isCalculated = true")
    List<String[]> fuzzSearch(String term, Community community);

    /**
     * 将梯度应用到所有类型相同的私有表
     *
     * @param gradient  梯度
     * @param community 小区
     */
    @Modifying
    @Query("update Device set gradient = :gradient where " +
            "community = :community and shareType is null and type = :type")
    void applyPrivateGradient(Gradient gradient, Community community, Device.DeviceType type);

    /**
     * 将梯度应用到所有类型相同的公摊表
     *
     * @param gradient  梯度
     * @param community 小区
     */
    @Modifying
    @Query("update Device set gradient = :gradient where " +
            "community = :community and shareType is not null and type = :type")
    void applyShareGradient(Gradient gradient, Community community, Device.DeviceType type);
}
