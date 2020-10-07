package cn.edu.xmu.comm.service.impl;

import cn.edu.xmu.comm.commons.annotation.Required;
import cn.edu.xmu.comm.commons.exception.DeviceException;
import cn.edu.xmu.comm.commons.exception.MailException;
import cn.edu.xmu.comm.commons.utils.MailUtils;
import cn.edu.xmu.comm.commons.utils.SessionUtils;
import cn.edu.xmu.comm.entity.*;
import cn.edu.xmu.comm.repository.*;
import cn.edu.xmu.comm.service.FinanceService;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 财务模块Service实现
 * Created by Roger on 2014/12/7 0007.
 *
 * @author Mengmeng Yang
 * @version 2014-12-23
 */
@Service
@Transactional(readOnly = true)
public class FinanceServiceImpl implements FinanceService {

    @Resource
    private BillItemRepository billItemRepository;

    @Resource
    private CommunityRepository communityRepository;
    @Resource
    private DeviceRepository deviceRepository;
    @Resource
    private GradientRepsitory gradientRepsitory;
    @Resource
    private OwnerRepository ownerRepository;
    @Resource
    private PaymentRepository paymentRepository;

    /**
     * 生成所有业主账单
     *
     * @throws DeviceException 设备异常
     * @see cn.edu.xmu.comm.commons.exception.DeviceException
     */
    @Override
    @Required(name = "clerk")
    @Transactional(readOnly = false)
    public void generateAllBill() throws DeviceException {
        long start = System.currentTimeMillis();
        Community community = SessionUtils.getCommunity();
        System.out.print(new Date());
        List<Integer> ids = ownerRepository.getIds(community);
        for (Integer id : ids) {
            Owner owner = ownerRepository.getOne(id);
            owner.generateBill();
            ownerRepository.flush();
        }
        long end = System.currentTimeMillis();
        System.out.println("生成账单公用时" + ((end - start) / 1000) + "秒");
    }

    /**
     * 获取已录入读数的设备
     *
     * @return 设备列表
     */
    @Override
    @Required(name = "clerk")
    public List<Device> getInputedDevice() {
        Community community = SessionUtils.getCommunity();
        return deviceRepository.getInputedDevice(community);
    }

    /**
     * 输入设备号模糊搜索设备
     *
     * @param term 关键字
     * @return 设备id，设备号，设备类型，设备当前读数的列表
     */
    @Override
    @Required(name = "clerk")
    public List<String[]> searchDevice(String term) {
        Community community = SessionUtils.getCommunity();
        return deviceRepository.fuzzSearch(term, community);
    }

    /**
     * 获得已录入读数的设备数
     *
     * @return 已录入读数的设备数
     */
    @Override
    @Required(name = "clerk")
    public Long getInputedDeviceCount() {
        Community community = SessionUtils.getCommunity();
        return deviceRepository.getInputedCount(community);
    }

    /**
     * 获得设备总数
     *
     * @return 设备总数
     */
    @Override
    @Required(name = "clerk")
    public Long getDeviceCount() {
        Community community = SessionUtils.getCommunity();
        return deviceRepository.countByCommunity(community);
    }

    /**
     * 添加设备的读数
     *
     * @param deviceId 指定设备id
     * @param date     日期
     * @param value    设备读数
     */
    @Override
    @Required(name = "clerk")
    @Transactional(readOnly = false)
    public void addDeviceValue(Integer deviceId, Date date, Double value) {
        Device device = deviceRepository.getOne(deviceId);
        try {
            device.addValue(date, value);
        } catch (DeviceException e) {
            e.printStackTrace();
        }
        deviceRepository.save(device);
    }

    /**
     * 更新设备读数
     *
     * @param id    id
     * @param value 值
     */
    @Override
    @Required(name = "clerk")
    @Transactional(readOnly = false)
    public void updateDeviceValue(Integer id, Double value) {
        Device device = deviceRepository.getOne(id);
        device.updateLastValue(value);
        deviceRepository.save(device);
    }

    /**
     * 添加梯度
     *
     * @param unitPrice 单价
     * @param type      设备类型
     * @return 梯度对象
     */
    @Override
    @Required(name = "director")
    @Transactional(readOnly = false)
    public Gradient addGradient(BigDecimal unitPrice, Device.DeviceType type) {
        Community community = SessionUtils.getCommunity();
        community = communityRepository.getOne(community.getId());
        Gradient gradient = new Gradient(unitPrice, type);
        community.getGradients().add(gradient);
        gradientRepsitory.save(gradient);
        return gradient;
    }

    /**
     * 添加梯度
     *
     * @param readings 读数
     * @param prices   价格
     * @param type     设备类型
     * @return 梯度对象
     */
    @Override
    @Required(name = "director")
    @Transactional(readOnly = false)
    public Gradient addGradient(Double[] readings, BigDecimal[] prices, Device.DeviceType type) {
        Community community = SessionUtils.getCommunity();
        community = communityRepository.getOne(community.getId());
        Validate.isTrue(readings.length + 1 == prices.length, "梯度数值数目有错误。");
        Gradient gradient = new Gradient(readings, prices, type);
        community.getGradients().add(gradient);
        gradientRepsitory.save(gradient);
        communityRepository.save(community);
        return gradient;
    }

