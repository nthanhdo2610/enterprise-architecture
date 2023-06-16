package cs544.service;

import cs544.StreamSender;
import cs544.dao.StreamDao;
import cs544.domain.Game;

import org.springframework.data.mongodb.core.ChangeStreamOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.messaging.ChangeStreamRequest;
import org.springframework.data.mongodb.core.messaging.DefaultMessageListenerContainer;
import org.springframework.data.mongodb.core.messaging.MessageListenerContainer;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.PostConstruct;

@Service
@Transactional
public class StreamService {
    private MongoTemplate mongoTemplate;
    private final StreamDao gameDao;
    private final StreamSender streamSender;

    public StreamService(StreamDao gameDao, MongoTemplate mongoTemplate, StreamSender streamSender) {
        this.gameDao = gameDao;
        this.mongoTemplate = mongoTemplate;
        this.streamSender = streamSender;
    }

    public String publishGame() {
        streamSender.sendMessage("hello", "GAME STREAM PUBLISH");
        return "GAME STREAM PUBLISH";
    }

    // @PostConstruct
    // public void startListening() {
    //     MessageListenerContainer container = new DefaultMessageListenerContainer(mongoTemplate);
    //     ChangeStreamOptions options = ChangeStreamOptions.builder()
    //             .filter(mongoTemplate.query(Game.class).matching(Query.query(Criteria.where("status").is("live"))))
    //             .returnFullDocumentOnUpdate()
    //             .build();

    //     container.register(ChangeStreamRequest.builder()
    //             .collection("yourCollection")
    //             .options(options)
    //             .targetType(Game.class)
    //             .publishTo(message -> {
    //                 // Process the changed document here
    //                 System.out.println("Received document: " + message.getBody());
    //             })
    //             .build());
    //     container.start();
    // }
}
