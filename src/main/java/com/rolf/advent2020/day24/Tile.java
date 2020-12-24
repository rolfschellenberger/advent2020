package com.rolf.advent2020.day24;

import java.util.Objects;

public class Tile {

    private final Point point;
    private boolean white = true;

    public Tile(final Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public boolean isWhite() {
        return white;
    }

    public boolean isBlack() {
        return !white;
    }

    public void flip() {
        white = !white;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Tile tile = (Tile) o;
        return Objects.equals(point, tile.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point);
    }

    @Override
    public String toString() {
        return "Tile{" +
                "point=" + point +
                ", white=" + white +
                '}';
    }
}
