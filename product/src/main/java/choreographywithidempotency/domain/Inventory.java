package choreographywithidempotency.domain;

import choreographywithidempotency.ProductApplication;
import choreographywithidempotency.domain.StockDecreaseFailed;
import choreographywithidempotency.domain.StockDecreased;
import choreographywithidempotency.domain.StockIncreased;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Inventory_table")
@Data
//<<< DDD / Aggregate Root
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productName;

    private String productImage;

    private Integer stock;

    private String productId;

    @PostPersist
    public void onPostPersist() {
        StockDecreaseFailed stockDecreaseFailed = new StockDecreaseFailed(this);
        stockDecreaseFailed.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
        StockDecreased stockDecreased = new StockDecreased(this);
        stockDecreased.publishAfterCommit();
    }

    @PreUpdate
    public void onPreUpdate() {
        StockIncreased stockIncreased = new StockIncreased(this);
        stockIncreased.publishAfterCommit();
    }

    public static InventoryRepository repository() {
        InventoryRepository inventoryRepository = ProductApplication.applicationContext.getBean(
            InventoryRepository.class
        );
        return inventoryRepository;
    }
}
//>>> DDD / Aggregate Root
