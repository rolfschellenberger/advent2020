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
    protected boolean isEnabled() {
        return true;
    }

    @Override
    protected int getDay() {
        return DAY;
    }

    @Override
    protected Object getResult1() throws IOException {
        final List<String> lines = readLines();
        if(true) return null;
        final List<List<Direction>> moves = lines.stream()
                .map(LineParser::parse)
                .collect(Collectors.toList());
        final Tile start = new Tile();
        start.initialize(100);
//        int i = 1;
        for (final List<Direction> directions : moves) {
//            System.out.println((i++) + ": " + directions);
            start.walk(directions);
        }

        return start.getBlackCount();
    }

    @Override
    protected Object getResult2() throws IOException {
        final List<String> lines = readLines();
        final List<List<Direction>> moves = lines.stream()
                .map(LineParser::parse)
                .collect(Collectors.toList());
        final Tile start = new Tile();
        start.initialize(1000);
        for (final List<Direction> directions : moves) {
            start.walk(directions);
        }

        for (int d = 1; d <= 100; d++) {
            final Set<Tile> toFlip = start.findToFlip();
            toFlip.forEach(Tile::flip);
            System.out.println(d + ": " + start.getBlackCount());
        }

        return start.getBlackCount();
    }
}
