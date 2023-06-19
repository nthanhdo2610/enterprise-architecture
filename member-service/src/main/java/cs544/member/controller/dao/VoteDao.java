package cs544.member.controller.dao;

import cs544.member.controller.domain.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VoteDao extends MongoRepository<Vote, String> {
    
}


