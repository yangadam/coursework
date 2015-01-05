package cn.edu.xmu.comm.service.impl;

import cn.edu.xmu.comm.commons.exception.DeviceException;
import cn.edu.xmu.comm.commons.exception.MailException;
import cn.edu.xmu.comm.commons.utils.MailUtils;
import cn.edu.xmu.comm.commons.utils.SessionUtils;
import cn.edu.xmu.comm.dao.*;
import cn.edu.xmu.comm.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 财务模块Service
 * Created by Roger on 2014/12/7 0007.
 *
 * @author Mengmeng Yang
 * @version 2014-12-23
 */
@Service
@Transactional(readOnly = true)
public class FinanceServiceImpl implements cn.edu.xmu.comm.service.FinanceService {

    //region DAO
    @Resource
    private BillItemDAO billItemDAO;
    @Resource
    private CommunityDAO communityDAO;
    @Resource
    private DeviceDAO deviceDAO;
    @Resource
    private GradientDAO gradientDAO;
    @Resource
    private OwnerDAO ownerDAO;
    @Resource
    private PaymentDAO paymentDAO;
    @Resource
    private RoomDAO roomDAO;
    //endregion

    //region BillItem Service

    /**
     * 生成所有业主账单
     */
    @Override
    @Transactional(readOnly = false)
    public void generateAllBill() throws DeviceException {
        Community community = SessionUtils.getCommunity();
        List<Owner> allOwner = ownerDAO.getAll(community);
        for (Owner owner : allOwner) {
            owner.generateBill();
            ownerDAO.flush();
        }
    }
    //endregion

    //region Device Service

    /**
     * 获取已录入读数的设备
     *
     * @return 设备列表
     */
    @Override
    public List<Device> getInputedDevice() {
        Community community = SessionUtils.getCommunity();
        return deviceDAO.getInputedDevice(community);
    }

    /**
     * 输入设备号模糊搜索设备
     *
     * @param term 关键字
     * @return 设备id，设备号，设备类型，设备当前读数的列表
     */
    @Override
    public List<String[]> searchDevice(String term) {
        Community community = SessionUtils.getCommunity();
        return deviceDAO.buzzSearch(term, community);
    }

    /**
     * 获得已录入读数的设备数
     *
     * @return 已录入读数的设备数
     */
    @Override
    public Long getInputedDeviceCount() {
        Community community = SessionUtils.getCommunity();
        return deviceDAO.getInputedCount(community);
    }


    /**
     * 获得设备总数
     *
     * @return 设备总数
     */
    @Override
    public Long getDeviceCount() {
        Community community = SessionUtils.getCommunity();
        return deviceDAO.getCount(community);
    }

    /**
     * 添加设备的读数
     *
     * @param deviceId 指定设备id
     * @param date     日期
     * @param value    设备读数
     */
    @Override
    @Transactional(readOnly = false)
    public void addDeviceValue(Integer deviceId, Date date, Double value) {
        Device device = deviceDAO.get(deviceId);
        try {
            device.addValue(date, value);
        } catch (DeviceException e) {
            e.printStackTrace();
        }
        deviceDAO.merge(device);
    }

    /**
     * 更新设备读数
     *
     * @param id    id
     * @param value 值
     */
    @Override
    @Transactional(readOnly = false)
    public void updateDeviceValue(Integer id, Double value) {
        Device device = deviceDAO.get(id);
        device.updateLastValue(value);
        deviceDAO.merge(device);
    }

//
//    /**
//     * 依据编号查找设备
//     *
//     * @param id 编号
//     * @return 设备
//     */
//    public Device getDeviceById(Integer id) {
//        return deviceDAO.get(id);
//    }
//
//    /**
//     * 添加设备的读数
//     *
//     * @param device 指定设备
//     * @param value  设备读数
//     */
//    @Transactional(readOnly = false)
//    public void addDeviceValue(Device device, Double value) {
//        try {
//            device.addValue(new Date(), value);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        deviceDAO.merge(device);
//    }
//
//    /**
//     * 添加设备的读数
//     *
//     * @param deviceId 指定设备编号
//     * @param date     日期
//     * @param value    设备读数
//     */
//    @Transactional(readOnly = false)
//    public void addDeviceValue(Integer deviceId, Date date, Double value) {
//        Device device = deviceDAO.get(deviceId);
//        addDeviceValue(device, date, value);
//        deviceDAO.merge(device);
//    }
//

//
//    /**
//     * 删除设备读数
//     *
//     * @param id id
//     */
//    @Transactional(readOnly = false)
//    public void delDeviceValue(Integer id) {
//        Device device = deviceDAO.get(id);
//        try {
//            device.delValue();
//        } catch (DeviceException e) {
//            e.printStackTrace();
//        }
//        deviceDAO.merge(device);
//    }
//

//
//    /**
//     * 返回设备的所有读数
//     *
//     * @param device 设备
//     * @return 设备的所有读数
//     */
//    public SortedMap<Date, Double> getDeviceValue(Device device) {
//        return device.getValues();
//    }
//
//    /**
//     * 依据设备编号返回设备的所有读数
//     *
//     * @param deviceId 设备编号
//     * @return 设备的所有读数
//     */
//    public SortedMap<Date, Double> getDeviceValue(Integer deviceId) {
//        return getDeviceValue(deviceDAO.get(deviceId));
//    }
//
//    /**
//     * 获得所有设备
//     *
//     * @return 设备列表
//     */
//    public List<Device> getAllDevices() {
//        Community community = SessionUtils.getCommunity();
//        return deviceDAO.getAll(community);
//    }
    //endregion

