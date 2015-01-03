package cn.edu.xmu.comm.service;

import cn.edu.xmu.comm.commons.exception.DeviceException;
import cn.edu.xmu.comm.commons.exception.MailException;
import cn.edu.xmu.comm.commons.utils.MailUtils;
import cn.edu.xmu.comm.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public interface FinanceService {


    /**
     * 依据编号查找设备
     *
     * @param id 编号
     * @return 设备
     */
    public Device getDeviceById(Integer id);

    /**
     * 添加梯度
     *
     * @param community 小区
     * @param unitPrice 单价
     * @return 梯度对象
     */
    @Transactional(readOnly = false)
    public Gradient addGradient(Community community, BigDecimal unitPrice);

    /**
     * 添加梯度
     *
     * @param community 小区
     * @param readings  读数
     * @param prices    价格
     * @return 梯度对象
     */
    @Transactional(readOnly = false)
    public Gradient addGradient(Community community, Double[] readings, BigDecimal[] prices);

    /**
     * 将梯度应用到设备
     *
     * @param gradient 梯度
     * @param device   设备
     */
    @Transactional(readOnly = false)
    public void applyGradient(Gradient gradient, Device device);

    /**
     * 将梯度应用到所有类型相同的私有表
     *
     * @param gradient  梯度
     * @param community 小区
     */
    @Transactional(readOnly = false)
    public void applyPrivateGradient(Gradient gradient, Community community);

    /**
     * 将梯度应用到所有类型相同的公摊表
     *
     * @param gradient  梯度
     * @param community 小区
     */
    @Transactional(readOnly = false)
    public void applyShareGradient(Gradient gradient, Community community);

    /**
     * 生成所有业主账单
     *
     * @param community 小区
     */
    @Transactional(readOnly = false)
    public void generateBill(Community community) throws DeviceException;

    /**
     * 添加设备的读数
     *
     * @param device 指定设备
     * @param value 设备读数
     */
    @Transactional(readOnly = false)
    public void addDeviceValue(Device device, BigDecimal value);

    /**
     * 添加设备的读数
     *
     * @param deviceId 指定设备编号
     * @param date 日期
     * @param value 设备读数
     */
    @Transactional(readOnly = false)
    public void addDeviceValue(Integer deviceId, Date date, BigDecimal value);

    /**
     * 添加设备的读数
     *
     * @param device 指定设备
     * @param date 日期
     * @param value 设备读数
     */
    @Transactional(readOnly = false)
    public void addDeviceValue(Device device, Date date, BigDecimal value);

    @Transactional(readOnly = false)
    public void delDeviceValue(Integer id);

    @Transactional(readOnly = false)
    public void updateDeviceValue(Integer id, Date date, BigDecimal value);

    /**
     * 返回设备的所有读数
     *
     * @param device 设备
     * @return 设备的所有读数
     */
    public SortedMap<Date, BigDecimal> getDeviceValue(Device device);

    /**
     * 依据设备编号返回设备的所有读数
     *
     * @param deviceId 设备编号
     * @return 设备的所有读数
     */
    public SortedMap<Date, BigDecimal> getDeviceValue(Integer deviceId);

    /**
     * 获取业主所有的未缴费账单
     * @param owner 业主
     * @return 未缴费账单列表
     */
    public List<BillItem> getUnpaidBillItems(Owner owner);

    /**
     * 获取业主所有的未缴费账单
     * @param ownerId 业主编号
     * @return 未缴费账单列表
     */
    public List<BillItem> getUnpaidBillItems(Integer ownerId);

    /**
     * 获取业主所有的未缴费账单
     * @param phoneNumber 电话号码
     * @return 未缴费账单列表
     */
    public List<BillItem> getUnpaidBillItemsByPhoneNumber(String phoneNumber);

    /**
     * 获取业主所有的未缴费账单
     * @param room 房间
     * @return 未缴费账单列表
     */
    public List<BillItem> getUnpaidBillItemsByRoom(Room room);

    /**
     * 获取业主所有的未缴费账单
     * @param roomId 房间编号
     * @return 未缴费账单列表
     */
    public List<BillItem> getUnpaidBillItemsByRoom(Integer roomId);

    /**
     * 支付账单
     *
     * @param paidBy 付款人
     * @param receiveBy 收款人
     * @param billItems 账单(账单项列表)
     * @return payment 支付记录
     */
    @Transactional(readOnly = false)
    public Payment payBillItems(Owner paidBy, User receiveBy, List<BillItem> billItems);

    /**
     * 获得超期欠缴费清单
     *
     * @param ownerid 业主编号
     * @return 超期欠缴清单
     */
    public List<BillItem> getOverDueBillItems(Integer ownerid);

    /**
     * 发送欠缴费邮件
     *
     * @param owner     业主
     * @param mailUtils 邮件工具
     * @throws cn.edu.xmu.comm.commons.exception.MailException
     */
    public void sendOverDueMail(Owner owner, MailUtils mailUtils) throws MailException;

    @Transactional(readOnly = false)
    public void addBillItem();

    /**
     * 获取已录入的设备
     * @param community 社区
     * @return 设备列表
     */
    public List<Device> getCanCalculateDevice(Community community);

    Device getDeviceByNo(Community community, String deviceNo);

}