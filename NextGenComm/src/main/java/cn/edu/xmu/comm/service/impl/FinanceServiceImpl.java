package cn.edu.xmu.comm.service.impl;

import cn.edu.xmu.comm.dao.CommunityDAO;
import cn.edu.xmu.comm.dao.DeviceDAO;
import cn.edu.xmu.comm.dao.GradientDAO;
import cn.edu.xmu.comm.dao.OwnerDAO;
import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Device;
import cn.edu.xmu.comm.entity.Gradient;
import cn.edu.xmu.comm.entity.Owner;
import cn.edu.xmu.comm.service.FinanceService;
import org.apache.commons.lang3.Validate;
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
public class FinanceServiceImpl implements FinanceService {

    @Resource
    private CommunityDAO communityDAO;

    @Resource
    private DeviceDAO deviceDAO;

    @Resource
    private OwnerDAO ownerDAO;

    @Resource
    private GradientDAO gradientDAO;

    @Override
    public Device getDeviceById(Integer id) {
        return deviceDAO.get(id);
    }

    /**
     * 添加梯度
     *
     * @param community 小区
     * @param unitPrice 单价
     * @return 梯度对象
     */
    @Override
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
    @Override
    @Transactional(readOnly = false)
    public Gradient addGradient(Community community, Double[] readings, BigDecimal[] prices) {
        Validate.isTrue(readings.length + 1 == prices.length, "梯度数值数目有错误。");
        Gradient gradient = new Gradient(readings, prices);
        community.getGradients().add(gradient);
        gradientDAO.persist(gradient);
        communityDAO.merge(community);
        return gradient;
    }

    /**
     * 将梯度应用到设备
     *
     * @param gradient 梯度
     * @param device   设备
     */
    @Override
    @Transactional(readOnly = false)
    public void applyGradient(Gradient gradient, Device device) {
        Validate.isTrue(gradient.getType().equals(device.getType()), "梯度与设备不匹配");
        device.setGradient(gradient);
        deviceDAO.merge(device);
    }

    /**
     * 将梯度应用到所有类型相同的私有表
     *
     * @param gradient  梯度
     * @param community 小区
     */
    @Override
    @Transactional(readOnly = false)
    public void applyPrivateGradient(Gradient gradient, Community community) {
        deviceDAO.applyPrivateGradient(gradient, community);
    }

    /**
     * 将梯度应用到所有类型相同的公摊表
     *
     * @param gradient  梯度
     * @param community 小区
     */
    @Override
    @Transactional(readOnly = false)
    public void applyShareGradient(Gradient gradient, Community community) {
        deviceDAO.applyShareGradient(gradient, community);
    }

    /**
     * 生成所有业主账单
     *
     * @param community 小区
     */
    @Override
    @Transactional(readOnly = false)
    public void generateBill(Community community) {
        List<Owner> allOwner = ownerDAO.getAll(community);
        for (Owner owner : allOwner) {
            owner.generateBill();
        }
    }

}