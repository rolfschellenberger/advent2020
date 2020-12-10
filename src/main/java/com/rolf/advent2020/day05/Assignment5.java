package com.rolf.advent2020.day05;

import com.rolf.advent2020.util.Assignment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Component
public class Assignment5 extends Assignment {

    private static final int DAY = 5;

    @Override
    protected int getDay() {
        return DAY;
    }

    @Override
    protected Object getResult1() throws IOException {
        final List<String> lines = readLines();
        return lines.stream()
                .map(SideParser::parse)
                .map(BoardingPass::getSeatId)
                .mapToInt(v -> v)
                .max().orElseThrow(NoSuchElementException::new);
    }

    @Override
    protected Object getResult2() throws IOException {
        final List<String> lines = readLines();
        final List<Integer> seatIds = lines.stream()
                .map(SideParser::parse)
                .map(BoardingPass::getSeatId)
                .sorted()
                .collect(Collectors.toList());

        int previousSeatId = seatIds.get(0);
        for (int seatId : seatIds) {
            if (seatId - previousSeatId > 1) {
                return seatId - 1;
            }
            previousSeatId = seatId;
        }
        return null;
    }
}
