package cs544.service;

import cs544.dao.GameDao;
import cs544.domain.Game;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class GameService {
    private MongoTemplate mongoTemplate;
    private final GameDao gameDao;
    private final RestTemplate restTemplate;

    public GameService(GameDao gameDao, MongoTemplate mongoTemplate, RestTemplate restTemplate) {
        this.gameDao = gameDao;
        this.mongoTemplate = mongoTemplate;
        this.restTemplate = restTemplate;
    }

    public List<Game> getAll() {
        return gameDao.findAll();
    }

    public Game add(Game game) {
        if (game.getStatus().equals("live") && hasLiveGame()) {
            System.out.println("One game status is already live");
            return null;
        } else {
            return gameDao.save(game);
        }
    }
    
    public String setStopToLive(Game game) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Game> requestEntity = new HttpEntity<>(game, headers);
        // Call the external API using POST method
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8082/stream/UnPublishGame",
                HttpMethod.POST,
                requestEntity,
                String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            // API call succeeded, save the game
            if (game != null) {
                game.setStatus("draft");
                gameDao.save(game);
            }
            return "STOPPED";
        } else {
            // API call failed, handle the error
            System.out.println("Failed to unpublish game");
            return "Failed to unpublish game";
        }
    }
    public String setStartToLive(Game game) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Game> requestEntity = new HttpEntity<>(game, headers);

        // Call the external API using POST method
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8082/stream/publishGame",
                HttpMethod.POST,
                requestEntity,
                String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            // API call succeeded, save the game
            if (game != null) {
                game.setStatus("live");
                gameDao.save(game);
            }
            return "STARTED";
        } else {
            // API call failed, handle the error
            System.out.println("Failed to publish game");
            return "Failed to publish game";
        }
    }

    public Game get(String id) {
        return gameDao.findById(id).orElse(null);
    }

    public void update(Game game) {
        gameDao.save(game);
    }

    public void delete(String id) {
        gameDao.delete(get(id));
    }

    public void deleteAll() {
        gameDao.deleteAll();
    }

    public void deleteAllOthers() {
        Query query = new Query();
        query.addCriteria(Criteria.where("status").ne("live"));
        List<Game> gamesToDelete = mongoTemplate.find(query, Game.class);
        for (Game game : gamesToDelete) {
            System.out.println("delete " + game.getLeagueName());
            gameDao.delete(game);
        }
    }

    private boolean hasLiveGame() {
        Query query = Query.query(Criteria.where("status").is("live"));
        return mongoTemplate.exists(query, Game.class);
    }

    public Game getLiveGame() {
        Query query = Query.query(Criteria.where("status").is("live"));
        return mongoTemplate.findOne(query, Game.class);
    }

    public Game getNoLiveGame() {
        Query query = Query.query(Criteria.where("status").ne("live"));
        return mongoTemplate.findOne(query, Game.class);
    }
}