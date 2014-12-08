import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by Roger on 2014/12/8 0008.
 */
@MappedSuperclass
public class ScopedEntity extends IdEntity{

    Community community;

    @Column
    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }
}
