package cn.edu.xmu.comm.repository;

import cn.edu.xmu.comm.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
