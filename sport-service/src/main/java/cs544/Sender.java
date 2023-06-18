package cs544;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Sender implements CommandLineRunner {
    @Autowired
    private RabbitTemplate template;
    
    public void sendMessage(String queue, String message) {
        template.convertAndSend(queue, message);
        System.out.println("Sent: " + message + " to: " + queue);
    }
    
    @Override
    public void run(String... args) throws Exception {
        
    }
}