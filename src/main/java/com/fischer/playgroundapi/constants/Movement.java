package com.fischer.playgroundapi.constants;

public enum Movement {
    UP,
    DOWN,
    LEFT,
    RIGHT;

    public static boolean contains(Movement movement) {
        String test = String.valueOf(movement);
        for (Movement m : Movement.values()) {
            if (m.name().equalsIgnoreCase(test)) {
                return true;
            }
        }
        return false;
    }
}
