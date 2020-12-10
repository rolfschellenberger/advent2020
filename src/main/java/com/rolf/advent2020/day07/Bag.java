package com.rolf.advent2020.day07;

import java.util.Objects;

public class Bag {
    private final String color;
    private final int amount;

    public Bag(final String color) {
        this(color, 1);
    }

    public Bag(final String color, final int amount) {
        this.color = color;
        this.amount = amount;
    }

    public String getColor() {
        return color;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Bag bag = (Bag) o;
        return Objects.equals(color, bag.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    @Override
    public String toString() {
        return "Bag{" +
                "color='" + color + '\'' +
                ", amount=" + amount +
                '}';
    }
}
