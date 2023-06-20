package cs544.service;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.web.client.RestTemplate;

import cs544.Sender;
import cs544.dao.GameDao;
import cs544.domain.Game;
import lombok.Setter;

@Service
@Transactional
@Setter
public class GameService {
    private MongoTemplate mongoTemplate;
    private final GameDao gameDao;
    private final RestTemplate restTemplate;
    private final Sender rabbitMqSender;
    private Timer timer;
    private Game gameMain;

    public GameService(GameDao gameDao, MongoTemplate mongoTemplate, RestTemplate restTemplate, Sender rabbitMqSender) {
        this.gameDao = gameDao;
        this.mongoTemplate = mongoTemplate;
        this.restTemplate = restTemplate;
        this.rabbitMqSender = rabbitMqSender;
    }

    public List<Game> getAll() {
        return gameDao.findAll();
    }

    public Game add(Game game) {
        if (game.getStatus().equals("live") && hasLiveGame()) {
            System.out.println("One game status is already live");
            return null;
        } else {
            this.gameMain = game;
            return gameDao.save(game);
        }
    }

    public String setScore(Game game, int goalHome, int goalVisit, String token) {
        int save_dur = gameMain.getDurationMinutes();
        this.gameMain = game;
        if (game != null) {
            game.setGoalHome(goalHome);
            game.setGoalVisit(goalVisit);
            gameDao.save(game);
            System.out.println("save_dur"+save_dur);
            gameMain = game;
            gameMain.setDurationMinutes(save_dur);
            rabbitMqSender.sendMessage("stream_transfer_result", game.getGameResult());
            return "UPDATED GAME SCORE" + game.toString();
        }
        return "Failed to score update";
    }

    public String setGameStatus(Game game, String status) {
        if (status == "live") {
            game.setGoalHome(0);
            game.setGoalVisit(0);
            game.setWonTeam(null);
            game.setDurationMinutes(90);
        } else {
            if (game.getGoalHome() > game.getGoalVisit()) {
                game.setWonTeam(game.getTeamNameHome());
            } else if (game.getGoalHome() < game.getGoalVisit()) {
                game.setWonTeam(game.getTeamNameVisitor());
            } else {
                game.setWonTeam("Draw");
            }
            game.setDurationMinutes(0);
        }
        game.setStatus(status);
        gameDao.save(game);
        this.gameMain = game;
        return status + " GAME";
    }

    public String setStopToLive(Game game, String token) {
        setGameStatus(game, "draft");
        rabbitMqSender.sendMessage("stream_transfer_result", game.getGameResult());
        return "STOPPED";
    }

    public String setStartToLive(Game game, String token) {
        setGameStatus(game, "live");
        this.gameMain = game;
        startTimer();
        return "STARTED";
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
        timer.scheduleAtFixedRate(timerTask, 0, 3000);
    }

    public void decreaseMinutes() {
        if (gameMain != null) {
            if (gameMain.getDurationMinutes() == 0) {
                setGameStatus(gameMain, "stop");
                if (timer != null) {
                    timer.cancel();
                }
            } else {
                String msg = gameMain.getGameResult();
                gameMain.setDurationMinutes(gameMain.getDurationMinutes() - 1);
                rabbitMqSender.sendMessage("stream_transfer_result", msg);
            }
        } else {
            if (timer != null) {
                timer.cancel();
            }
        }
    }
    public Game get(String id) {
        return gameDao.findById(id).orElse(null);
    }

    public void update(Game game) {
        gameDao.save(game);
        this.gameMain = game;
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