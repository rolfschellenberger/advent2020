package com.rolf.advent2020.map;

import java.util.List;

public class Location {

    private final Structure structure;
    private Location up;
    private Location down;
    private Location left;
    private Location right;

    public Location(final Structure structure) {
        this.structure = structure;
    }

    public Structure getStructure() {
        return structure;
    }

    public Location getUp() {
        return up;
    }

    public Location setUp(final Location up) {
        this.up = up;
        return this;
    }

    public Location getDown() {
        return down;
    }

    public Location setDown(final Location down) {
        this.down = down;
        return this;
    }

    public Location getLeft() {
        return left;
    }

    public Location setLeft(final Location left) {
        this.left = left;
        return this;
    }

    public Location getRight() {
        return right;
    }

    public Location setRight(final Location right) {
        this.right = right;
        return this;
    }

    public Location get(final Direction direction) {
        return switch (direction) {
            case UP -> getUp();
            case DOWN -> getDown();
            case LEFT -> getLeft();
            case RIGHT -> getRight();
        };
    }

    public Location move(final List<Move> moves) {
        Location result = this;
        for (final Move move : moves) {
            result = result.move(move);
        }
        return result;
    }

    public Location move(final Move move) {
        Location result = this;
        for (int i = 0; i < move.getSteps(); i++) {
            result = result == null ? null : result.get(move.getDirection());
        }
        return result;
    }

    @Override
    public String toString() {
        return "Location{" +
                "structure=" + structure +
                ", up=" + up +
                ", down=" + down +
                '}';
    }
}
