package com.rolf.advent2020.day11;

import com.rolf.advent2020.util.Assignment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Assignment11 extends Assignment {

    private static final int DAY = 11;

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
        final Floor floor = FloorParser.parse(lines);
        int moves = 1;
        while (moves > 0) {
            moves = floor.step();
        }
        return floor.getOccupiedChairCount();
    }

    @Override
    protected Object getResult2() throws IOException {
        final List<String> lines = readLines();
        final Floor floor = FloorParser.parse(lines);
        int moves = 1;
        while (moves > 0) {
            moves = floor.step2();
        }
        return floor.getOccupiedChairCount();
    }
}
