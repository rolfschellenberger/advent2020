package com.rolf.advent2020.day05;

import java.util.Stack;

public class SideParser {

    private SideParser() {
    }

    public static BoardingPass parse(final String input) {
        if (input == null || input.length() != 10) {
            throw new RuntimeException("Invalid input: " + input);
        }
        final String rowInput = input.substring(0, 7);
        final String columnInput = input.substring(7, 10);
        final Stack<Side> rowSides = toStack(rowInput);
        final Stack<Side> columnSides = toStack(columnInput);
        return BoardingPass.from(rowSides, columnSides);
    }

    private static Stack<Side> toStack(final String input) {
        final Stack<Side> stack = new Stack<>();
        final char[] characters = input.toCharArray();
        for (int i = input.length() - 1; i >= 0; i--) {
            final Side side = Side.fromValue(characters[i]);
            stack.add(side);
        }
        return stack;
    }
}
