package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.commons.exception.DeviceException;
import cn.edu.xmu.comm.commons.exception.MailException;
import cn.edu.xmu.comm.commons.utils.MailUtils;
import cn.edu.xmu.comm.entity.Device;
import cn.edu.xmu.comm.entity.Gradient;
import cn.edu.xmu.comm.entity.Owner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * description
 *
 * @author Mengmeng Yang
 * @version 1/5/2015 0005
 */
public interface FinanceService {
    /**
     * 生成所有业主账单
     */
    @Transactional(readOnly = false)
    void generateAllBill() throws DeviceException;

    Long getDeviceCount();

    @Transactional(readOnly = false)
    void updateDeviceValue(Integer id, Double value);

    List<Device> getAllDevice();

    List<Gradient> getGradients();

    @Transactional(readOnly = false)
    Gradient addGradient(BigDecimal unitPrice, Device.DeviceType type);

    @Transactional(readOnly = false)
    Gradient addGradient(Double[] readings, BigDecimal[] prices, Device.DeviceType type);

    @Transactional(readOnly = false)
    void applyGradient(Gradient gradient, Device device);

    @Transactional(readOnly = false)
    void applyPrivateGradient(Integer gradientId);

    @Transactional(readOnly = false)
    void applyShareGradient(Integer gradientId);

    /**
     * 获取欠缴费用户
     *
     * @return 欠缴费用户列表
     */
    List<Owner> getArrearageOwner();

    /**
     * 获取已录入读数的设备
     *
     * @return 设备列表
     */
    public List<Device> getInputedDevice();

    List<String[]> searchDevice(String term);

    /**
     * 获得已录入读数的设备数
     *
     * @return 已录入读数的设备数
     */
    public Long getInputedDeviceCount();

    /**
     * 业主进行网上缴费
     */
    @Transactional(readOnly = false)
    void makePayment();

    @Transactional(readOnly = false)
    void makePayment(Integer ownerId);

    /**
     * 发送欠缴费邮件
     *
     * @param ownerId   业主id
     * @param mailUtils 邮件工具
     * @throws cn.edu.xmu.comm.commons.exception.MailException
     */

    void sendOverDueMail(Integer ownerId, MailUtils mailUtils) throws MailException;

    /**
     * 邮件抬头
     *
     * @param owner 业主
     * @return 邮件抬头
     */
    String getOverDueMailSubject(Owner owner);

    /**
     * 邮件主题
     *
     * @param owner 业主
     * @return 邮件主体
     */
    String getOverDueMailContext(Owner owner);


    void addDeviceValue(Integer deviceId, Date date, Double curValue);
}
