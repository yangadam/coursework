package cn.edu.xmu.comm.dao.impl;

import cn.edu.xmu.comm.commons.persistence.BaseDaoImpl;
import cn.edu.xmu.comm.dao.PaymentDAO;
import cn.edu.xmu.comm.entity.Payment;
import org.springframework.stereotype.Repository;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/5/2015 0005
 */
@Repository
public class PaymentDaoImpl extends BaseDaoImpl<Payment, Integer> implements PaymentDAO {
}
