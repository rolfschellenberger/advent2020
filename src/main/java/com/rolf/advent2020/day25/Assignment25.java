package com.rolf.advent2020.day25;

import com.rolf.advent2020.util.Assignment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Assignment25 extends Assignment {

    private static final int DAY = 25;
    private static final long MOD = 20201227;

    @Override
    protected int getDay() {
        return DAY;
    }

    @Override
    protected Object getResult1() throws IOException {
        final List<String> lines = readLines();
        final long loopSize = findLoopSize(Long.parseLong(lines.get(0)));
        return calculateEncryptionKey(loopSize, Long.parseLong(lines.get(1)));
    }

    private long findLoopSize(final long key) {
        long loop = 0;
        long value = 1;
        while (value != key) {
            value *= 7;
            value %= MOD;
            loop++;
        }
        return loop;
    }

    private long calculateEncryptionKey(final long loopSize, final long otherKey) {
        long value = 1;
        for (long i = 0; i < loopSize; i++) {
            value *= otherKey;
            value %= MOD;
        }
        return value;
    }

    @Override
    protected Object getResult2() {
        return "There was no part 2";
    }
}
