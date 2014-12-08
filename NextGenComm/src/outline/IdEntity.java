import javax.persistence.*;

/**
 * Created by Roger on 2014/12/8 0008.
 */
@MappedSuperclass
public class IdEntity {

    String id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @PrePersist
    public void prePersist() {
        this.id = null;
    }

}
