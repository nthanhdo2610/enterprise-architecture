package cs544.service;

import cs544.StreamSender;
import cs544.domain.Game;
import lombok.Getter;
import lombok.Setter;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import java.util.Timer;
import java.util.TimerTask;
@Service
@Transactional
@Setter
@Getter
public class StreamService {
    private Timer timer;

    private final StreamSender streamSender;
    private Game game;

    public StreamService(StreamSender streamSender) {
        this.streamSender = streamSender;
    }
    public void setGame(Game g){
        this.game = g;
    }
    public String startGame() {
        String msg = "GAME STREAM START";
        streamSender.sendMessage("stream_transfer", msg);
        startTimer();
        return msg;
    }
    private void startTimer() {
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                decreaseMinutes();
            }
        };
        // Schedule the timer task to run every 1 minute (60000 milliseconds)
        timer.scheduleAtFixedRate(timerTask, 0, 600);
    }
    public void decreaseMinutes() {
        if (game!=null){
            System.out.println("game.getDurationMinutes()"+game.getDurationMinutes());
            if (game.getDurationMinutes()==0){
                ResponseEntity<String> response = connectToStream(game, "setStopFromStream");
                if (response.getStatusCode().is2xxSuccessful()) {
                    if (timer != null) {
                        timer.cancel();
                    }
                }
            }else{
                String msg = "GAME RESULT ("+game.getGoalHome()+":"+game.getGoalVisit()+") MINUTES: "+game.getDurationMinutes();
                game.setDurationMinutes(game.getDurationMinutes()-1);
                streamSender.sendMessage("stream_transfer", msg);
            }
        }else{
            if (timer != null) {
                timer.cancel();
            }
        }
    }
    public String stopGame() {
        String msg = "GAME STREAM STOP";
        streamSender.sendMessage("stream_transfer", msg);
        return msg;
    }
    public String updateScore() {
        String msg = "GAME STREAM UpdateScore";
        streamSender.sendMessage("stream_transfer", msg);
        return msg;
    }
    public ResponseEntity<String> connectToStream(Game game, String method){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Game> requestEntity = new HttpEntity<>(game, headers);
        // Call the external API using POST method
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8081/sport/"+method,
                HttpMethod.POST,
                requestEntity,
                String.class);
        return response;
    }
}
