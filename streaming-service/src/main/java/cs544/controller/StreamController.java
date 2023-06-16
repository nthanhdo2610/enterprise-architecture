package cs544.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import cs544.domain.Game;
import cs544.service.StreamService;
import java.util.List;

@RestController
public class StreamController {
    private final StreamService streamService;
    
    public StreamController(StreamService streamService) {
        this.streamService = streamService;
    }

    @PostMapping("/stream/publishGame")
	public String publishGame(@RequestBody Game game) {
        System.out.println("GAMMEE*****"+game);
		return streamService.publishGame();
	}
    
    // @GetMapping("/getAllGame")
    // public List<Game> getAll() {
    //     return streamService.getAll();
    // }
    // @GetMapping("/getLiveGame")
    // public Game getLiveGame() {
    //     return streamService.getLiveGame();
    // }
    // @GetMapping("/game/{id}")
    // public Game get(@PathVariable String id) {
    //     return streamService.get(id);
    // }

    // @PostMapping
    // public RedirectView add(@RequestBody Game game) {
    //     streamService.add(game);
    //     return new RedirectView("/game/" + game.getGameId());
    // }

    // @PutMapping
    // public void update(@RequestBody Game game) {
    //     streamService.update(game);
    // }

    // @DeleteMapping("/game/{id}")
    // public void delete(@PathVariable String id) {
    //     streamService.delete(id);
    // }
    // @DeleteMapping("/game/deleteAll")
    // public void deleteAll() {
    //     streamService.deleteAll();
    // }
    // @DeleteMapping("/game/deleteAllOthers")
    // public void deleteAllOthers() {
    //     streamService.deleteAllOthers();
    // }
}
