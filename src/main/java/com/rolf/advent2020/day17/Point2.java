package com.rolf.advent2020.day17;

import java.util.Objects;

public class Point2 {
    private final int x;
    private final int y;
    private final int z;
    private final int w;
    private boolean active = false;

    public Point2(final int x, final int y, final int z, final int w, final boolean active) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
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

    public int getW() {
        return w;
    }

    public boolean isActive() {
        return active;
    }

    public Point2 setActive(final boolean active) {
        this.active = active;
        return this;
    }

    public Point2 clone() {
        return new Point2(x, y, z, w, active);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Point2 point = (Point2) o;
        return x == point.x &&
                y == point.y &&
                z == point.z &&
                w == point.w;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, w);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", w=" + w +
                ", active=" + active +
                '}';
    }
}
