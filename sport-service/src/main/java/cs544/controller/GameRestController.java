package cs544.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import cs544.domain.Game;
import cs544.service.GameService;
import cs544.token.TokenServer;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GameRestController {
    private final GameService gameService;
    private final TokenServer tokenServer;
    private final ObjectMapper objectMapper;

    public GameRestController(GameService gameService, TokenServer tokenServer, ObjectMapper objectMapper) {
        this.gameService = gameService;
        this.tokenServer = tokenServer;
        this.objectMapper = new ObjectMapper();
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
    public Map<String, Object> addGame(@RequestBody Game game, @RequestParam String token) {
        Map<String, Object> responseMap = new HashMap<>();
        if (tokenServer.verifyToken(token)) {
            if ("ROLE_ADMIN".equals(tokenServer.verifyTokenRole(token))) {
                Game addedGame = gameService.add(game);
                if (addedGame != null) {
                    responseMap.put("msg", "SUCCESS");
                    responseMap.put("data", addedGame);
                } else {
                    responseMap.put("msg", "FAILED");
                    responseMap.put("data", null);
                }
            } else {
                responseMap.put("msg", "FAILED YOU ARE NOT ADMIN");
                responseMap.put("data", null);
            }
        } else {
            responseMap.put("msg", "FAILED");
            responseMap.put("data", null);
        }
        return responseMap;
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
            } else {
                return "YOU ARE NOT ADMIN";
            }
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
            } else {
                return "YOU ARE NOT ADMIN";
            }
        } else {
            return "YOU NEED TO TRANSFER TOKEN";
        }
    }

    @Tag(name = "getAllGame", description = "coming the all games")
    @GetMapping("/getAllGame")
    public Map<String, Object> getAll(@RequestParam String token) {
        Map<String, Object> responseMap = new HashMap<>();
        if (tokenServer.verifyToken(token)) {
            List<Game> games = gameService.getAll();
            responseMap.put("msg", "SUCCESS");
            responseMap.put("data", games);
        } else {
            responseMap.put("msg", "FAILED");
            responseMap.put("data", new ArrayList<>()); // Return an empty list instead of null
        }
        return responseMap;
    }

    @Tag(name = "getLiveGame", description = "getting just Live Game")
    @GetMapping("/getLiveGame")
    public ResponseEntity<JsonNode> getLiveGame(@RequestParam String token) {
        if (tokenServer.verifyToken(token)) {
            Game liveGame = gameService.getLiveGame();
            if (liveGame != null) {
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("msg", "SUCCESS");
                responseMap.put("data", liveGame);
                try {
                    String response = objectMapper.writeValueAsString(responseMap);
                    JsonNode jsonNode = objectMapper.readTree(response);
                    return ResponseEntity.ok(jsonNode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // Handle the case when there is no live game available
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("msg", "FAILED");
                responseMap.put("data", null);
                try {
                    String response = objectMapper.writeValueAsString(responseMap);
                    JsonNode jsonNode = objectMapper.readTree(response);
                    return ResponseEntity.ok(jsonNode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build ();
    }

    @Tag(name = "get", description = "get just one Game by ID")
    @GetMapping("/get")
    public ResponseEntity<JsonNode> get(@RequestParam String id, @RequestParam String token) {
        if (tokenServer.verifyToken(token) && gameService.get(id)!=null) {
            try {
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("msg", "SUCCESS");
                responseMap.put("data", gameService.get(id));
                String response = objectMapper.writeValueAsString(responseMap);
                JsonNode jsonNode = objectMapper.readTree(response);
                return ResponseEntity.ok(jsonNode);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("msg", "FAILED GET BY ID");
        responseMap.put("data", null);
        try {
            String response = objectMapper.writeValueAsString(responseMap);
            JsonNode jsonNode = objectMapper.readTree(response);
            return ResponseEntity.ok(jsonNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @Tag(name = "delete", description = "delete just one Game by ID")
    @DeleteMapping("/delete")
    public String delete(@RequestParam String id, @RequestParam String token) {
        if (tokenServer.verifyToken(token)) {
            if ("ROLE_ADMIN".equals(tokenServer.verifyTokenRole(token))) {
                gameService.delete(id);
                return "DELETE SUCCESS";
            }
            return "FAILED DELETE, YOU ARE NOT ADMIN";
        }
        return "FAILED DELETE";
    }

    @Tag(name = "deleteAll", description = "deleteAll Game")
    @DeleteMapping("/deleteAll")
    public String deleteAll(@RequestParam String token) {
        if (tokenServer.verifyToken(token)) {
            if ("ROLE_ADMIN".equals(tokenServer.verifyTokenRole(token))) {
                gameService.deleteAll();
                return "DELETE ALL SUCCESS";
            }
            return "FAILED DELETE ALL, YOU ARE NOT ADMIN";
        }
        return "FAILED DELETE ALL";
    }

    @Tag(name = "deleteAllOthers", description = "deleteAll without live Game")
    @DeleteMapping("/deleteAllOthers")
    public String deleteAllOthers(@RequestParam String token) {
        if (tokenServer.verifyToken(token)) {
            if ("ROLE_ADMIN".equals(tokenServer.verifyTokenRole(token))) {
                gameService.deleteAllOthers();
                return "DELETE ALL OTHERS SUCCESS";
            }
            return "FAILED DELETE ALL OTHERS, YOU ARE NOT ADMIN";
        }
        return "FAILED DELETE ALL OTHERS";
    }
}
