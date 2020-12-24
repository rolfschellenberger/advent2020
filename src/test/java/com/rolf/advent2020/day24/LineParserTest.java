package com.rolf.advent2020.day24;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LineParserTest {

    @Test
    public void test() {
        List<Direction> directions = LineParser.parse("sesenwnenenewseeswwswswwnenewsewsw");
        assertEquals(Arrays.asList(Direction.se, Direction.se, Direction.nw, Direction.ne, Direction.ne, Direction.ne,
                Direction.w, Direction.se, Direction.e, Direction.sw, Direction.w, Direction.sw, Direction.sw,
                Direction.w, Direction.ne, Direction.ne, Direction.w, Direction.se, Direction.w, Direction.sw), directions);

        directions = LineParser.parse("neeenesenwnwwswnenewnwwsewnenwseswesw");
        assertEquals(Arrays.asList(Direction.ne, Direction.e, Direction.e, Direction.ne, Direction.se, Direction.nw,
                Direction.nw, Direction.w, Direction.sw, Direction.ne, Direction.ne, Direction.w, Direction.nw, Direction.w,
                Direction.se, Direction.w, Direction.ne, Direction.nw, Direction.se, Direction.sw, Direction.e, Direction.sw), directions);

        directions = LineParser.parse("seswneswswsenwwnwse");
        assertEquals(Arrays.asList(Direction.se, Direction.sw, Direction.ne, Direction.sw, Direction.sw, Direction.se, Direction.nw, Direction.w, Direction.nw, Direction.se), directions);

        directions = LineParser.parse("nwnwneseeswswnenewneswwnewseswneseene");
        assertEquals(Arrays.asList(Direction.nw, Direction.nw, Direction.ne, Direction.se, Direction.e, Direction.sw, Direction.sw, Direction.ne,
                Direction.ne, Direction.w, Direction.ne, Direction.sw, Direction.w, Direction.ne, Direction.w, Direction.se,
                Direction.sw, Direction.ne, Direction.se, Direction.e, Direction.ne), directions);

        directions = LineParser.parse("wseweeenwnesenwwwswnew");
        assertEquals(Arrays.asList(Direction.w, Direction.se, Direction.w, Direction.e, Direction.e, Direction.e, Direction.nw,
                Direction.ne, Direction.se, Direction.nw, Direction.w, Direction.w, Direction.sw, Direction.ne, Direction.w), directions);
    }

}