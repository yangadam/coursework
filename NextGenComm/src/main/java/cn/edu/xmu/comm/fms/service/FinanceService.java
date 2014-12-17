package cn.edu.xmu.comm.fms.service;


import cn.edu.xmu.comm.pms.dao.OwnerDAO;
import cn.edu.xmu.comm.pms.entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by Roger on 2014/12/5 0005.
 */
@Service
@Transactional
public class FinanceService {

    @Autowired
    private OwnerDAO ownerDAO;

    /**
     * 生成所有业主账单
     */
    public void generateBill() {
        List<Owner> allOwner = ownerDAO.getAll();
        for (Owner owner : allOwner) {
            owner.generateBill();
            ownerDAO.flush();
        }
    }

}