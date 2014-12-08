import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Roger on 2014/12/5 0005.
 */
@Entity
public class BillItem extends DataEntity {
    String name;
    Double amount;
    Double usage;
    String description;
    Date createdDate;
    String status;
}
