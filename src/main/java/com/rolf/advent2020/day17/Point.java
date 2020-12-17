package com.rolf.advent2020.day17;

import java.util.Objects;

public class Point {
    private final int x;
    private final int y;
    private final int z;
    private boolean active = false;

    public Point(final int x, final int y, final int z, final boolean active) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.active = active;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public boolean isActive() {
        return active;
    }

    public Point setActive(final boolean active) {
        this.active = active;
        return this;
    }

    public Point clone() {
        return new Point(x, y, z, active);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Point point = (Point) o;
        return x == point.x &&
                y == point.y &&
                z == point.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", active=" + active +
                '}';
    }
}
