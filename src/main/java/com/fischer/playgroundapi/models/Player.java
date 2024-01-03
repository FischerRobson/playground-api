package com.fischer.playgroundapi.models;

import com.fischer.playgroundapi.constants.Axis;
import com.fischer.playgroundapi.constants.Movement;
import com.fischer.playgroundapi.constants.MovementHelper;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class Player {
    private String name;
    private int x, y;

    public void handleMovement(Movement movement) {
        Map<Axis, Integer> movementMap = MovementHelper.getMovement(movement);

        int newX = this.x + movementMap.get(Axis.X);
        int newY = this.y + movementMap.get(Axis.Y);

        if (newX >= 0 && newY >= 0 && newX <= 9 && newY <= 9) {
            this.x = newX;
            this.y = newY;
        }
    }
}
