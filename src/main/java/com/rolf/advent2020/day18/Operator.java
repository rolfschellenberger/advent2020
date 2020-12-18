package com.rolf.advent2020.day18;

public enum Operator {
    SUM('+'),
    MULTIPLY('*'),
    NONE('@');

    private final char value;

    Operator(final char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public static Operator from(final char value) {
        for (final Operator operator : values()) {
            if (operator.value == value) {
                return operator;
            }
        }
        return NONE;
    }
}
