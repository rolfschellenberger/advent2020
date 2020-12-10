package com.rolf.advent2020.day01;

import com.rolf.advent2020.util.Assignment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Assignment1 extends Assignment {

    private static final int DAY = 1;

    @Override
    protected int getDay() {
        return DAY;
    }

    @Override
    protected Object getResult1() throws IOException {
        final List<Long> numbers = readLongs();
        return new Sum().findSum(numbers, 2, 2020);
    }

    @Override
    protected Object getResult2() throws IOException {
        final List<Long> numbers = readLongs();
        return new Sum().findSum(numbers, 3, 2020);
    }
}
