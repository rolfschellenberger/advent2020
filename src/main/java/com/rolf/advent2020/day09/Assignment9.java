package com.rolf.advent2020.day09;

import com.rolf.advent2020.util.Assignment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Assignment9 extends Assignment {

    private static final int DAY = 9;

    @Override
    protected int getDay() {
        return DAY;
    }

    @Override
    protected Object getResult1() throws IOException {
        final List<Long> lines = readLongs();
        final Collection collection = new Collection(lines);
        return collection.findFirstNotSum(25);
    }

    @Override
    protected Object getResult2() throws IOException {
        final List<Long> lines = readLongs();
        final Collection collection = new Collection(lines);
        final List<Long> range = collection.findContinuousRange(15353384L);
        final long min = range.stream().min(Long::compare).get();
        final long max = range.stream().max(Long::compare).get();
        return min + max;
    }
}
