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
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
public class MemberRestController {

    private final MemberService memService;
    private final TokenServer tokenServer;

    public MemberRestController(MemberService mService, TokenServer tokenServer) {
        this.memService = mService;
        this.tokenServer = tokenServer;
    }

    @Tag(name = "To test Api connection", description = "Just to check if api works")
    @GetMapping
    public String getAll() {
        return "Success";
    }

    @Tag(name = "Get Comments", description = "This is public api anyone can access to view live game comments, but if no live game exists it will return empty array")
    @GetMapping("/comments")
    public List<Comment> Comments() {
        Game game = memService.getLiveGame();
        System.out.println(game);
        if (game != null && game.getGameId() != null) {
            return memService.getAllComments(game.getGameId());
        } else {
            return null;
        }

    }

    @Tag(name = "Add Comments", description = "This member based api, requires accesstoken, user access to to add comment on live game")
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

    @Tag(name = "Get Votes", description = "This is public api, requires live gameid, to view live game comments")
    @GetMapping("/votes")
    public List<Vote> Votes() {
        Game game = memService.getLiveGame();
        System.out.println(game);
        if (game != null && game.getGameId() != null) {
            return memService.getAllVotes(game.getGameId());
        } else {
            return null;
        }
    }

    @Tag(name = "Add Votes", description = "This is member api, it will add vote to current live game")
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
