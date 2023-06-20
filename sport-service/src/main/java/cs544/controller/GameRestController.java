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
import io.swagger.v3.oas.annotations.tags.Tag;

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
    @Tag(name = "setScore", description = "It is setting the scores")
    @PostMapping("/setScore")
    public String setScore(@RequestBody Map<String, Integer> scores, @RequestParam String token) {
        if (tokenServer.verifyToken(token)) {
            if ("ROLE_ADMIN".equals(tokenServer.verifyTokenRole(token))) {
                if (gameService.getLiveGame() == null) {
                    return "WE DON'T LIVE HAVE GAME";
                } else {
                    int homeScore = scores.getOrDefault("home", 0);
                    int visitScore = scores.getOrDefault("visit", 0);
                    return gameService.setScore(gameService.getLiveGame(), homeScore, visitScore, token);
                }
            } else {
                return "YOU ARE NOT ADMIN";
            }
        } else {
            return "YOU NEED TO TRANSFER TOKEN";
        }
    }

    @Tag(name = "addGame", description = "It is adding new game to Mongodb")
    @PostMapping("/addGame")
    public Game addGame(@RequestBody Game game, @RequestParam String token) {
        if (tokenServer.verifyToken(token)) {
            if ("ROLE_ADMIN".equals(tokenServer.verifyTokenRole(token))) {
                return gameService.add(game);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    @Tag(name = "setStart", description = "It is starting game also sending to RabbitMQ")
    @PostMapping("/setStart")
    public String setStart(@RequestParam String token) {
        if (tokenServer.verifyToken(token)) {
            if ("ROLE_ADMIN".equals(tokenServer.verifyTokenRole(token))) {
                if (gameService.getLiveGame() != null) {
                    return "ALREADY STARTED";
                } else {
                    if (gameService.getAll().size() == 0) {
                        return "WE DON'T HAVE GAME";
                    } else {
                        return gameService.setStartToLive(gameService.getNoLiveGame(), token);
                    }
                }
            } else {return "YOU ARE NOT ADMIN";}
        } else {
            return "YOU NEED TO TRANSFER TOKEN";
        }
    }
    @Tag(name = "setStop", description = "It is stoping game also set the who is win")
    @PostMapping("/setStop")
    public String setStop(@RequestParam String token) {
        if (tokenServer.verifyToken(token)) {
            if ("ROLE_ADMIN".equals(tokenServer.verifyTokenRole(token))) {
            if (gameService.getLiveGame() == null) {
                return "THERE DOESN'T HAVE LIVE GAME";
            } else {
                return gameService.setStopToLive(gameService.getLiveGame(), token);
            }
            } else {return "YOU ARE NOT ADMIN";}
        } else {
            return "YOU NEED TO TRANSFER TOKEN";
        }
    }
    @Tag(name = "getAllGame", description = "coming the all games")
    @GetMapping("/getAllGame")
    public List<Game> getAll(@RequestParam String token) {
        if (tokenServer.verifyToken(token)) {
            return gameService.getAll();
        } else {
            return null;
        }
    }
    @Tag(name = "getLiveGame", description = "getting just Live Game")
    @GetMapping("/getLiveGame")
    public Game getLiveGame(@RequestParam String token) {
        if (tokenServer.verifyToken(token)) {
            return gameService.getLiveGame();
        } else {
            return null;
        }
    }
    @Tag(name = "get", description = "get just one Game by ID")
    @GetMapping("/{id}")
    public Game get(@PathVariable String id, @RequestParam String token) {
        if (tokenServer.verifyToken(token)) {
            return gameService.get(id);
        } else {
            return null;
        }
    }
    @Tag(name = "delete", description = "delete just one Game by ID")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id, @RequestParam String token) {
        if (tokenServer.verifyToken(token)) {
            gameService.delete(id);
        }
    }
    @Tag(name = "deleteAll", description = "deleteAll Game")
    @DeleteMapping("/deleteAll")
    public void deleteAll(@RequestParam String token) {
        if (tokenServer.verifyToken(token)) {
            if ("ROLE_ADMIN".equals(tokenServer.verifyTokenRole(token))) {
                gameService.deleteAll();
            }
        }
    }
    @Tag(name = "deleteAllOthers", description = "deleteAll without live Game")
    @DeleteMapping("/deleteAllOthers")
    public void deleteAllOthers(@RequestParam String token) {
        if (tokenServer.verifyToken(token)) {
            if ("ROLE_ADMIN".equals(tokenServer.verifyTokenRole(token))) {
                gameService.deleteAllOthers();
            }
        }
    }
}
