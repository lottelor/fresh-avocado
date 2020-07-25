package com.antria.freshavocado.database;

import java.util.HashMap;
import java.util.Map;

public enum Ripeness {
    HARD(1),
    RIPE(2),
    ROTTEN(3);

    private int value;
    private static Map map = new HashMap<>();

    private Ripeness(int value) {
        this.value = value;
    }

    static {
        for (Ripeness ripeness : Ripeness.values()) {
            map.put(ripeness.value, ripeness);
        }
    }

    public static Ripeness valueOf(int ripeness) {
        return (Ripeness) map.get(ripeness);
    }

    public int getValue() {
        return value;
    }
}