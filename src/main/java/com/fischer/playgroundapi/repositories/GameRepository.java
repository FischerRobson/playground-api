package com.fischer.playgroundapi.repositories;
import com.fischer.playgroundapi.models.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface GameRepository extends MongoRepository<Game, String> {
}
