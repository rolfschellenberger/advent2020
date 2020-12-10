package com.rolf.advent2020.day09;

import java.util.Collections;
import java.util.List;

public class Collection {
    private final List<Long> collection;

    public Collection(final List<Long> collection) {
        this.collection = collection;
    }

    public long findFirstNotSum(final int preamble) {
        for (int i = preamble; i < collection.size(); i++) {
            if (!isSum(i, preamble)) {
                return collection.get(i);
            }
        }
        return -1;
    }

    public boolean isSum(final int index, final int preamble) {
        final long sum = collection.get(index);
        final List<Long> subList = collection.subList(index - preamble, index);
        return isSum(subList, sum);
    }

    private boolean isSum(final List<Long> values, final long sum) {
        for (int a = 0; a < values.size(); a++) {
            for (int b = 0; b < values.size(); b++) {
                // Only sum two different numbers
                if (a != b) {
                    if (values.get(a) + values.get(b) == sum) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public List<Long> findContinuousRange(final long sum) {
        for (int i = 0; i < collection.size(); i++) {
            // Now sum the next range
            for (int j = i + 1; j < collection.size(); j++) {
                final List<Long> range = collection.subList(i, j + 1);
                final long rangeSum = sum(range);
                if (rangeSum == sum) {
                    return range;
                }
                // Early termination, probably not needed
                if (rangeSum > sum) {
                    break;
                }
            }
        }
        return Collections.emptyList();
    }

    private long sum(final List<Long> range) {
        return range.stream().reduce(0L, Long::sum);
    }
}
