package com.rolf.advent2020.day24;

import com.rolf.advent2020.util.Assignment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Assignment24 extends Assignment {

    private static final int DAY = 24;

    @Override
    protected int getDay() {
        return DAY;
    }

    @Override
    protected Object getResult1() throws IOException {
        final Floor floor = walkFloor();
        return floor.getBlackCount();
    }

    @Override
    protected Object getResult2() throws IOException {
        final Floor floor = walkFloor();

        // Flip for 100 days
        for (int day = 1; day <= 100; day++) {
            final Set<Tile> tiles = floor.findTilesToFlip();
            tiles.forEach(Tile::flip);
        }

        return floor.getBlackCount();
    }

    private Floor walkFloor() throws IOException {
        final List<String> lines = readLines();
        final List<List<Direction>> moves = lines.stream()
                .map(LineParser::parse)
                .collect(Collectors.toList());

        final Floor floor = new Floor();
        for (final List<Direction> directions : moves) {
            final Tile tile = floor.findTile(directions);
            tile.flip();
        }
        return floor;
    }
}
