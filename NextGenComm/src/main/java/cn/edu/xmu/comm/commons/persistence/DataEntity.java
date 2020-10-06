package cn.edu.xmu.comm.commons.persistence;

import javax.persistence.*;
import java.util.Date;

/**
 * 分页对象
 *
 * @author Mengmeng Yang
 * @version 12/8/2014
 */
@MappedSuperclass
public class DataEntity {

    //region Instance Variables
    private Date createDate;
    private Date updateDate;
    //endregion

    //region Getters

    //region Lifecircle Methods
    @PrePersist
    public void prePersist() {
        this.updateDate = new Date();
        this.createDate = this.updateDate;
    }

    @PreUpdate
    public void preUpdate() {
        this.updateDate = new Date();
    }
    //endregion

    /**
     * 获得创建日期
     *
     * @return 创建日期
     */
    @Temporal(TemporalType.DATE)
    public Date getCreateDate() {
        return createDate;
    }

    //region Setters
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    //endregion

    /**
     * 获得最后更新日期
     *
     * @return 最后更新日期
     */
    @Temporal(TemporalType.DATE)
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    //endregion

}
