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

@RestController
public class GameRestController {
    private final GameService gameService;
    public GameRestController(GameService gameService) {
        this.gameService = gameService;
    }
    @PostMapping("/game/addGame")
	public Game addGame(@RequestBody Game game) {
		return gameService.add(game);
	}
    @PostMapping("/game/setStart")
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
    @PostMapping("/game/setStop")
	public String setStop() {
        if (gameService.getLiveGame()==null){
            return "THERE DOESN'T HAVE LIVE GAME";
        }else{
            // if (gameService.getAll().size()==0){
            //     return "WE DON'T HAVE GAME";
            // }else{
                return gameService.setStopToLive(gameService.getLiveGame());
            // }
        }
	}
    @GetMapping("/game/getAllGame")
    public List<Game> getAll() {
        return gameService.getAll();
    }
    @GetMapping("/game/getLiveGame")
    public Game getLiveGame() {
        return gameService.getLiveGame();
    }
    @GetMapping("/game/{id}")
    public Game get(@PathVariable String id) {
        return gameService.get(id);
    }

    @PostMapping
    public RedirectView add(@RequestBody Game game) {
        gameService.add(game);
        return new RedirectView("/game/" + game.getGameId());
    }

    @PutMapping
    public void update(@RequestBody Game game) {
        gameService.update(game);
    }

    @DeleteMapping("/game/{id}")
    public void delete(@PathVariable String id) {
        gameService.delete(id);
    }
    @DeleteMapping("/game/deleteAll")
    public void deleteAll() {
        gameService.deleteAll();
    }
    @DeleteMapping("/game/deleteAllOthers")
    public void deleteAllOthers() {
        gameService.deleteAllOthers();
    }
}
