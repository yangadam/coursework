package cn.edu.xmu.comm.repository;

import cn.edu.xmu.comm.entity.Car;
import cn.edu.xmu.comm.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    /**
     * 获得所有车辆
     *
     * @param community 小区
     * @return 车辆列表
     */
    List<Car> findByCommunity(Community community);

    /**
     * 通过车牌号获取车辆
     *
     * @param license 车牌号
     * @return 车辆
     */
    Car findByLicense(String license);
}
