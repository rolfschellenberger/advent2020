package com.rolf.advent2020.day03;

import com.rolf.advent2020.util.Assignment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Assignment3 extends Assignment {

    private static final int DAY = 3;

    @Override
    protected int getDay() {
        return DAY;
    }

    @Override
    protected Object getResult1() throws IOException {
        final List<String> lines = readLines();
        final Map map = MapParser.parse(lines);
        final MapMover mapMover = new MapMover(map);
        final List<Move> moves = new ArrayList<>();
        moves.add(new Move(Direction.RIGHT, 3));
        moves.add(new Move(Direction.DOWN, 1));
        return mapMover.moveAndFind(0, 0, moves, Structure.TREE);
    }

    @Override
    protected Object getResult2() throws IOException {
        final List<String> lines = readLines();
        final Map map = MapParser.parse(lines);

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

        return move(map, moves);
    }

    private long move(final Map map, final List<List<Move>> moves) {
        long result = 1;
        for (final List<Move> move : moves) {
            result = result * new MapMover(map).moveAndFind(0, 0, move, Structure.TREE);
        }
        return result;
    }
}
