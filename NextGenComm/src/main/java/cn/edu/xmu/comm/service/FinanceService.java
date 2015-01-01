package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.commons.exception.DeviceException;
import cn.edu.xmu.comm.dao.CommunityDAO;
import cn.edu.xmu.comm.dao.DeviceDAO;
import cn.edu.xmu.comm.dao.GradientDAO;
import cn.edu.xmu.comm.dao.OwnerDAO;
import cn.edu.xmu.comm.entity.*;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

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
     * 依据编号查找设备
     *
     * @param id 编号
     * @return 设备
     */
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
    @Transactional(readOnly = false)
    public void applyShareGradient(Gradient gradient, Community community) {
        deviceDAO.applyShareGradient(gradient, community);
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

    /**
     * 添加设备的读数
     *
     * @param device 指定设备
     * @param value 设备读数
     */
    @Transactional(readOnly = false)
    public void addDeviceValue(Device device, BigDecimal value) {
        try {
            device.addValue(new Date(), value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        deviceDAO.merge(device);
    }

    /**
     * 添加设备的读数
     *
     * @param deviceId 指定设备编号
     * @param date 日期
     * @param value 设备读数
     */
    @Transactional(readOnly = false)
    public void addDeviceValue(Integer deviceId, Date date, BigDecimal value) {
        Device device = deviceDAO.get(deviceId);
        addDeviceValue(device, date, value);
        deviceDAO.merge(device);
    }

    /**
     * 添加设备的读数
     *
     * @param device 指定设备
     * @param date 日期
     * @param value 设备读数
     */
    //@Transactional(readOnly = false)
    public void addDeviceValue(Device device, Date date, BigDecimal value) {
        try {
            device.addValue(date, value);
        } catch (DeviceException e) {
            e.printStackTrace();
        }
        deviceDAO.merge(device);
    }

    //@Transactional(readOnly = false)
    public void delDeviceValue(Integer id) {
        Device device = deviceDAO.get(id);
        try {
            device.delValue();
        } catch (DeviceException e) {
            e.printStackTrace();
        }
        deviceDAO.merge(device);
    }

    @Transactional(readOnly = false)
    public void updateDeviceValue(Integer id, Date date, BigDecimal value) {
        Device device = deviceDAO.get(id);
        try {
            device.updateValue(date, value);
        } catch (DeviceException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回设备的所有读数
     *
     * @param device 设备
     * @return 设备的所有读数
     */
    public SortedMap<Date, BigDecimal> getDeviceValue(Device device) {
        return device.getValues();
    }

    /**
     * 依据设备编号返回设备的所有读数
     *
     * @param deviceId 设备编号
     * @return 设备的所有读数
     */
    public SortedMap<Date, BigDecimal> getDeviceValue(Integer deviceId) {
        return getDeviceValue(deviceDAO.get(deviceId));
    }

}