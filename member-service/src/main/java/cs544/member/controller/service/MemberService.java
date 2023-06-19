package cs544.member.controller.service;

import cs544.member.controller.domain.Game;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import cs544.member.controller.dao.CommentDao;
import cs544.member.controller.dao.VoteDao;
import cs544.member.controller.domain.Comment;
import cs544.member.controller.domain.Vote;

@Service
@Transactional
public class MemberService {
    
    private final MongoTemplate mongoTemplate;
    private final CommentDao commentDao;
    private final VoteDao voteDao;
    //private final RestTemplate restTemplate;

    public MemberService(CommentDao commentDao, VoteDao voteDao, MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.voteDao = voteDao;
        //this.restTemplate = restTemplate;
        this.commentDao = commentDao;
    }

    public List<Comment> getAllComments(String gameId) {
        Query query = Query.query(Criteria.where("gameId").is(gameId));
        return mongoTemplate.find(query, Comment.class);
    }

    public List<Vote> getAllVotes(String gameId) {
        Query query = Query.query(Criteria.where("gameId").is(gameId));
        return mongoTemplate.find(query, Vote.class);
    }

    public Game getLiveGame() {
        Query query = Query.query(Criteria.where("status").is("live"));
        return mongoTemplate.findOne(query, Game.class);
    }

     public void addComment(Comment req) {
        commentDao.save(req);
     }

     public void addVote(Vote req) {
        voteDao.save(req);
     }
    
}
