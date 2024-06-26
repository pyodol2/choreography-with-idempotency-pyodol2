package choreographywithidempotency.infra;

import choreographywithidempotency.config.kafka.KafkaProcessor;
import choreographywithidempotency.domain.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    OrderRepository orderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='StockDecreased'"
    )
    public void wheneverStockDecreased_Approve(
        @Payload StockDecreased stockDecreased
    ) {
        StockDecreased event = stockDecreased;
        System.out.println(
            "\n\n##### listener Approve : " + stockDecreased + "\n\n"
        );

        // Sample Logic //
        Order.approve(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DeliveryFailed'"
    )
    public void wheneverDeliveryFailed_Reject(
        @Payload DeliveryFailed deliveryFailed
    ) {
        DeliveryFailed event = deliveryFailed;
        System.out.println(
            "\n\n##### listener Reject : " + deliveryFailed + "\n\n"
        );

        // Sample Logic //
        Order.reject(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='StockDecreaseFailed'"
    )
    public void wheneverStockDecreaseFailed_Reject(
        @Payload StockDecreaseFailed stockDecreaseFailed
    ) {
        StockDecreaseFailed event = stockDecreaseFailed;
        System.out.println(
            "\n\n##### listener Reject : " + stockDecreaseFailed + "\n\n"
        );

        // Sample Logic //
        Order.reject(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
