package com.rolf.advent2020.day12;

public class Waypoint {

    private int x;
    private int y;

    public Waypoint(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Waypoint{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public void rotate(final Action action) {
        /*
        xRot = xCenter + cos(Angle) * (x - xCenter) - sin(-Angle) * (y - yCenter)
        yRot = yCenter + sin(-Angle) * (x - xCenter) + cos(Angle) * (y - yCenter)
         */
        final int actionValue = (action.getValue() % 360);
        final int degree = action.getActionType() == ActionType.LEFT ? 360 - actionValue : actionValue;
        final double angleRadians = degree * Math.PI / 180.0;
        final int newX = (int) Math.round((Math.cos(angleRadians) * x - Math.sin(-angleRadians) * y));
        final int newY = (int) Math.round(Math.sin(-angleRadians) * x + Math.cos(angleRadians) * y);
        this.x = newX;
        this.y = newY;
    }

    public void move(final Action action) {
        final int value = action.getValue();
        switch (action.getActionType()) {
            case NORTH -> move(Direction.NORTH, value);
            case EAST -> move(Direction.EAST, value);
            case SOUTH -> move(Direction.SOUTH, value);
            case WEST -> move(Direction.WEST, value);
        }
    }

    private void move(final Direction direction, final int value) {
        x += value * direction.getX();
        y += value * direction.getY();
    }
}
