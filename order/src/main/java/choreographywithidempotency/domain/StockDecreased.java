package choreographywithidempotency.domain;

import choreographywithidempotency.domain.*;
import choreographywithidempotency.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class StockDecreased extends AbstractEvent {

    private Long id;
    private String productName;
    private String productImage;
    private Integer stock;
}
