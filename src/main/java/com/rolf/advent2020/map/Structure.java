package com.rolf.advent2020.map;

public enum Structure {
    SQUARE("."),
    TREE("#");

    private final String value;

    Structure(final String value) {
        this.value = value;
    }

    public static Structure fromValue(final String input) {
        for (final Structure structure : values()) {
            if (structure.value.equals(input)) {
                return structure;
            }
        }
        return null;
    }
}
