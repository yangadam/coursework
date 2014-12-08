import javax.persistence.Entity;
import java.util.List;

/**
 * Created by Roger on 2014/12/7 0007.
 */
@Entity
public class Floor extends ScopedEntity {
    Building building;
    List<Room> roomList;
    List<Device> deviceList;
}
