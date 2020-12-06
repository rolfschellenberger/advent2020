package com.rolf.advent2020.day5;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum Side {
    UPPER('B', 'R'),
    LOWER('F', 'L');

    private final Set<Character> values = new HashSet<>();

    Side(final Character... values) {
        this.values.addAll(Arrays.asList(values));
    }

    public static Side fromValue(final char character) {
        for (Side side : values()) {
            if (side.values.contains(character)) {
                return side;
            }
        }
        throw new RuntimeException("Invalid character: " + character);
    }
}
