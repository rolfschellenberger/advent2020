package com.rolf.advent2020.day22;

import java.util.List;
import java.util.stream.Collectors;

public class Hand {
    private final List<Integer> cards;

    public Hand(final List<Integer> cards) {
        this.cards = cards;
    }

    public List<Integer> getCards() {
        return cards;
    }

    public int getTop() {
        return cards.remove(0);
    }

    public void add(final int one, final int two) {
        cards.add(one);
        cards.add(two);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public static Hand from(final List<String> lines) {
        return new Hand(lines.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + cards +
                '}';
    }
}
