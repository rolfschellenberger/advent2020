package com.rolf.advent2020.day12;

import java.util.LinkedList;

public enum Direction {
    NORTH(0, 1),
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0);

    private static final LinkedList<Direction> LINK = new LinkedList<>() {
        {
            add(NORTH);
            add(EAST);
            add(SOUTH);
            add(WEST);
        }
    };

    private final int x;
    private final int y;

    Direction(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction rotateLeft(final int value) {
        final int index = getDirectionPos(this);
        final int step = value / 90 % 4;
        final int newIndex = index + (4 - step);
        return LINK.get(newIndex % 4);
    }

    public Direction rotateRight(final int value) {
        final int index = getDirectionPos(this);
        final int step = value / 90 % 4;
        final int newIndex = index + step;
        return LINK.get(newIndex % 4);
    }

    private int getDirectionPos(final Direction direction) {
        for (int i = 0; i < LINK.size(); i++) {
            if (LINK.get(i) == direction) {
                return i;
            }
        }
        throw new RuntimeException("Direction " + direction + " not found.");
    }
}
