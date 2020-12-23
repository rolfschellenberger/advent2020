package com.rolf.advent2020.day23;

import java.util.Objects;

public class Cup {
    private final int value;
    private Cup previous;
    private Cup next;

    public Cup(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Cup getPrevious() {
        return previous;
    }

    public Cup setPrevious(final Cup previous) {
        this.previous = previous;
        return this;
    }

    public Cup getNext() {
        return next;
    }

    public Cup setNext(final Cup next) {
        this.next = next;
        return this;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Cup cup = (Cup) o;
        return value == cup.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Cup{" +
                "value=" + value +
                '}';
    }
}
