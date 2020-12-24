package com.rolf.advent2020.day24;

public enum Direction {
    e(2, 0),
    se(1, -1),
    sw(-1, -1),
    w(-2, 0),
    nw(-1, 1),
    ne(1, 1);

    private final int deltaX;
    private final int deltaY;

    Direction(final int x, final int y) {
        this.deltaX = x;
        this.deltaY = y;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    public static boolean isValue(final String value) {
        for (final Direction d : values()) {
            if (d.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
