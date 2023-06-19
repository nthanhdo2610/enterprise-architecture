package cs544.service;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;
import org.springframework.web.client.RestTemplate;

import cs544.dao.CommentDao;
import cs544.dao.VoteDao;
import cs544.domain.Comment;
import cs544.domain.Vote;
import cs544.domain.Game;

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
