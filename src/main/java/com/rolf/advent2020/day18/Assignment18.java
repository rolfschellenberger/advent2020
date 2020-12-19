package com.rolf.advent2020.day18;

import com.rolf.advent2020.util.Assignment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Assignment18 extends Assignment {

    private static final int DAY = 18;

    @Override
    protected int getDay() {
        return DAY;
    }

    @Override
    protected Object getResult1() throws IOException {
        final List<String> lines = readLines();
        return lines.stream()
                .map(Sequence::parse)
                .map(Sequence::solve)
                .reduce(0L, Long::sum);
    }

    @Override
    protected Object getResult2() throws IOException {
        final List<String> lines = readLines();
        return lines.stream()
                .map(Sequence2::parse)
                .map(Sequence2::solve)
                .reduce(0L, Long::sum);
    }
}
