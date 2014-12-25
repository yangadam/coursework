package cn.edu.xmu.comm.entity;

import cn.edu.xmu.comm.commons.persistence.DataEntity;

import javax.persistence.*;
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
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User recieveBy;

    /**
     * 账单列表
     */
    @OneToMany(mappedBy = "payment", targetEntity = BillItem.class)
    private List<BillItem> billItemList;

    /**
     * 总费用
     */
    private Double total;
    //endregion

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

    public User getRecieveBy() {
        return recieveBy;
    }

    public void setRecieveBy(User recieveBy) {
        this.recieveBy = recieveBy;
    }

    public List<BillItem> getBillItemList() {
        return billItemList;
    }

    public void setBillItemList(List<BillItem> billItemList) {
        this.billItemList = billItemList;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    //endregion

}
