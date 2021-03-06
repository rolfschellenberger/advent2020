package com.rolf.advent2020.day10;

import com.rolf.advent2020.util.Assignment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Assignment10 extends Assignment {

    private static final int DAY = 10;

    @Override
    protected int getDay() {
        return DAY;
    }

    @Override
    protected Object getResult1() throws IOException {
        final List<Long> lines = readLongs();
        return Sockets.multiplyOneThreeCount(lines);
    }

    @Override
    protected Object getResult2() throws IOException {
        final List<Long> lines = readLongs();
        return NodeParser.parseNodes(lines);
    }
}
