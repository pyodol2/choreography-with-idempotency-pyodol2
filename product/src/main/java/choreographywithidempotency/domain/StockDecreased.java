package choreographywithidempotency.domain;

import choreographywithidempotency.domain.*;
import choreographywithidempotency.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class StockDecreased extends AbstractEvent {

    private Long id;
    private String productName;
    private String productImage;
    private Integer stock;

    public StockDecreased(Inventory aggregate) {
        super(aggregate);
    }

    public StockDecreased() {
        super();
    }
}
//>>> DDD / Domain Event
