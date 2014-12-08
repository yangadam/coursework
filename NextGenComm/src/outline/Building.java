import javax.persistence.Entity;
import java.util.List;

/**
 * Created by Roger on 2014/12/7 0007.
 */
@Entity
public class Building extends ScopedEntity {

    List<Floor> floorList;
    List<Device> deviceList;

}
