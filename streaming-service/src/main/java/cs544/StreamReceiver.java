package cs544;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "stream_transfer_result")
public class StreamReceiver {

    @RabbitHandler
    public void receiveMessage(String message) {
        System.out.println("Received: " + message);
    }
}
