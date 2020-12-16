package com.rolf.advent2020.day16;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Range {
    private final String name;
    private final Set<Integer> allowedValues;

    public Range(final String name, final Set<Integer> allowedValues) {
        this.name = name;
        this.allowedValues = allowedValues;
    }

    public static Range fromValue(final String input) {
        final String name = input.substring(0, input.indexOf(":"));
        final Set<Integer> allowedValues = new HashSet<>();
        final String rangeString = input.substring(name.length() + 2);
        final String[] ranges = rangeString.split(" or ");
        for (final String range : ranges) {
            final int start = Integer.parseInt(range.substring(0, range.indexOf("-")));
            final int end = Integer.parseInt(range.substring(range.indexOf("-") + 1));
            final Set<Integer> values = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toSet());
            allowedValues.addAll(values);
        }
        return new Range(name, allowedValues);
    }

    public String getName() {
        return name;
    }

    public Set<Integer> getAllowedValues() {
        return allowedValues;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Range range = (Range) o;
        return Objects.equals(name, range.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Range{" +
                "name='" + name + '\'' +
                ", allowedValues=" + allowedValues +
                '}';
    }
}
