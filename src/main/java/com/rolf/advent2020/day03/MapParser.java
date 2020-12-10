package com.rolf.advent2020.day03;

import java.util.ArrayList;
import java.util.List;

public class MapParser {

    private MapParser() {
    }

    public static Map parse(final List<String> input) {
        final List<List<Location>> locations = new ArrayList<>();

        for (final String line : input) {
            final List<Location> row = new ArrayList<>();
            for (final char c : line.toCharArray()) {
                row.add(LocationParser.parse(String.valueOf(c)));
            }
            locations.add(row);
        }

        return new Map(locations);
    }
}
