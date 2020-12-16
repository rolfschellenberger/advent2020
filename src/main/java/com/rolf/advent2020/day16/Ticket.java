package com.rolf.advent2020.day16;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Ticket {
    private final Set<Integer> numbers;
    private final List<Integer> numberList;

    private Ticket(final List<Integer> numbers) {
        this.numbers = new HashSet<>(numbers);
        this.numberList = numbers;
    }

    public static Ticket fromValue(final String input) {
        final String[] n = input.split(",");
        return new Ticket(Arrays.stream(n)
                .map(Integer::parseInt)
                .collect(Collectors.toList()));
    }

    public Set<Integer> getNumbers() {
        return numbers;
    }

    public List<Integer> getNumberList() {
        return numberList;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "numbers=" + numberList +
                '}';
    }
}
