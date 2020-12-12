package com.rolf.advent2020.day12;

public class Ferry {

    private int x;
    private int y;
    private Direction direction;

    public Ferry() {
        this.x = 0;
        this.y = 0;
        direction = Direction.EAST;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Ferry{" +
                "x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                '}';
    }

    public void move(final Action action) {
        final int value = action.getValue();
        switch (action.getActionType()) {
            case NORTH -> move(Direction.NORTH, value);
            case EAST -> move(Direction.EAST, value);
            case SOUTH -> move(Direction.SOUTH, value);
            case WEST -> move(Direction.WEST, value);
            case FORWARD -> move(direction, value);
            case LEFT -> rotateLeft(value);
            case RIGHT -> rotateRight(value);
        }
    }

    private void rotateLeft(final int value) {
        this.direction = direction.rotateLeft(value);
    }

    private void rotateRight(final int value) {
        this.direction = direction.rotateRight(value);
    }

    private void move(final Direction direction, final int value) {
        x += value * direction.getX();
        y += value * direction.getY();
    }
}
