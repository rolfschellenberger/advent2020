package com.rolf.advent2020.day11;

public class Location {
    private final int x;
    private final int y;
    private Type type;

    public Location(final int x, final int y, final Type type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void setType(final Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", type=" + type +
                '}';
    }
}