    //region Gradient Service

//    /**
//     * 获得说有梯度
//     *
//     * @return 梯度列表
//     */
//    public List<Gradient> getGradients() {
//        Community community = SessionUtils.getCommunity();
//        return gradientDAO.getAll(community);
//    }
//
//    /**
//     * 添加梯度
//     *
//     * @param unitPrice 单价
//     * @return 梯度对象
//     */
//    @Transactional(readOnly = false)
//    public Gradient addGradient(BigDecimal unitPrice, Device.DeviceType type) {
//        Community community = SessionUtils.getCommunity();
//        Gradient gradient = new Gradient(unitPrice, type);
//        community.getGradients().add(gradient);
//        gradientDAO.persist(gradient);
//        return gradient;
//    }
//
//    /**
//     * 添加梯度
//     *
//     * @param readings 读数
//     * @param prices   价格
//     * @return 梯度对象
//     */
//    @Transactional(readOnly = false)
//    public Gradient addGradient(Double[] readings, BigDecimal[] prices, Device.DeviceType type) {
//        Community community = SessionUtils.getCommunity();
//        Validate.isTrue(readings.length + 1 == prices.length, "梯度数值数目有错误。");
//        Gradient gradient = new Gradient(readings, prices, type);
//        community.getGradients().add(gradient);
//        gradientDAO.persist(gradient);
//        communityDAO.merge(community);
//        return gradient;
//    }
//
//    /**
//     * 将梯度应用到设备
//     *
//     * @param gradient 梯度
//     * @param device   设备
//     */
//    @Transactional(readOnly = false)
//    public void applyGradient(Gradient gradient, Device device) {
//        Validate.isTrue(gradient.getType().equals(device.getType()), "梯度与设备不匹配");
//        device.setGradient(gradient);
//        deviceDAO.merge(device);
//    }
//
//    /**
//     * 将梯度应用到所有类型相同的私有表
//     *
//     * @param gradientId 梯度id
//     */
//    @Transactional(readOnly = false)
//    public void applyPrivateGradient(Integer gradientId) {
//        Community community = SessionUtils.getCommunity();
//        Gradient gradient = gradientDAO.get(gradientId);
//        deviceDAO.applyPrivateGradient(gradient, community);
//    }
//
//    /**
//     * 将梯度应用到所有类型相同的公摊表
//     *
//     * @param gradientId 梯度id
//     */
//    @Transactional(readOnly = false)
//    public void applyShareGradient(Integer gradientId) {
//        Community community = SessionUtils.getCommunity();
//        Gradient gradient = gradientDAO.get(gradientId);
//        deviceDAO.applyShareGradient(gradient, community);
//    }
    //endregion

    //region Owner Service

    /**
     * 获取欠缴费用户
     *
     * @return 欠缴费用户列表
     */
    @Override
    public List<Owner> getArrearageOwner() {
        Community community = SessionUtils.getCommunity();
        return ownerDAO.ArrearageOwner(community);
    }
    //endregion

    //region Payment Service

    /**
     * 业主进行网上缴费
     */
    @Override
    @Transactional(readOnly = false)
    public void makePayment() {
        Owner owner = (Owner) SessionUtils.getUser();
        Payment payment = owner.makePayment(null);
        if (payment != null) {
            paymentDAO.persist(payment);
        }
    }
    //endregion

    //region Mail Service

    /**
     * 发送欠缴费邮件
     *
     * @param ownerId   业主id
     * @param mailUtils 邮件工具
     * @throws MailException
     */
    @Override
    public void sendOverDueMail(Integer ownerId, MailUtils mailUtils) throws MailException {
        Owner owner = ownerDAO.get(ownerId);
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
    @Override
    public String getOverDueMailSubject(Owner owner) {
        return owner.getCommunity().getName() + " 欠缴费通知";
    }

    /**
     * 邮件主题
     *
     * @param owner 业主
     * @return 邮件主体
     */
    @Override
    public String getOverDueMailContext(Owner owner) {
        String head = "亲爱的" + owner.getName() + "先生/女士:" +
                "<div>&nbsp; &nbsp; &nbsp; 您好！您在" + owner.getCommunity().getName() +
                "尚有以下条目未缴费，请尽快缴费，谢谢！</div>";
        Set<BillItem> unpaidBillitem = owner.getUnpaidBills();
        Set<BillItem> overDueBills = owner.getOverDueBillItems();
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


    //endregion

}