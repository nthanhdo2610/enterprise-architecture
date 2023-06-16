package cs544.service;

import cs544.dao.GameDao;
import cs544.domain.Game;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import java.util.List;
@Service
@Transactional
public class GameService {
    private MongoTemplate mongoTemplate;
    private final GameDao gameDao;

    public GameService(GameDao gameDao , MongoTemplate mongoTemplate)  {
        this.gameDao = gameDao;
        this.mongoTemplate = mongoTemplate;
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
            System.out.println("delete "+game.getLeagueName());
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
}