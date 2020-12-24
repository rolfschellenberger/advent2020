package com.rolf.advent2020.day24;

public enum Direction {
    e,
    se,
    sw,
    w,
    nw,
    ne;

    public static boolean isValue(final String value) {
        for (final Direction d : values()) {
            if (d.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
