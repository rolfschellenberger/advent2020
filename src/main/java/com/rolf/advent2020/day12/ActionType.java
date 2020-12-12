package com.rolf.advent2020.day12;

public enum ActionType {
    NORTH('N'),
    SOUTH('S'),
    EAST('E'),
    WEST('W'),
    LEFT('L'),
    RIGHT('R'),
    FORWARD('F');

    private final char letter;

    ActionType(final char letter) {
        this.letter = letter;
    }

    public static ActionType fromValue(final char value) {
        for (final ActionType action : values()) {
            if (action.letter == value) {
                return action;
            }
        }
        throw new RuntimeException("Invalid value: " + value);
    }
}
