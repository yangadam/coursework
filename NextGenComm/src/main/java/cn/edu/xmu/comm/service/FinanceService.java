package cn.edu.xmu.comm.service;


import cn.edu.xmu.comm.dao.OwnerDAO;
import cn.edu.xmu.comm.entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by Roger on 2014/12/5 0005.
 */
@Service
@Transactional(readOnly = true)
public class FinanceService {

    @Autowired
    private OwnerDAO ownerDAO;

    /**
     * 生成所有业主账单
     */
    @Transactional(readOnly = false)
    public void generateBill() {
        List<Owner> allOwner = ownerDAO.getAll();
        for (Owner owner : allOwner) {
            owner.generateBill();
        }
    }

}