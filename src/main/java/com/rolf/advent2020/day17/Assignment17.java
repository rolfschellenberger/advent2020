package com.rolf.advent2020.day17;

import com.rolf.advent2020.util.Assignment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Assignment17 extends Assignment {

    private static final int DAY = 17;

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
        Space space = Space.parse(lines);
        for (int i = 0; i < 6; i++) {
            space = space.cycle();
        }
        return space.getActivePoints().size();
    }

    @Override
    protected Object getResult2() throws IOException {
        final List<String> lines = readLines();
        Space2 space = Space2.parse(lines);
        for (int i = 0; i < 6; i++) {
            space = space.cycle();
        }
        return space.getActivePoints().size();
    }
}
