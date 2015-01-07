package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.persistence.DataEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 公维金
 * Created by Roger on 2014/12/8 0005.
 *
 * @author Mengmeng Yang
 * @version 2014-12-8
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class PublicFund extends DataEntity {

    //region Instance Variables
    /**
     * 公维金主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 阈值
     */
    private BigDecimal threshold;

    /**
     * 公维金账户
     */
    private String account;

    /**
     * 余额
     */
    private BigDecimal balance;
    //公维金收入支出记录，定义一个类

    /**
     * 每户需交费用
     */
    private BigDecimal chargePerRoom;
    //endregion

    PublicFund() {
    }

    public PublicFund(BigDecimal threshold, String account, BigDecimal balance, BigDecimal chargePerRoom) {
        this.threshold = threshold;
        this.account = account;
        this.balance = balance;
        this.chargePerRoom = chargePerRoom;
    }


    //region Public Methods

    /**
     * 是否需要交公维金
     *
     * @return 判断结果
     */
    public Boolean isNeeded() {
        return balance.compareTo(threshold) == -1;
    }
    //endregion

    //region Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getThreshold() {
        return threshold;
    }

    public void setThreshold(BigDecimal threshold) {
        this.threshold = threshold;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getChargePerRoom() {
        return chargePerRoom;
    }

    public void setChargePerRoom(BigDecimal chargePerRoom) {
        this.chargePerRoom = chargePerRoom;
    }
    //endregion
}
