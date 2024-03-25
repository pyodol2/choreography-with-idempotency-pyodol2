package choreographywithidempotency.domain;

import choreographywithidempotency.domain.*;
import choreographywithidempotency.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class OrderApproved extends AbstractEvent {

    private Long id;
    private String customerId;
    private String customerName;
    private String productId;
    private String productName;
    private Integer qty;
    private String address;

    public OrderApproved(Order aggregate) {
        super(aggregate);
    }

    public OrderApproved() {
        super();
    }
}
//>>> DDD / Domain Event
