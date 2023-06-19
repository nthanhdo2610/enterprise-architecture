package cs544.member.controller.dao;

import cs544.member.controller.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentDao extends MongoRepository<Comment, String> {
    
}
