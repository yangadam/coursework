package cn.edu.xmu.comm.service.impl;

import cn.edu.xmu.comm.commons.exception.DeviceException;
import cn.edu.xmu.comm.commons.exception.MailException;
import cn.edu.xmu.comm.commons.utils.MailUtils;
import cn.edu.xmu.comm.dao.*;
import cn.edu.xmu.comm.entity.*;
import cn.edu.xmu.comm.service.FinanceService;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
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
public class FinanceServiceImpl implements FinanceService {

    @Resource
    private CommunityDAO communityDAO;

    @Resource
    private DeviceDAO deviceDAO;

    @Resource
    private OwnerDAO ownerDAO;

    @Resource
    private GradientDAO gradientDAO;

    @Resource
    private RoomDAO roomDAO;

    @Resource
    private PaymentDAO paymentDAO;

    @Resource
    private BillItemDAO billItemDAO;

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
    public Gradient addGradient(Community community, BigDecimal unitPrice, Device.DeviceType type) {
        Gradient gradient = new Gradient(unitPrice, type);
        community.getGradients().add(gradient);
        gradientDAO.persist(gradient);
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
    public Gradient addGradient(Community community, Double[] readings, BigDecimal[] prices, Device.DeviceType type) {
        Validate.isTrue(readings.length + 1 == prices.length, "梯度数值数目有错误。");
        Gradient gradient = new Gradient(readings, prices, type);
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
    public void generateBill(Community community) throws DeviceException {
        List<Owner> allOwner = ownerDAO.getAll(community);
        for (Owner owner : allOwner) {
            owner.generateBill();
            ownerDAO.flush();
            ownerDAO.clear();
        }
    }

    /**
     * 添加设备的读数
     *
     * @param device 指定设备
     * @param value  设备读数
     */
    @Transactional(readOnly = false)
    public void addDeviceValue(Device device, Double value) {
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
     * @param date     日期
     * @param value    设备读数
     */
    @Transactional(readOnly = false)
    public void addDeviceValue(Integer deviceId, Date date, Double value) {
        Device device = deviceDAO.get(deviceId);
        addDeviceValue(device, date, value);
        deviceDAO.merge(device);
    }

    /**
     * 添加设备的读数
     *
     * @param device 指定设备
     * @param date   日期
     * @param value  设备读数
     */
    @Transactional(readOnly = false)
    public void addDeviceValue(Device device, Date date, Double value) {
        try {
            device.addValue(date, value);
        } catch (DeviceException e) {
            e.printStackTrace();
        }
        deviceDAO.merge(device);
    }

    @Transactional(readOnly = false)
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
    public void updateDeviceValue(Integer id, Double value) {
        Device device = deviceDAO.get(id);
        device.updateLastValue(value);
        deviceDAO.merge(device);
    }

    /**
     * 返回设备的所有读数
     *
     * @param device 设备
     * @return 设备的所有读数
     */
    public SortedMap<Date, Double> getDeviceValue(Device device) {
        return device.getValues();
    }

    /**
     * 依据设备编号返回设备的所有读数
     *
     * @param deviceId 设备编号
     * @return 设备的所有读数
     */
    public SortedMap<Date, Double> getDeviceValue(Integer deviceId) {
        return getDeviceValue(deviceDAO.get(deviceId));
    }

    /**
     * 获取业主所有的未缴费账单
     *
     * @param owner 业主
     * @return 未缴费账单列表
     */
    public List<BillItem> getUnpaidBillItems(Owner owner) {
        return owner.getUnpaidBills();
    }

    /**
     * 获取业主所有的未缴费账单
     *
     * @param ownerId 业主编号
     * @return 未缴费账单列表
     */
    public List<BillItem> getUnpaidBillItems(Integer ownerId) {
        Owner owner = ownerDAO.get(ownerId);
        return getUnpaidBillItems(owner);
    }

    /**
     * 获取业主所有的未缴费账单
     *
     * @param phoneNumber 电话号码
     * @return 未缴费账单列表
     */
    public List<BillItem> getUnpaidBillItemsByPhoneNumber(String phoneNumber) {
        Owner owner = ownerDAO.getByPhoneNumber(phoneNumber);
        return getUnpaidBillItems(owner);
    }

    /**
     * 获取业主所有的未缴费账单
     *
     * @param room 房间
     * @return 未缴费账单列表
     */
    public List<BillItem> getUnpaidBillItemsByRoom(Room room) {
        Owner owner = room.getOwner();
        return getUnpaidBillItems(owner);
    }

    /**
     * 获取业主所有的未缴费账单
     *
     * @param roomId 房间编号
     * @return 未缴费账单列表
     */
    public List<BillItem> getUnpaidBillItemsByRoom(Integer roomId) {
        Room room = roomDAO.get(roomId);
        return getUnpaidBillItemsByRoom(room);
    }

    /**
     * 支付账单
     *
     * @param paidBy    付款人
     * @param receiveBy 收款人
     * @param billItems 账单(账单项列表)
     * @return payment 支付记录
     */
    @Transactional(readOnly = false)
    public Payment makePayment(Owner paidBy, Staff receiveBy, List<BillItem> billItems) {
        Payment payment = paidBy.makePayment(receiveBy, billItems);
        ownerDAO.merge(paidBy);
        billItemDAO.merge(billItems);
        paymentDAO.persist(payment);
        return payment;
    }

    /**
     * 获得超期欠缴费清单
     *
     * @param ownerId 业主编号
     * @return 超期欠缴清单
     */
    public List<BillItem> getOverDueBillItems(Integer ownerId) {
        Owner owner = ownerDAO.get(ownerId);
        return owner.getOverDueBillItems();
    }

    /**
     * 获取欠缴费用户
     *
     * @param community 社区
     * @return 欠缴费用户列表
     */
    public List<Owner> getOwnerWithOverDue(Community community) {
        List<Owner> owners = ownerDAO.getAll(community);
        List<Owner> results = new ArrayList<Owner>();
        for (Owner owner : owners) {
            if (owner.getOverDueBillItems().size() > 0) {
                results.add(owner);
            }
        }
        return results;
    }

    /**
     * 发送欠缴费邮件
     *
     * @param owner     业主
     * @param mailUtils 邮件工具
     * @throws MailException
     */
    public void sendOverDueMail(Owner owner, MailUtils mailUtils) throws MailException {
        String email = owner.getEmail();
        if (email == null) {
            throw new MailException("没有邮件地址", owner);
        }
        String subject = getOverDueMailSubject(owner);
        String context = getOverDueMailContext(owner);
        mailUtils.sendMail(email, subject, context);
    }

    /**
     * 邮件抬头
     *
     * @param owner 业主
     * @return 邮件抬头
     */
    public String getOverDueMailSubject(Owner owner) {
        return owner.getCommunity().getName() + " 欠缴费通知";
    }

    /**
     * 邮件主题
     *
     * @param owner 业主
     * @return 邮件主体
     */
    public String getOverDueMailContext(Owner owner) {
        String head = "亲爱的" + owner.getName() + "先生/女士:" +
                "<div>&nbsp; &nbsp; &nbsp; 您好！您在" + owner.getCommunity().getName() +
                "尚有以下条目未缴费，请尽快缴费，谢谢！</div>";
        List<BillItem> unpaidBillitem = owner.getUnpaidBills();
        List<BillItem> overDueBills = owner.getOverDueBillItems();
        String body = "<div><table border=\"0.5\">";
        body += "<tr>" +
                "<td>条目名</td>" +
                "<td>用量</td>" +
                "<td>滞纳天数</td>" +
                "<td>原金额</td>" +
                "<td>滞纳金金额</td>" +
                "<td>总金额</td>" +
                "<td>备注</td>" +
                "</tr>";
        for (BillItem billItem : overDueBills) {
            String item = "<tr>";
            item += "<td>" + billItem.getName() + "</td>";
            item += "<td>" + billItem.getUsage() + "</td>";
            item += "<td>" + billItem.getOverDueDays() + "</td>";
            item += "<td>" + billItem.getAmount().subtract(billItem.getOverDueFee()) + "</td>";
            item += "<td>" + billItem.getOverDueFee() + "</td>";
            item += "<td>" + billItem.getAmount() + "</td>";
            item += "<td>" + billItem.getDescription() + "</td>";
            item += "</tr>";
            body += item;
        }
        body += "</table></div>";
        return head + body;
    }

    /**
     * 获取已录入的设备
     *
     * @param community 社区
     * @return 设备列表
     */
    public List<Device> getCanCalculateDevice(Community community) {
        return deviceDAO.getCanCalculateDevice(community);
    }

    @Override
    public Device getDeviceByNo(Community community, String deviceNo) {
        return deviceDAO.getByNo(community, deviceNo);
    }

    @Override
    public List<String[]> searchDevice(String term, Community community) {
        return deviceDAO.buzzSearch(term, community);
    }

    @Override
    public Long getInputDeviceCount(Community community) {
        return deviceDAO.getInputCount(community);
    }

    @Override
    public Long getDeviceCount(Community community) {
        return deviceDAO.getCount(community);
    }

    @Override
    public List<Gradient> getGradients(Community community) {
        return gradientDAO.getAll(community);
    }

    @Override
    public void makePayment(Integer id) {
        Owner owner = ownerDAO.get(id);
        Payment payment = owner.makePayment(null);
        paymentDAO.persist(payment);
        ownerDAO.flush();
        ownerDAO.clear();
    }

}