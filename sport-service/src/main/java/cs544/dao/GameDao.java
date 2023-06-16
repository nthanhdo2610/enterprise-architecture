package cs544.dao;

import cs544.domain.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameDao extends MongoRepository<Game, String> {

}