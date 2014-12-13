package cn.edu.xmu.comm.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Roger on 2014/12/8 0008.
 */
@MappedSuperclass
public class DataEntity {

    //region Instance Variables
    /**
     * 创建日期
     */
    @Temporal(TemporalType.DATE)
    private Date createDate;

    /**
     * 最后更新日期
     */
    @Temporal(TemporalType.DATE)
    private Date updateDate;
    //endregion

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

    //region Getters and Setters
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    //endregion

}
