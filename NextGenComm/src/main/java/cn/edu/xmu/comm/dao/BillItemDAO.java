package cn.edu.xmu.comm.dao;

import cn.edu.xmu.comm.commons.persistence.BaseDAO;
import cn.edu.xmu.comm.entity.BillItem;
import cn.edu.xmu.comm.entity.Owner;

import java.util.List;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/8/2015 0008
 */
public interface BillItemDAO extends BaseDAO<BillItem, Integer> {
    /**
     * 获取业主未支付的账单项
     *
     * @param owner 业主
     * @return 账单项列表
     */
    List<BillItem> getUnpaidBillItems(Owner owner);
}
