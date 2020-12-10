package com.rolf.advent2020.day08;

import com.rolf.advent2020.util.Assignment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Assignment8 extends Assignment {

    private static final int DAY = 8;

    @Override
    protected int getDay() {
        return DAY;
    }

    @Override
    protected Object getResult1() throws IOException {
        final List<String> lines = readLines();
        final Program program = Program.from(lines);
        return program.execute();
    }

    @Override
    protected Object getResult2() throws IOException {
        final List<String> lines = readLines();
        final Program program = Program.from(lines);
        return program.executeTree();
    }
}
