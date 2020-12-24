package com.rolf.advent2020.day24;

import java.util.ArrayList;
import java.util.List;

public class LineParser {
    private LineParser() {
    }

    public static List<Direction> parse(final String line) {
        final List<Direction> result = new ArrayList<>();

        String value = "";
        for (final char c : line.toCharArray()) {
            value += c + "";
            if (Direction.isValue(value)) {
                result.add(Direction.valueOf(value));
                value = "";
            }
        }

        return result;
    }
}
