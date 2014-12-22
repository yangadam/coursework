package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.dao.DeviceDAO;
import cn.edu.xmu.comm.dao.OwnerDAO;
import cn.edu.xmu.comm.entity.Owner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 财务模块Service
 * Created by Roger on 2014/12/7 0007.
 *
 * @author Mengmeng Yang
 * @version 2014-12-23
 */
@Service
@Transactional(readOnly = true)
public class FinanceService {

    @Resource
    private DeviceDAO deviceDAO;

    @Resource
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