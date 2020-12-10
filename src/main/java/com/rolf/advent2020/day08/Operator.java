package com.rolf.advent2020.day08;

public enum Operator {
    ACC,
    JMP,
    NOP;

    public static Operator fromValue(final String value) {
        for (final Operator operator : values()) {
            if (operator.name().equalsIgnoreCase(value)) {
                return operator;
            }
        }
        throw new RuntimeException("Invalid operator: " + value);
    }
}
