package com.rolf.advent2020.day8;

public class Result {
    private final int line;
    private final int value;

    public Result(final int line, final int value) {
        this.line = line;
        this.value = value;
    }

    public int getLine() {
        return line;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Result{" +
                "line=" + line +
                ", value=" + value +
                '}';
    }
}
