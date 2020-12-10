package com.rolf.advent2020.day10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sockets {

    public static long multiplyOneThreeCount(final List<Long> input) {
        long ones = 0;
        long threes = 0;

        final List<Long> sortedInput = new ArrayList<>(input);
        Collections.sort(sortedInput);
        long prev = 0;
        for (final long value : sortedInput) {
            final long diff = value - prev;
            if (diff == 1) ones++;
            if (diff == 3) threes++;
            prev = value;
        }
        // Add one 3 for your device
        threes++;
        return ones * threes;
    }
}