    /**
     * 将梯度应用到设备
     *
     * @param gradient 梯度
     * @param device   设备
     */
    @Override
    @Required(name = "director")
    @Transactional(readOnly = false)
    public void applyGradient(Gradient gradient, Device device) {
        Validate.isTrue(gradient.getType().equals(device.getType()), "梯度与设备不匹配");
        device.setGradient(gradient);
        deviceRepository.save(device);
    }

    /**
     * 将梯度应用到所有类型相同的私有表
     *
     * @param gradientId 梯度id
     */
    @Override
    @Required(name = "director")
    @Transactional(readOnly = false)
    public void applyPrivateGradient(Integer gradientId) {
        Community community = SessionUtils.getCommunity();
        Gradient gradient = gradientRepsitory.getOne(gradientId);
        deviceRepository.applyPrivateGradient(gradient, community, gradient.getType());
    }

    /**
     * 将梯度应用到所有类型相同的公摊表
     *
     * @param gradientId 梯度id
     */
    @Override
    @Required(name = "director")
    @Transactional(readOnly = false)
    public void applyShareGradient(Integer gradientId) {
        Community community = SessionUtils.getCommunity();
        Gradient gradient = gradientRepsitory.getOne(gradientId);
        deviceRepository.applyShareGradient(gradient, community, gradient.getType());
    }

    /**
     * 获得所有梯度
     *
     * @return 梯度列表
     */
    @Override
    @Required(name = "director")
    public List<Gradient> getGradients() {
        Community community = SessionUtils.getCommunity();
        return gradientRepsitory.findByCommunity(community);
    }

    /**
     * 发送欠缴费邮件
     *
     * @param ownerId   业主id
     * @param mailUtils 邮件工具
     * @throws MailException 发送邮件异常
     * @see cn.edu.xmu.comm.commons.exception.MailException
     */
    @Override
    @Required(name = "clerk")
    public void sendOverDueMail(Integer ownerId, MailUtils mailUtils) throws MailException {
        Owner owner = ownerRepository.getOne(ownerId);
        String email = owner.getEmail();
        if (email == null) {
            throw new MailException("没有邮件地址", owner);
        }
        String subject = getOverDueMailSubject(owner);
        String context = getOverDueMailContext(owner);
        mailUtils.sendMail(email, subject, context);
    }

    /**
     * 获取欠缴费用户
     *
     * @return 欠缴费用户列表
     */
    @Override
    @Required(name = "clerk")
    public List<Owner> getArrearageOwner() {
        Community community = SessionUtils.getCommunity();
        return ownerRepository.arrearageOwner(community);
    }

    /**
     * 业主进行网上缴费
     */
    @Override
    @Required(name = "owner")
    @Transactional(readOnly = false)
    public void makePayment() {
        Owner owner = ownerRepository.getOne(SessionUtils.getUser().getId());
        List<BillItem> billItems = owner.getUnpaidBills();
        Payment payment = owner.makePayment(null, billItems);
        if (payment != null) {
            paymentRepository.save(payment);
        }
        billItemRepository.saveAll(billItems);
        ownerRepository.save(owner);
    }

    /**
     * 出纳收费
     *
     * @param ownerId 业主id
     */
    @Override
    @Required(name = "cashier")
    @Transactional(readOnly = false)
    public void charge(Integer ownerId) {
        Owner owner = ownerRepository.getOne(ownerId);
        List<BillItem> billItems = owner.getUnpaidBills();
        Payment payment = owner.makePayment(null, billItems);
        if (payment != null) {
            paymentRepository.save(payment);
        }
        billItemRepository.saveAll(billItems);
        ownerRepository.save(owner);
    }

    /**
     * 邮件抬头
     *
     * @param owner 业主
     * @return 邮件抬头
     */
    private String getOverDueMailSubject(Owner owner) {
        return owner.getCommunity().getName() + " 欠缴费通知";
    }

    /**
     * 邮件主题
     *
     * @param owner 业主
     * @return 邮件主体
     */
    private String getOverDueMailContext(Owner owner) {
        StringBuilder head = new StringBuilder("亲爱的");
        head.append(owner.getName());
        head.append("先生/女士:");
        head.append("<div>&nbsp; &nbsp; &nbsp; 您好！您在");
        head.append(owner.getCommunity().getName());
        head.append("尚有以下条目未缴费，请尽快缴费，谢谢！</div>");
        List<BillItem> overDueBills = owner.getOverDueBillItems();
        StringBuilder body = new StringBuilder("<div><table border=\"1\">");
        body.append("<tr>");
        body.append("<td>条目名</td>");
        body.append("<td>用量</td>");
        body.append("<td>滞纳天数</td>");
        body.append("<td>原金额</td>");
        body.append("<td>滞纳金金额</td>");
        body.append("<td>总金额</td>");
        body.append("<td>备注</td>");
        body.append("</tr>");
        for (BillItem billItem : overDueBills) {
            StringBuilder item = new StringBuilder("<tr>");
            item.append("<td>").append(billItem.getName()).append("</td>");
            item.append("<td>").append(billItem.getUsage()).append("</td>");
            item.append("<td>").append(billItem.getOverDueDays()).append("</td>");
            item.append("<td>").append(billItem.getAmount()).append("</td>");
            item.append("<td>").append(billItem.getOverDueFee()).append("</td>");
            item.append("<td>").append(billItem.getAmount().multiply(billItem.getOverDueFee())).append("</td>");
            item.append("<td>").append(billItem.getDescription()).append("</td>");
            item.append("</tr>");
            body.append(item);
        }
        body.append("</table></div>");
        return head.append(body).toString();
    }
}