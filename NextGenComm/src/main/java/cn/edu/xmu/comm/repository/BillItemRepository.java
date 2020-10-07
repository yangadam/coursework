package cn.edu.xmu.comm.repository;

import cn.edu.xmu.comm.entity.BillItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillItemRepository extends JpaRepository<BillItem, Integer> {
}
