package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.entity.BillItem;
import cn.edu.xmu.comm.entity.Owner;

import java.util.List;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/5/2015 0005
 */
public interface BillItemDAO extends BaseDAO<BillItem, Integer> {
    List<BillItem> getUnpaidBillItems(Owner owner);
}
