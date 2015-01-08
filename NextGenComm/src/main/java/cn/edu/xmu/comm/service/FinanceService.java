package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.commons.annotation.Required;
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
 * 财务模块Service接口
 *
 * @author Mengmeng Yang
 * @version 1/8/2015 0008
 */
public interface FinanceService {
    /**
     * 生成所有业主账单
     *
     * @throws DeviceException 设备异常
     * @see cn.edu.xmu.comm.commons.exception.DeviceException
     */
    @Required(name = "clerk")
    @Transactional(readOnly = false)
    void generateAllBill() throws DeviceException;

    /**
     * 获取已录入读数的设备
     *
     * @return 设备列表
     */
    @Required(name = "clerk")
    List<Device> getInputedDevice();

    /**
     * 输入设备号模糊搜索设备
     *
     * @param term 关键字
     * @return 设备id，设备号，设备类型，设备当前读数的列表
     */
    @Required(name = "clerk")
    List<String[]> searchDevice(String term);

    /**
     * 获得已录入读数的设备数
     *
     * @return 已录入读数的设备数
     */
    @Required(name = "clerk")
    Long getInputedDeviceCount();

    /**
     * 获得设备总数
     *
     * @return 设备总数
     */
    @Required(name = "clerk")
    Long getDeviceCount();

    /**
     * 添加设备的读数
     *
     * @param deviceId 指定设备id
     * @param date     日期
     * @param value    设备读数
     */
    @Required(name = "clerk")
    @Transactional(readOnly = false)
    void addDeviceValue(Integer deviceId, Date date, Double value);

    /**
     * 更新设备读数
     *
     * @param id    id
     * @param value 值
     */
    @Required(name = "clerk")
    @Transactional(readOnly = false)
    void updateDeviceValue(Integer id, Double value);

    /**
     * 添加梯度
     *
     * @param unitPrice 单价
     * @param type      设备类型
     * @return 梯度对象
     */
    @Required(name = "director")
    @Transactional(readOnly = false)
    Gradient addGradient(BigDecimal unitPrice, Device.DeviceType type);

    /**
     * 添加梯度
     *
     * @param readings 读数
     * @param prices   价格
     * @param type     设备类型
     * @return 梯度对象
     */
    @Required(name = "director")
    @Transactional(readOnly = false)
    Gradient addGradient(Double[] readings, BigDecimal[] prices, Device.DeviceType type);

    /**
     * 将梯度应用到设备
     *
     * @param gradient 梯度
     * @param device   设备
     */
    @Required(name = "director")
    @Transactional(readOnly = false)
    void applyGradient(Gradient gradient, Device device);

    /**
     * 将梯度应用到所有类型相同的私有表
     *
     * @param gradientId 梯度id
     */
    @Required(name = "director")
    @Transactional(readOnly = false)
    void applyPrivateGradient(Integer gradientId);

    /**
     * 将梯度应用到所有类型相同的公摊表
     *
     * @param gradientId 梯度id
     */
    @Required(name = "director")
    @Transactional(readOnly = false)
    void applyShareGradient(Integer gradientId);

    /**
     * 获得所有梯度
     *
     * @return 梯度列表
     */
    @Required(name = "director")
    List<Gradient> getGradients();

    /**
     * 获取欠缴费用户
     *
     * @return 欠缴费用户列表
     */
    @Required(name = "clerk")
    List<Owner> getArrearageOwner();

    /**
     * 业主进行网上缴费
     */
    @Required(name = "owner")
    @Transactional(readOnly = false)
    void makePayment();

    /**
     * 出纳收费
     *
     * @param ownerId 业主id
     */
    @Required(name = "cashier")
    @Transactional(readOnly = false)
    void charge(Integer ownerId);

    /**
     * 发送欠缴费邮件
     *
     * @param ownerId   业主id
     * @param mailUtils 邮件工具
     * @throws MailException 发送邮件异常
     * @see cn.edu.xmu.comm.commons.exception.MailException
     */
    @Required(name = "clerk")
    void sendOverDueMail(Integer ownerId, MailUtils mailUtils) throws MailException;
}
