package cs544.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import cs544.domain.Game;
import cs544.service.StreamService;
@RestController
public class StreamController {
    private final StreamService streamService;
    
    public StreamController(StreamService streamService) {
        this.streamService = streamService;
    }

    @PostMapping("/stream/publishGame")
	public String publishGame(@RequestBody Game game) {
        System.out.println("PUBLISHED GAMMEE*****"+game);
		return streamService.publishGame();
	}
    @PostMapping("/stream/UnPublishGame")
	public String UnPublishGame(@RequestBody Game game) {
        System.out.println("------------UNPUBLISHED GAMMEE*****"+game);
		return streamService.UnPublishGame();
	}
}