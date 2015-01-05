package cn.edu.xmu.comm.dao.impl;

import cn.edu.xmu.comm.commons.persistence.BaseDaoImpl;
import cn.edu.xmu.comm.commons.persistence.Parameter;
import cn.edu.xmu.comm.dao.BillItemDAO;
import cn.edu.xmu.comm.entity.BillItem;
import cn.edu.xmu.comm.entity.Owner;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账单项DAO
 * Created by Roger on 2014/12/9 0009.
 *
 * @author Mengmeng Yang
 * @version 2014-12-9
 */
@Repository
public class BillItemDaoImpl extends BaseDaoImpl<BillItem, Integer> implements BillItemDAO {
    @Override
    public List<BillItem> getUnpaidBillItems(Owner owner) {
        String ql = "from BillItem where owner = :p1 and billItemStatus != :p2";
        return searchByQL(ql, new Parameter(owner, BillItem.BillItemStatus.PAID));
    }
}
