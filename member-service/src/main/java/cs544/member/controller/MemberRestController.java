package cs544.member.controller;

import java.util.List;

import cs544.member.controller.domain.Game;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cs544.member.controller.domain.Comment;
import cs544.member.controller.domain.Vote;
import cs544.member.controller.service.MemberService;

@RestController
public class MemberRestController {

    private final MemberService memService;
    private final TokenServer tokenServer;

    public MemberRestController(MemberService mService, TokenServer tokenServer) {
        this.memService = mService;
        this.tokenServer = tokenServer;
    }


    @GetMapping
    public String getAll() {
        return "Success";
    }

    @GetMapping("/comments")
    public List<Comment> Comments(@RequestParam String gameId) {
        return memService.getAllComments(gameId);
    }

    
    @PostMapping("/comments")
    public String Comments(@RequestParam String token, @RequestBody Comment comment) {
        if (tokenServer.verifyToken(token)) {
            Game game = memService.getLiveGame();
            System.out.println(game);
            if (game != null) {
                comment.setGameId(game.getGameId());
                memService.addComment(comment);
                return "Success";
            } else {
                 return "STOP FAILED FROM STREAM";
             }
        } else {
            return "YOU NEED TO TRANSFER TOKEN";
        }
    }

    @GetMapping("/votes")
    public List<Vote> Votes(@RequestParam String gameId) {
        return memService.getAllVotes(gameId);
    }

    
    @PostMapping("/votes")
    public String Votes(@RequestParam String token, @RequestBody Vote vote) {
        if (tokenServer.verifyToken(token)) {
            Game game = memService.getLiveGame();
            System.out.println(game);
            if (game != null) {
                vote.setGameId(game.getGameId());
                memService.addVote(vote);
                return "Success";
            } else {
                 return "STOP FAILED FROM STREAM";
             }
        } else {
            return "YOU NEED TO TRANSFER TOKEN";
        }
    }
    
}
