package com.rolf.advent2020.day14;

import com.rolf.advent2020.util.Assignment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

@Component
public class Assignment14 extends Assignment {

    private static final int DAY = 14;

    @Override
    protected int getDay() {
        return DAY;
    }

    @Override
    protected Object getResult1() throws IOException {
        final List<String> lines = readLines();

        final BigInteger[] memory = new BigInteger[1000000];
        Mask mask = new Mask();
        for (final String line : lines) {
            switch (getType(line)) {
                case 1 -> {
                    mask = Mask.fromValue(line);
                }
                case 0 -> {
                    final BigInteger value = mask.apply(getValue(line));
                    final int index = getMemoryAddress(line);
                    memory[index] = value;
                }
            }
        }

        return Arrays.stream(memory)
                .filter(Objects::nonNull)
                .reduce(BigInteger.ZERO, BigInteger::add);
    }

    private int getType(final String input) {
        return input.contains("mask") ? 1 : 0;
    }

    private long getValue(final String input) {
        return Long.parseLong(input.substring(input.indexOf('=') + 2));
    }

    private int getMemoryAddress(final String input) {
        return Integer.parseInt(input.substring(input.indexOf('[') + 1, input.indexOf(']')));
    }

    @Override
    protected Object getResult2() throws IOException {
        final List<String> lines = readLines();

        final Map<BigInteger, BigInteger> memory = new HashMap<>();
        Mask2 mask = new Mask2();
        for (final String line : lines) {
            switch (getType(line)) {
                case 1 -> {
                    mask = Mask2.fromValue(line);
                }
                case 0 -> {
                    final int index = getMemoryAddress(line);
                    final List<BigInteger> memoryAddresses = mask.apply(index);
                    for (final BigInteger memoryAddress : memoryAddresses) {
                        memory.put(memoryAddress, BigInteger.valueOf(getValue(line)));
                    }
                }
            }
        }

        return memory.values().stream()
                .reduce(BigInteger.ZERO, BigInteger::add);
    }
}
