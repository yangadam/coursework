package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.calc.CalculatorFactory;
import cn.edu.xmu.comm.commons.calc.IOverdueFineCalculator;
import cn.edu.xmu.comm.commons.persistence.DataEntity;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Roger on 2014/12/5 0005.
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class BillItem extends DataEntity {

    //region Instance variables
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 账单项名称
     */
    @Column(nullable = false)
    private String name;

    /**
     * 账单项描述
     */
    private String description;

    /**
     * 金额
     */
    @Column(nullable = false, scale = 2)
    private BigDecimal amount;
    /**
     * 用量
     */
    @Column(name = "musage")
    private Double usage;

    /**
     * 账单项状态，PAID：已付款账单项、UNPAID：未付款账单项、OVERDUE：超期未付款
     */
    private BillItemStatus status;

    /**
     * 用户
     */
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Owner.class)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    /**
     * 支付
     */
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Payment.class)
    @JoinColumn(name = "payment_id", nullable = true)
    private Payment payment;

    /**
     * 缴费期限 超过该间隔为超期
     */
    private Integer duration;
    /**
     * 滞纳金金额
     */
    private BigDecimal overDueFee;
    //endregion

    //region Constructors
    public BillItem() {
        this.status = BillItemStatus.UNPAID;
        this.duration = 30;
        this.setCreateDate(new Date());
        this.overDueFee = BigDecimal.ZERO;
    }

    public BillItem(String name, String description, BigDecimal amount, Double usage, Owner owner) {
        super.prePersist();
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.usage = usage;
        this.owner = owner;
        this.status = BillItemStatus.UNPAID;
        this.overDueFee = BigDecimal.ZERO;
        setStatus(BillItemStatus.UNPAID);
    }

    public BillItem(String name, String description, BigDecimal amount, Double usage, Owner owner, Integer duration) {
        this(name, description, amount, usage, owner);
        this.duration = duration;
    }
    //endregion

    //region Public Methods

    /**
     * 判断账单项是否超期
     *
     * @param date 日期
     * @return 是否超期
     */
    public boolean isOverDue(Date date) {
        if (status == BillItemStatus.PAID) {
            return false;
        } else if (status == BillItemStatus.OVERDUE) {
            return true;
        } else if (getIntervalDays(date, this.getCreateDate()) > duration) {
            status = BillItemStatus.OVERDUE;
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断账单项是否超期
     *
     * @return 是否超期
     */
    public boolean isOverDue() {
        return isOverDue(new Date());
    }

    /**
     * 判断两个Date对象之间的天数间隔
     *
     * @param startDay 开始日期
     * @param endDay   结束日期
     * @return 天数间隔
     */
    public Integer getIntervalDays(Date startDay, Date endDay) {
        if (startDay.after(endDay)) {
            Date cal = startDay;
            startDay = endDay;
            endDay = cal;
        }
        long sl = startDay.getTime();
        long el = endDay.getTime();
        long ei = el - sl;
        return (int) (ei / (1000 * 60 * 60 * 24));
    }

    /**
     * 计算超期天数
     *
     * @return 超期天数
     */
    public Integer getOverDueDays() {
        Date current = new Date();
        Integer overDueDays = getIntervalDays(this.getCreateDate(), current) - duration;
        if (overDueDays <= 0)
            return 0;
        else
            return overDueDays;
    }

    /**
     * 更新滞纳金费用
     *
     * @return 滞纳金金额
     */
    public BigDecimal updateOverDueFee() {
        // 若账单已经付款 或者 更新日期小于1天则不计算
        if (status == BillItemStatus.PAID /*|| getIntervalDays(getUpdateDate(), new Date()) < 1*/)
            return overDueFee;
        Community community = owner.getCommunity();
        String type = community.getOverDueFeeType();
        IOverdueFineCalculator calculator = CalculatorFactory.getCalculator(type);
        overDueFee = calculator.calculate(this);
        if (overDueFee.compareTo(BigDecimal.ZERO) == 1)
            status = BillItemStatus.OVERDUE;
        // 记录更新日期
        this.setUpdateDate(new Date());
        return overDueFee;
    }


    //endregion

    //region Setters and Getters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        updateOverDueFee();
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Double getUsage() {
        return usage;
    }

    public void setUsage(Double usage) {
        this.usage = usage;
    }

    public BillItemStatus getStatus() {
        return status;
    }

    public void setStatus(BillItemStatus status) {
        this.status = status;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }


    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public BigDecimal getOverDueFee() {
        updateOverDueFee();
        return overDueFee;
    }

    public void setOverDueFee(BigDecimal overDueFee) {
        this.overDueFee = overDueFee;
    }

    //endregion

    //region Constants

    /**
     * 账单项状态，PAID：已付款账单项、UNPAID：未付款账单项
     */
    public enum BillItemStatus {
        PAID("已付款"), UNPAID("期限内未付款"), OVERDUE("超期未付款");

        private String typeName;

        private BillItemStatus(String typeName) {
            this.typeName = typeName;
        }

        @Override
        public String toString() {
            return typeName;
        }
    }
    //endregion

}
