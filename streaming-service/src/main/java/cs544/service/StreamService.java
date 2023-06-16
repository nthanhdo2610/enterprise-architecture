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
        String msg = "GAME STREAM PUBLISH";
        streamSender.sendMessage("stream_transfer", msg);
        return msg;
    }

    public String UnPublishGame() {
        String msg = "GAME STREAM UNPUBLISH";
        streamSender.sendMessage("stream_transfer", msg);
        return msg;
    }
}
