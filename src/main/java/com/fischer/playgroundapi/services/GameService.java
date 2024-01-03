package com.fischer.playgroundapi.services;

import com.fischer.playgroundapi.constants.Movement;
import com.fischer.playgroundapi.exceptions.FullGameException;
import com.fischer.playgroundapi.exceptions.InvalidGameException;
import com.fischer.playgroundapi.exceptions.InvalidMovementException;
import com.fischer.playgroundapi.models.Game;
import com.fischer.playgroundapi.models.GameDTO;
import com.fischer.playgroundapi.models.Player;
import com.fischer.playgroundapi.models.PlayerDTO;
import com.fischer.playgroundapi.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class GameService {

    @Autowired
    Random random;

    @Autowired
    GameRepository gameRepository;

    private int getRandomPosition() {
        return random.nextInt(10);
    }

    private Game returnValidGameOrThrowInvalidGameException(Optional<Game> game) {
        if (game.isEmpty()) {
            throw new InvalidGameException();
        }

        Game existingGame = game.get();

        Duration duration = Duration.between(existingGame.getCreatedAt(), LocalDateTime.now());

        if (duration.toMinutes() > 5) {
            throw new InvalidGameException();
        }

        return existingGame;
    }


    public GameDTO create(PlayerDTO player) {
        Game game = new Game();
        Player player1 = new Player(player.getName(), getRandomPosition(),getRandomPosition() );
        game.setPlayer1(player1);
        Game savedGame = gameRepository.save(game);
        return new GameDTO(savedGame.getId(), true, savedGame.getPlayer1(), savedGame.getPlayer2());
    }

    public GameDTO join(PlayerDTO player, String gameId) {
        Optional<Game> game = gameRepository.findById(gameId);

        Game existingGame = returnValidGameOrThrowInvalidGameException(game);

        if (existingGame.getPlayer2() != null) {
            throw new FullGameException();
        }

        Player player2 = new Player(player.getName(), getRandomPosition(),getRandomPosition() );
        existingGame.setPlayer2(player2);
        Game savedGame = gameRepository.save(existingGame);
        return new GameDTO(savedGame.getId(), false, savedGame.getPlayer1(), savedGame.getPlayer2());
    }

    public GameDTO move(String gameId, boolean isPlayer1, Movement movement) {

        if (!Movement.contains(movement)) {
            throw new InvalidMovementException();
        }

        Optional<Game> game = gameRepository.findById(gameId);

        Game existingGame = returnValidGameOrThrowInvalidGameException(game);

        Player player;

        if (isPlayer1) {
            player = existingGame.getPlayer1();
            player.handleMovement(movement);
            existingGame.setPlayer1(player);
        } else {
            player = existingGame.getPlayer2();
            player.handleMovement(movement);
            existingGame.setPlayer2(player);
        }

        Game savedGame = gameRepository.save(existingGame);
        return new GameDTO(savedGame.getId(), isPlayer1, savedGame.getPlayer1(), savedGame.getPlayer2());
    }

    public List<Game> getGamesHistory() {
        return gameRepository.findAll();
    }

}
