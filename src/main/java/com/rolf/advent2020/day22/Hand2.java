package com.rolf.advent2020.day22;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Hand2 {
    private final int playerId;
    private final List<Integer> cards;

    public Hand2(final int playerId, final List<Integer> cards) {
        this.playerId = playerId;
        this.cards = cards;
    }

    public int getPlayerId() {
        return playerId;
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

    public static Hand2 from(final int playerId, final List<String> lines) {
        return new Hand2(playerId, lines.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList()));
    }

    public Hand2 copy() {
        return new Hand2(playerId, new ArrayList<>(cards));
    }

    public Hand2 copy(final int count) {
        return new Hand2(playerId, new ArrayList<>(cards.subList(0, count)));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Hand2 hand2 = (Hand2) o;
        return Objects.equals(cards, hand2.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards);
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + cards +
                '}';
    }
}
