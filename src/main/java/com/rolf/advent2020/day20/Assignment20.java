package com.rolf.advent2020.day20;

import com.rolf.advent2020.util.Assignment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Assignment20 extends Assignment {

    private static final int DAY = 20;

    @Override
    protected int getDay() {
        return DAY;
    }

    @Override
    protected Object getResult1() throws IOException {
        final Puzzle puzzle = loadPuzzle();
        return puzzle.getCorners().stream()
                .map(Tile::getId)
                .reduce(1L, (a, b) -> a * b);
    }

    @Override
    protected Object getResult2() throws IOException {
        final Puzzle puzzle = loadPuzzle();
        puzzle.setGrid();
        final int seaMonsterCount = puzzle.findSeaMonsters();
        final int seaCount = puzzle.findSea();
        // A sea monster exists of 15 #
        return seaCount - (15 * seaMonsterCount);
    }

    private Puzzle loadPuzzle() throws IOException {
        return new Puzzle(loadTiles());
    }

    private List<Tile> loadTiles() throws IOException {
        final List<String> lines = readLines();
        final List<Tile> tiles = new ArrayList<>();
        final List<String> tile = new ArrayList<>();
        for (final String line : lines) {
            if (!line.isBlank()) {
                tile.add(line);
            } else {
                tiles.add(Tile.from(tile));
                tile.clear();
            }
        }
        if (!tile.isEmpty()) {
            tiles.add(Tile.from(tile));
            tile.clear();
        }

        // Line up all tiles with all tiles.
        for (final Tile tile1 : tiles) {
            for (final Tile tile2 : tiles) {
                tile1.lineUp(tile2);
            }
        }

        return tiles;
    }
}
