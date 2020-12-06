package com.rolf.advent2020.day3;

import java.util.List;

public class Map {

    private final Location[][] layout;

    public Map(final Location[][] layout) {
        this.layout = layout;
    }

    public Map(final List<List<Location>> layout) {
        final int yLength = layout.size();
        final int xLength = layout.get(0).size();
        this.layout = new Location[xLength][yLength];
        for (int y = 0; y < yLength; y++) {
            for (int x = 0; x < xLength; x++) {
                this.layout[x][y] = layout.get(y).get(x);
            }
        }

        // Set all parent locations for each location.
        for (int x = 0; x < this.layout.length; x++) {
            for (int y = 0; y < this.layout[x].length; y++) {
                final Location current = this.layout[x][y];
                current.setUp(get(x, y - 1));
                current.setDown(get(x, y + 1));
                current.setLeft(get(x - 1, y));
                current.setRight(get(x + 1, y));
            }
        }
    }

    public Location get(final int x, final int y) {
        int xx = x;

        // Loop right to left and the other way
        if (xx == -1) xx = layout.length - 1;
        if (xx == layout.length) xx = 0;

        if (xx < 0) return null;
        if (y >= layout[xx].length || y < 0) return null;
        return layout[xx][y];
    }
}
