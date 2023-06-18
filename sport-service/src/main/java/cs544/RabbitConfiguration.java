package cs544;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitConfiguration {

    private static final String QUEUE_NAME = "stream_transfer_result";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME);
    }
}
