package com.fischer.playgroundapi.controllers;

import com.fischer.playgroundapi.exceptions.InvalidGameException;
import com.fischer.playgroundapi.models.GameDTO;
import com.fischer.playgroundapi.models.MoveDTO;
import com.fischer.playgroundapi.models.Player;
import com.fischer.playgroundapi.models.PlayerDTO;
import com.fischer.playgroundapi.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameService gameService;

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody PlayerDTO player) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.create(player));
    }

    @PostMapping("/join/{room}")
    public ResponseEntity join(@PathVariable String room, @RequestBody PlayerDTO player) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(gameService.join(player, room));
        } catch (InvalidGameException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/move/{room}")
    public ResponseEntity move(@PathVariable String room, @RequestParam boolean isPlayer1, @RequestBody MoveDTO move) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(gameService.move(room, isPlayer1, move.getMovement()));
        } catch (Exception e) {
           if (e instanceof HttpMessageNotReadableException) {
               return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
           }
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }


    @GetMapping("/history")
    public ResponseEntity getGamesHistory() {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.getGamesHistory());
    }

}
