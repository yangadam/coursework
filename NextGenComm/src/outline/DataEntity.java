import javax.persistence.*;
import java.util.Date;

/**
 * Created by Roger on 2014/12/8 0008.
 */
@MappedSuperclass
public class DataEntity extends IdEntity {

    Date createDate;
    Date updateDate;

    @PrePersist
    public void prePersist() {
        this.updateDate = new Date();
        this.createDate = this.updateDate;
    }

    @PreUpdate
    public void preUpdate(){
        this.updateDate = new Date();
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
