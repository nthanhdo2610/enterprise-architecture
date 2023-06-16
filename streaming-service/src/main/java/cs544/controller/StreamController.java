package cs544.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import cs544.domain.Game;
import cs544.service.StreamService;

@RestController
public class StreamController {
    private final StreamService streamService;

    public StreamController(StreamService streamService) {
        this.streamService = streamService;
    }

    @PostMapping("/startGame")
    public String startGame(@RequestBody Game game) {
        System.out.println("STARTED GAME*****" + game);
        streamService.setGame(game);
        return streamService.startGame();
    }

    @PostMapping("/stopGame")
    public String stopGame(@RequestBody Game game) {
        System.out.println("------------STOPPED GAME*****" + game);
        streamService.setGame(game);
        return streamService.stopGame();
    }

    @PostMapping("/updateScore")
    public String updateScore(@RequestBody Game game) {
        if (streamService.getGame() != null) {
            int dur = streamService.getGame().getDurationMinutes();
            streamService.setGame(game);
            streamService.getGame().setDurationMinutes(dur);
            return streamService.updateScore();
        } else {
            return "Failed update Score stream";
        }

    }
}
