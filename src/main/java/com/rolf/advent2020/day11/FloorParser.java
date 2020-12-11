package com.rolf.advent2020.day11;

import java.util.List;

public class FloorParser {
    private FloorParser() {
    }

    public static Floor parse(final List<String> input) {
        final Location[][] locations = new Location[input.get(0).length()][input.size()];

        for (int y = 0; y < input.size(); y++) {
            final char[] line = input.get(y).toCharArray();
            for (int x = 0; x < line.length; x++) {
                char c = line[x];
                switch (c) {
                    case 'L' -> locations[x][y] = new Location(x, y, Type.CHAIR_EMPTY);
                    case '#' -> locations[x][y] = new Location(x, y, Type.CHAIR_OCCUPIED);
                    case '.' -> locations[x][y] = new Location(x, y, Type.FLOOR);
                }
            }
        }

        return new Floor(locations);
    }
}
