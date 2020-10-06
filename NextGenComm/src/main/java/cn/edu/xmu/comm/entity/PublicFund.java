package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.persistence.DataEntity;
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

    private Integer id;
    private BigDecimal threshold;
    private String account;
    private BigDecimal balance;
    private BigDecimal chargePerRoom;

    /**
     * 无参构造函数
     */
    PublicFund() {
    }

    /**
     * 构造函数
     *
     * @param threshold     阈值
     * @param account       账户
     * @param balance       余额
     * @param chargePerRoom 每户收费
     */
    public PublicFund(BigDecimal threshold, String account, BigDecimal balance, BigDecimal chargePerRoom) {
        this.threshold = threshold;
        this.account = account;
        this.balance = balance;
        this.chargePerRoom = chargePerRoom;
    }

    /**
     * 是否需要交公维金
     *
     * @return 判断结果
     */
    public Boolean isNeeded() {
        return balance.compareTo(threshold) == -1;
    }

    /**
     * 获得公维金主键
     *
     * @return 公维金主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    /**
     * 获得阈值
     *
     * @return 阈值
     */
    public BigDecimal getThreshold() {
        return threshold;
    }

    /**
     * 获得公维金账户
     *
     * @return 公维金账户
     */
    public String getAccount() {
        return account;
    }

    /**
     * 获得余额
     *
     * @return 余额
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * 获得每户需交费用
     *
     * @return 每户需交费用
     */
    public BigDecimal getChargePerRoom() {
        return chargePerRoom;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setThreshold(BigDecimal threshold) {
        this.threshold = threshold;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setChargePerRoom(BigDecimal chargePerRoom) {
        this.chargePerRoom = chargePerRoom;
    }

}
