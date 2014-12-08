import javax.persistence.Entity;

/**
 * Created by Roger on 2014/12/7 0007.
 */
@Entity
public class ParkPlace extends ScopedEntity {

    String position;
    Double monthlyFee;
}
