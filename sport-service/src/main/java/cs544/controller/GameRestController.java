package cs544.controller;

import cs544.domain.Game;
import cs544.service.GameService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;

@RestController
public class GameRestController {
    private final GameService gameService;
    public GameRestController(GameService gameService) {
        this.gameService = gameService;
    }
    @PostMapping("/setScore")
	public String setScore(@RequestBody Map<String, Integer> scores) {
        if (gameService.getLiveGame()==null){
            return "WE DON'T LIVE HAVE GAME";
        }else{
            System.out.println("=------"+scores);
            int homeScore = scores.getOrDefault("home", 0);
            int visitScore = scores.getOrDefault("visit", 0);
            return gameService.setScore(gameService.getLiveGame(), homeScore, visitScore);
        }
	}
    @PostMapping("/addGame")
	public Game addGame(@RequestBody Game game) {
		return gameService.add(game);
	}
    @PostMapping("/setStart")
	public String setStart() {
        if (gameService.getLiveGame()!=null){
            return "ALREADY STARTED";
        }else{
            if (gameService.getAll().size()==0){
                return "WE DON'T HAVE GAME";
            }else{
                return gameService.setStartToLive(gameService.getNoLiveGame());
            }
        }
	}
    @PostMapping("/setStop")
	public String setStop() {
        if (gameService.getLiveGame()==null){
            return "THERE DOESN'T HAVE LIVE GAME";
        }else{
            return gameService.setStopToLive(gameService.getLiveGame());
        }
	}
    @GetMapping("/getAllGame")
    public List<Game> getAll() {
        return gameService.getAll();
    }
    @GetMapping("/getLiveGame")
    public Game getLiveGame() {
        return gameService.getLiveGame();
    }
    @GetMapping("/{id}")
    public Game get(@PathVariable String id) {
        return gameService.get(id);
    }

    @PostMapping
    public RedirectView add(@RequestBody Game game) {
        gameService.add(game);
        return new RedirectView("/" + game.getGameId());
    }

    @PutMapping
    public void update(@RequestBody Game game) {
        gameService.update(game);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        gameService.delete(id);
    }
    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        gameService.deleteAll();
    }
    @DeleteMapping("/deleteAllOthers")
    public void deleteAllOthers() {
        gameService.deleteAllOthers();
    }
}
