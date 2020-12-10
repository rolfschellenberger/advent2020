package com.rolf.advent2020.day01;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Sum {

    public Optional<NumberPair> findSum(final List<Long> numbers, final int count, final long sum) {
        return iterate(numbers, count, sum, new ArrayList<>());
    }

    private Optional<NumberPair> iterate(final List<Long> numbers, final int count, final long sum, final List<Long> list) {
        for (final long number : numbers) {
            final List<Long> t = new ArrayList<>(list);
            t.add(number);
            if (t.size() == count) {
                final NumberPair pair = NumberPair.of(t.toArray(new Long[0]));
                if (pair.getSum() == sum) {
                    return Optional.of(pair);
                }
            } else {
                final Optional<NumberPair> pair = iterate(numbers, count, sum, t);
                if (pair.isPresent()) {
                    return pair;
                }
            }
        }
        return Optional.empty();
    }
}
