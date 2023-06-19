package cs544.dao;

import cs544.domain.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VoteDao extends MongoRepository<Vote, String> {
    
}


