package cs544.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cs544.domain.Game;
import cs544.service.GameService;
import cs544.token.TokenServer;

import java.util.List;
import java.util.Map;

@RestController
public class GameRestController {
    private final GameService gameService;
    private final TokenServer tokenServer;

    public GameRestController(GameService gameService, TokenServer tokenServer) {
        this.gameService = gameService;
        this.tokenServer = tokenServer;
    }

    @PostMapping("/setScore")
    public String setScore(@RequestBody Map<String, Integer> scores, @RequestParam String token) {
        if (tokenServer.verifyToken(token)) {
            if (gameService.getLiveGame() == null) {
                return "WE DON'T LIVE HAVE GAME";
            } else {
                System.out.println("=------" + scores);
                int homeScore = scores.getOrDefault("home", 0);
                int visitScore = scores.getOrDefault("visit", 0);
                return gameService.setScore(gameService.getLiveGame(), homeScore, visitScore, token);
            }
        } else {
            return "YOU NEED TO TRANSFER TOKEN";
        }
    }

    @PostMapping("/addGame")
    public Game addGame(@RequestBody Game game, @RequestParam String token) {
        // if (tokenServer.verifyToken(token)) {
            return gameService.add(game);
        // } else {
        //     return null;
        // }
    }

    @PostMapping("/setStart")
    public String setStart(@RequestParam String token) {
        // if (tokenServer.verifyToken(token)) {
            if (gameService.getLiveGame() != null) {
                return "ALREADY STARTED";
            } else {
                if (gameService.getAll().size() == 0) {
                    return "WE DON'T HAVE GAME";
                } else {
                    return gameService.setStartToLive(gameService.getNoLiveGame(), token);
                }
            }
        // } else {
        //     return "YOU NEED TO TRANSFER TOKEN";
        // }
    }

    @PostMapping("/setStop")
    public String setStop(@RequestParam String token) {
        if (tokenServer.verifyToken(token)) {
            if (gameService.getLiveGame() == null) {
                return "THERE DOESN'T HAVE LIVE GAME";
            } else {
                return gameService.setStopToLive(gameService.getLiveGame(), token);
            }
        } else {
            return "YOU NEED TO TRANSFER TOKEN";
        }
    }

    @PostMapping("/setStopFromStream")
    public String setStopFromStream(@RequestBody Game game, @RequestParam String token) {
        if (tokenServer.verifyToken(token)) {
            if (game != null) {
                return gameService.setGameStatus(game, "stop");
            } else {
                return "STOP FAILED FROM STREAM";
            }
        } else {
            return "YOU NEED TO TRANSFER TOKEN";
        }
    }

    @GetMapping("/getAllGame")
    public List<Game> getAll(@RequestParam String token) {
        // if (tokenServer.verifyToken(token)) {
            return gameService.getAll();
        // } else {
        //     return null;
        // }
    }

    @GetMapping("/getLiveGame")
    public Game getLiveGame(@RequestParam String token) {
        // if (tokenServer.verifyToken(token)) {
            return gameService.getLiveGame();
        // } else {
        //     return null;
        // }
    }

    @GetMapping("/{id}")
    public Game get(@PathVariable String id, @RequestParam String token) {
        if (tokenServer.verifyToken(token)) {
            return gameService.get(id);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id, @RequestParam String token) {
        if (tokenServer.verifyToken(token)) {
            gameService.delete(id);
        }
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll(@RequestParam String token) {
        if (tokenServer.verifyToken(token)) {
            gameService.deleteAll();
        }
    }

    @DeleteMapping("/deleteAllOthers")
    public void deleteAllOthers(@RequestParam String token) {
        if (tokenServer.verifyToken(token)) {
            gameService.deleteAllOthers();
        }
    }
}
