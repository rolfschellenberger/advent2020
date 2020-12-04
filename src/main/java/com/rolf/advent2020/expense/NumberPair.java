package com.rolf.advent2020.expense;

import java.util.Arrays;
import java.util.List;

public class NumberPair {

    private final List<Long> numbers;
    private final long sum;
    private final long multiply;

    private NumberPair(final Long... numbers) {
        this.numbers = Arrays.asList(numbers);
        this.sum = this.numbers.stream().reduce(0L, Long::sum);
        this.multiply = this.numbers.stream().reduce(1L, (a, b) -> a * b);
    }

    public List<Long> getNumbers() {
        return numbers;
    }

    public long getSum() {
        return sum;
    }

    public long getMultiply() {
        return multiply;
    }

    @Override
    public String toString() {
        return "NumberPair{" +
                "numbers=" + numbers +
                ", sum=" + sum +
                ", multiply=" + multiply +
                '}';
    }

    public static NumberPair of(final Long... numbers) {
        return new NumberPair(numbers);
    }
}
