package com.rolf.advent2020.day13;

import com.rolf.advent2020.util.Assignment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Assignment13 extends Assignment {

    private static final int DAY = 13;

    @Override
    protected int getDay() {
        return DAY;
    }

    @Override
    protected Object getResult1() throws IOException {
        final List<String> lines = readLines();
        final long time = Long.parseLong(lines.get(0));
        final List<Long> busIds = Arrays.stream(lines.get(1).split(","))
                .filter(s -> !"x".equals(s))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        long minTime = -1;
        long bestBusId = 0;
        for (final long busId : busIds) {
            final long waitTime = busId - (time % busId);
            if (minTime == -1 || waitTime < minTime) {
                minTime = waitTime;
                bestBusId = busId;
            }
        }
        return minTime * bestBusId;
    }

    @Override
    protected Object getResult2() throws IOException {
        final List<String> lines = readLines();
        final String[] busLines = lines.get(1).split(",");
        final List<Bus> busses = new ArrayList<>();
        for (int i = 0; i < busLines.length; i++) {
            if (!"x".equals(busLines[i])) {
                busses.add(new Bus(Long.parseLong(busLines[i]), i));
            }
        }

        // Start with the first bus
        final int firstBusIndex = 0;
        final Bus one = busses.remove(firstBusIndex);
        long startIndex = one.getBusId();
        long incrementStep = findIncrementStep(Collections.singletonList(one));

        // Iterate all busses to align all.
        final List<Bus> alignedBusses = new ArrayList<>();
        alignedBusses.add(one);
        while (!busses.isEmpty()) {
            final Bus nextBus = busses.remove(firstBusIndex);
            alignedBusses.add(nextBus);
            startIndex = findLineUp(startIndex, incrementStep, nextBus);
            incrementStep = findIncrementStep(alignedBusses);
        }

        return startIndex;
    }

    private long findLineUp(final long startIndex, final long incrementStep, final Bus nextBus) {
        for (long i = startIndex; i < Long.MAX_VALUE; i += incrementStep) {
            long nextTime = i + nextBus.getTimeOffset();
            if (nextTime % nextBus.getBusId() == 0) {
                // We are aligned
                return i;
            }
        }
        return 0;
    }

    private long findIncrementStep(final List<Bus> busses) {
        long factor = 1;
        for (final Bus bus : busses) {
            factor *= bus.getBusId();
        }
        return factor;
    }
}
