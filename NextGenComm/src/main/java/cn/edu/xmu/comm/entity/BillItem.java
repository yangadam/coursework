package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.persistence.DataEntity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Roger on 2014/12/5 0005.
 */
@Entity
public class BillItem extends DataEntity {

    //region Instance variables
    /**
     * 账单项状态，未支付
     */
    public static final int UNPAID = 0;
    /**
     * 账单项状态，已支付
     */
    public static final int PAID = 1;
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
    @Column(nullable = false)
    private BigDecimal amount;
    /**
     * 用量
     */
    @Column(name = "musage", nullable = true)
    private BigDecimal usage;
    /**
     * 状态 0：未支付、1：已支付
     * <li>{@link #UNPAID}</li>
     * <li>{@link #PAID}</li>
     */
    private int status;
    //endregion
    /**
     * 用户
     */
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Owner.class)
    @JoinColumn(name = "owner_id")
    private Owner owner;
    //endregion
    /**
     * 支付
     */
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Payment.class)
    @JoinColumn(name = "payment_id", nullable = true)
    private Payment payment;

    //region Constructors
    public BillItem() {
        this.status = UNPAID;
    }

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
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getUsage() {
        return usage;
    }

    public void setUsage(BigDecimal usage) {
        this.usage = usage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
    //endregion

    //region Constants

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    //endregionss

}
