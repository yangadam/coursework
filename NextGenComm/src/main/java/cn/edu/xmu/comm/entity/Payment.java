package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.persistence.DataEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * 支付实体
 * Created by Roger on 2014/12/8 0005.
 *
 * @author Mengmeng Yang
 * @version 2014-12-8
 */
@Entity
public class Payment extends DataEntity {

    //region Instance Variables
    /**
     * 支付主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 付款人
     */
    @ManyToOne(targetEntity = Owner.class)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner paidBy;

    /**
     * 收款人
     */
    @ManyToOne(targetEntity = Staff.class)
    @JoinColumn(name = "user_id")
    private Staff receiveBy;

    /**
     * 账单列表
     */
    @OneToMany(mappedBy = "payment", targetEntity = BillItem.class)
    private List<BillItem> billItemList;

    /**
     * 总费用
     */
    private BigDecimal total;
    //endregion

    Payment() {
    }

    /**
     * 构造函数
     *
     * @param paidBy    支付人
     * @param receiveBy 接收人
     */
    Payment(Owner paidBy, Staff receiveBy) {
        this(paidBy, receiveBy, paidBy.getUnpaidBills());
    }

    /**
     * 构造函数
     *
     * @param paidBy       支付人
     * @param receiveBy    接收人
     * @param billItemList 账单项列表
     */
    Payment(Owner paidBy, Staff receiveBy, List<BillItem> billItemList) {
        BigDecimal tempTotal = BigDecimal.ZERO;
        this.paidBy = paidBy;
        this.receiveBy = receiveBy;
        this.billItemList = billItemList;
        for (BillItem billItem : billItemList) {
            tempTotal = tempTotal.add(billItem.getAmount()).add(billItem.getOverDueFee());
        }
        this.total = tempTotal;
    }

    //region Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Owner getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(Owner paidBy) {
        this.paidBy = paidBy;
    }

    public Staff getRecieveBy() {
        return receiveBy;
    }

    public void setRecieveBy(Staff recieveBy) {
        this.receiveBy = recieveBy;
    }

    public List<BillItem> getBillItemList() {
        return billItemList;
    }

    public void setBillItemList(List<BillItem> billItemList) {
        this.billItemList = billItemList;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    //endregion

}
