package com.rolf.advent2020.day11;

public enum Direction {
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, 1),
    DOWN(0, -1),
    UP_LEFT(-1, 1),
    UP_RIGHT(1, 1),
    DOWN_LEFT(-1, -1),
    DOWN_RIGHT(1, -1);

    private final int xDelta;
    private final int yDelta;

    Direction(final int xDelta, final int yDelta) {
        this.xDelta = xDelta;
        this.yDelta = yDelta;
    }

    public int getxDelta() {
        return xDelta;
    }

    public int getyDelta() {
        return yDelta;
    }

    @Override
    public String toString() {
        return "Direction{" +
                "name=" + name() +
                ", xDelta=" + xDelta +
                ", yDelta=" + yDelta +
                '}';
    }
}
