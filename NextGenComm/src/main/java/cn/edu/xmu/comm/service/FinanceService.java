package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.dao.CommunityDAO;
import cn.edu.xmu.comm.dao.DeviceDAO;
import cn.edu.xmu.comm.dao.GradientDAO;
import cn.edu.xmu.comm.dao.OwnerDAO;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Gradient;
import cn.edu.xmu.comm.entity.Owner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
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
    private CommunityDAO communityDAO;

    @Resource
    private DeviceDAO deviceDAO;

    @Resource
    private OwnerDAO ownerDAO;

    @Resource
    private GradientDAO gradientDAO;

    /**
     * 添加梯度
     *
     * @param community 小区
     * @param unitPrice 单价
     * @return 梯度对象
     */
    @Transactional(readOnly = false)
    public Gradient addGradient(Community community, BigDecimal unitPrice) {
        Gradient gradient = new Gradient(unitPrice);
        community.getGradients().add(gradient);
        gradientDAO.persist(gradient);
        communityDAO.merge(community);
        return gradient;
    }

    /**
     * 添加梯度
     *
     * @param community 小区
     * @param readings  读数
     * @param prices    价格
     * @return 梯度对象
     */
    @Transactional(readOnly = false)
    public Gradient addGradient(Community community, Double[] readings, BigDecimal[] prices) {
        if (readings.length + 1 == prices.length) {
            return null;
        }
        Gradient gradient = new Gradient(readings, prices);
        community.getGradients().add(gradient);
        gradientDAO.persist(gradient);
        communityDAO.merge(community);
        return gradient;
    }


    /**
     * 生成所有业主账单
     *
     * @param community 小区
     */
    @Transactional(readOnly = false)
    public void generateBill(Community community) {
        List<Owner> allOwner = ownerDAO.getAll(community);
        for (Owner owner : allOwner) {
            owner.generateBill();
        }
    }

}