package com.rolf.advent2020.day05;

import java.util.Stack;

public class BoardingPass {

    private final int row;
    private final int column;

    private BoardingPass(final int row, final int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getSeatId() {
        return row * 8 + column;
    }

    public static BoardingPass from(final Stack<Side> rowSides, final Stack<Side> columnSides) {
        final int row = findLocation(rowSides);
        final int column = findLocation(columnSides);
        return new BoardingPass(row, column);
    }

    private static int findLocation(final Stack<Side> sides) {
        final int min = 0;
        final int max = (int) Math.pow(2, sides.size());
        return binarySearch(min, max, sides);
    }

    private static int binarySearch(final int min, final int max, final Stack<Side> sides) {
        if (min == max || sides.isEmpty()) {
            return min;
        }
        final Side side = sides.pop();
        return switch (side) {
            case UPPER -> binarySearch(min + (max - min + 1) / 2, max, sides);
            case LOWER -> binarySearch(min, min + (max - min - 1) / 2, sides);
        };
    }
}
