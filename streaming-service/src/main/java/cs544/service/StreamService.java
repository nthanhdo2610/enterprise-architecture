package cs544.service;

import cs544.StreamSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class StreamService {
    private final StreamSender streamSender;

    public StreamService(StreamSender streamSender) {
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
    public String updateScore() {
        String msg = "GAME STREAM UpdateScore";
        streamSender.sendMessage("stream_transfer", msg);
        return msg;
    }
    
}
