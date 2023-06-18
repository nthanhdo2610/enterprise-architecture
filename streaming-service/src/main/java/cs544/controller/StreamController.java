package cs544.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cs544.domain.Game;
import cs544.service.StreamService;
import cs544.token.TokenServer;

@RestController
public class StreamController {
    private final StreamService streamService;
    private final TokenServer tokenServer;

    public StreamController(StreamService streamService, TokenServer tokenServer) {
        this.streamService = streamService;
        this.tokenServer = tokenServer;
    }

    @PostMapping("/startGame")
    public String startGame(@RequestBody Game game, @RequestParam String token) {
        System.out.println("game"+game+"  token "+token);
        if (tokenServer.verifyToken(token)) {
            System.out.println("STARTED GAME*****" + game);
            streamService.setGame(game, token);
            return streamService.startGame();

        } else {
            return "YOU NEED TO TRANSFER TOKEN";
        }
    }

    @PostMapping("/stopGame")
    public String stopGame(@RequestBody Game game, @RequestParam String token) {
        if (tokenServer.verifyToken(token)) {
            System.out.println("------------STOPPED GAME*****" + game);
            streamService.setGame(game, token);
            return streamService.stopGame();
        } else {
            return "YOU NEED TO TRANSFER TOKEN";
        }
    }

    @PostMapping("/updateScore")
    public String updateScore(@RequestBody Game game, @RequestParam String token) {
        if (tokenServer.verifyToken(token)) {
            if (streamService.getGame() != null) {
                int dur = streamService.getGame().getDurationMinutes();
                streamService.setGame(game, token);
                streamService.getGame().setDurationMinutes(dur);
                return streamService.updateScore();
            } else {
                return "Failed update Score stream";
            }
        } else {
            return "YOU NEED TO TRANSFER TOKEN";
        }
    }
}
