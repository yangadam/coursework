package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.entity.Community;
import cn.edu.xmu.comm.entity.Device;
import cn.edu.xmu.comm.entity.Gradient;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 12/31/2014 0031
 */
public interface FinanceService {
    /**
     * 添加梯度
     *
     * @param community 小区
     * @param unitPrice 单价
     * @return 梯度对象
     */
    @Transactional(readOnly = false)
    Gradient addGradient(Community community, BigDecimal unitPrice);

    /**
     * 添加梯度
     *
     * @param community 小区
     * @param readings  读数
     * @param prices    价格
     * @return 梯度对象
     */
    @Transactional(readOnly = false)
    Gradient addGradient(Community community, Double[] readings, BigDecimal[] prices);

    /**
     * 将梯度应用到设备
     *
     * @param gradient 梯度
     * @param device   设备
     */
    @Transactional(readOnly = false)
    void applyGradient(Gradient gradient, Device device);

    /**
     * 将梯度应用到所有类型相同的私有表
     *
     * @param gradient  梯度
     * @param community 小区
     */
    @Transactional(readOnly = false)
    void applyPrivateGradient(Gradient gradient, Community community);

    /**
     * 将梯度应用到所有类型相同的公摊表
     *
     * @param gradient  梯度
     * @param community 小区
     */
    @Transactional(readOnly = false)
    void applyShareGradient(Gradient gradient, Community community);

    /**
     * 生成所有业主账单
     *
     * @param community 小区
     */
    @Transactional(readOnly = false)
    void generateBill(Community community);
}
