package com.fischer.playgroundapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GameDTO {
    private String id;
    private boolean isPlayer1;

    private Player player1;
    private Player player2;
}
