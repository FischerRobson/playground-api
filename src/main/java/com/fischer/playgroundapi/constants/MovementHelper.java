package com.fischer.playgroundapi.constants;

import java.util.EnumMap;
import java.util.Map;


public class MovementHelper {
    private static final Map<Movement, Map<Axis, Integer>> movementMap;

    static  {
        movementMap = new EnumMap<>(Movement.class);

        Map<Axis, Integer> up = new EnumMap<>(Axis.class);
        up.put(Axis.X, 0);
        up.put(Axis.Y, -1);

        Map<Axis, Integer> down = new EnumMap<>(Axis.class);
        down.put(Axis.X, 0);
        down.put(Axis.Y, 1);

        Map<Axis, Integer> left = new EnumMap<>(Axis.class);
        left.put(Axis.X, -1);
        left.put(Axis.Y, 0);

        Map<Axis, Integer> right = new EnumMap<>(Axis.class);
        right.put(Axis.X, 1);
        right.put(Axis.Y, 0);

        movementMap.put(Movement.UP, up);
        movementMap.put(Movement.DOWN, down);
        movementMap.put(Movement.LEFT, left);
        movementMap.put(Movement.RIGHT, right);
    }

    public static Map<Axis, Integer> getMovement(Movement movement) {
        return movementMap.get(movement);
    }
}
