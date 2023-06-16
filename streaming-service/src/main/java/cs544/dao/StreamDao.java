package cs544.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import cs544.domain.Game;

public interface StreamDao extends MongoRepository<Game, String> {

}