package com.rolf.advent2020.day3;

public class Move {

    private final Direction direction;
    private final int steps;

    public Move(final Direction direction, final int steps) {
        this.direction = direction;
        this.steps = steps;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getSteps() {
        return steps;
    }

    @Override
    public String toString() {
        return "Move{" +
                "direction=" + direction +
                ", steps=" + steps +
                '}';
    }
}
