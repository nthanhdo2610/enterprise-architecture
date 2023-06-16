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
    @PostMapping("/addGame")
	public Game addUser(@RequestBody Game game) {
		return gameService.add(game);
	}
    public GameRestController(GameService gameService) {
        this.gameService = gameService;
    }
    @GetMapping("/getAllGame")
    public List<Game> getAll() {
        return gameService.getAll();
    }
    @GetMapping("/getLiveGame")
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
