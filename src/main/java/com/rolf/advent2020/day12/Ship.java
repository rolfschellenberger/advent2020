package com.rolf.advent2020.day12;

public class Ship {

    private int x;
    private int y;

    public Ship() {
        this.x = 0;
        this.y = 0;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move(final Waypoint waypoint, final int times) {
        x += times * waypoint.getX();
        y += times * waypoint.getY();
    }

    @Override
    public String toString() {
        return "Ship{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
