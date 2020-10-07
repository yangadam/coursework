package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.persistence.DataEntity;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 支付实体
 * Created by Roger on 2014/12/8 0005.
 *
 * @author Mengmeng Yang
 * @version 2014-12-8
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class Payment extends DataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(targetEntity = Owner.class)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner paidBy;
    @ManyToOne(targetEntity = Staff.class)
    @JoinColumn(name = "user_id")
    private Staff receiveBy;
    @OneToMany(mappedBy = "payment", targetEntity = BillItem.class)
    private List<BillItem> billItemList = new ArrayList<BillItem>();
    private BigDecimal total;

    /**
     * 无参构造函数
     */
    Payment() {
    }

    /**
     * 构造函数
     *
     * @param paidBy    支付人
     * @param receiveBy 接收人
     */
    public Payment(Owner paidBy, Staff receiveBy) {
        this(paidBy, receiveBy, paidBy.getUnpaidBills());
    }

    /**
     * 构造函数
     *
     * @param paidBy       支付人
     * @param receiveBy    接收人
     * @param billItemList 账单项列表
     */
    public Payment(Owner paidBy, Staff receiveBy, List<BillItem> billItemList) {
        BigDecimal tempTotal = BigDecimal.ZERO;
        this.paidBy = paidBy;
        this.receiveBy = receiveBy;
        this.billItemList.addAll(billItemList);
        for (BillItem billItem : billItemList) {
            billItem.setStatus(BillItem.BillItemStatus.PAID);
            billItem.setPayment(this);
            tempTotal = tempTotal.add(billItem.getAmount()).add(billItem.getOverDueFee());
            billItem.setOwner(null);
        }
        this.total = tempTotal;
    }

    /**
     * 获得支付主键
     *
     * @return 支付主键
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获得付款人
     *
     * @return 付款人
     */
    public Owner getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(Owner paidBy) {
        this.paidBy = paidBy;
    }

    /**
     * 获得收款人
     *
     * @return 收款人
     */
    public Staff getReceiveBy() {
        return receiveBy;
    }

    public void setReceiveBy(Staff receiveBy) {
        this.receiveBy = receiveBy;
    }

    /**
     * 获得账单列表
     *
     * @return 支付账单列表
     */
    public List<BillItem> getBillItemList() {
        return billItemList;
    }

    public void setBillItemList(List<BillItem> billItemList) {
        this.billItemList = billItemList;
    }

    /**
     * 获得总费用
     *
     * @return 总费用
     */
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
