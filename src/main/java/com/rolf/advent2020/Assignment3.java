package com.rolf.advent2020;

import com.rolf.advent2020.map.*;
import com.rolf.advent2020.util.FileReader;
import com.rolf.advent2020.map.MapParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Assignment3 {

    private final Logger logger = LoggerFactory.getLogger(Assignment3.class);

//    @Scheduled(initialDelay = 0, fixedDelay = Integer.MAX_VALUE)
    public void run1() throws IOException {
        logger.info("Starting 3a...");
        final List<String> lines = FileReader.readLines("/3a");
        final Map map = MapParser.parse(lines);
        final MapMover mapMover = new MapMover(map);
        final List<Move> moves = new ArrayList<>();
        moves.add(new Move(Direction.RIGHT, 3));
        moves.add(new Move(Direction.DOWN, 1));
        logger.info("3a: " + mapMover.moveAndFind(0, 0, moves, Structure.TREE));
    }

//    @Scheduled(initialDelay = 0, fixedDelay = Integer.MAX_VALUE)
    public void run2() throws IOException {
        logger.info("Starting 3b...");
        final List<String> lines = FileReader.readLines("/3a");
        final Map map = MapParser.parse(lines);
        final MapMover mapMover = new MapMover(map);

        final List<List<Move>> moves = new ArrayList<>();
        moves.add(Arrays.asList(
                new Move(Direction.RIGHT, 1),
                new Move(Direction.DOWN, 1)
        ));
        moves.add(Arrays.asList(
                new Move(Direction.RIGHT, 3),
                new Move(Direction.DOWN, 1)
        ));
        moves.add(Arrays.asList(
                new Move(Direction.RIGHT, 5),
                new Move(Direction.DOWN, 1)
        ));
        moves.add(Arrays.asList(
                new Move(Direction.RIGHT, 7),
                new Move(Direction.DOWN, 1)
        ));
        moves.add(Arrays.asList(
                new Move(Direction.RIGHT, 1),
                new Move(Direction.DOWN, 2)
        ));

        logger.info("3b: " + move(map, moves));
    }

    private long move(final Map map, final List<List<Move>> moves) {
        long result = 1;
        for (final List<Move> move : moves) {
            result = result * new MapMover(map).moveAndFind(0, 0, move, Structure.TREE);
        }
        return result;
    }
}
