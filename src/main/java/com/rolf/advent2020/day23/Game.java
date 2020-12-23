package com.rolf.advent2020.day23;

import java.util.*;

public class Game {
    private final Map<Integer, Cup> cups;
    private final int highestValue;
    private Cup currentCup;

    public Game(final List<Integer> cups) {
        this(cups, cups.size());
    }

    public Game(final List<Integer> cups, final int count) {
        this.cups = new HashMap<>(count);
        this.highestValue = count;

        int firstValue = -1;
        int lastValue = -1;
        Cup previousCup = null;
        for (final int value : cups) {
            final Cup cup = new Cup(value);
            cup.setPrevious(previousCup);
            if (previousCup != null) {
                previousCup.setNext(cup);
            }
            previousCup = cup;
            this.cups.put(value, cup);

            if (firstValue == -1) {
                firstValue = value;
            }
            lastValue = value;
        }
        for (int i = 0; i < count - cups.size(); i++) {
            final int value = cups.size() + i + 1;
            final Cup cup = new Cup(value);
            cup.setPrevious(previousCup);
            if (previousCup != null) {
                previousCup.setNext(cup);
            }
            previousCup = cup;
            this.cups.put(value, cup);

            if (firstValue == -1) {
                firstValue = value;
            }
            lastValue = value;
        }

        // Connect the last with the first
        final Cup first = this.cups.get(firstValue);
        final Cup last = this.cups.get(lastValue);
        first.setPrevious(last);
        last.setNext(first);

        // Start from first cup
        this.currentCup = first;
    }

    public void move() {
        // Get the 3 cups
        final Cup one = currentCup.getNext();
        final Cup two = one.getNext();
        final Cup three = two.getNext();
        final Cup four = three.getNext();

        // Find destination cup
        final int destinationValue = getDestinationValue(currentCup.getValue(), highestValue, one, two, three);
        final Cup destinationCup = cups.get(destinationValue);

        // Now we link the current to the cup after three
        link(currentCup, four);

        // And we link the destination to the 'one' and the 'three' to the old next of the destination.
        final Cup destinationCupNext = destinationCup.getNext();
        link(destinationCup, one);
        link(three, destinationCupNext);

        // Go to the next cup
        this.currentCup = currentCup.getNext();
    }

    private int getDestinationValue(final int currentValue, final int highestValue, final Cup one, final Cup two, final Cup three) {
        final Set<Integer> ignoreValues = new HashSet<>(3);
        ignoreValues.add(one.getValue());
        ignoreValues.add(two.getValue());
        ignoreValues.add(three.getValue());
        int destinationValue = currentValue - 1;

        while (ignoreValues.contains(destinationValue)) {
            destinationValue--;
        }
        if (destinationValue <= 0) {
            destinationValue = getDestinationValue(highestValue + 1, highestValue, one, two, three);
        }
        return destinationValue;
    }

    private void link(final Cup from, final Cup to) {
        from.setNext(to);
        to.setPrevious(from);
    }

    public String getResult1() {
        final Cup oneCup = this.cups.get(1);
        Cup next = oneCup.getNext();

        final StringBuilder sb = new StringBuilder();
        while (next != oneCup) {
            sb.append(next.getValue());
            next = next.getNext();
        }
        return sb.toString();
    }

    public long getResult2() {

        final Cup one = this.cups.get(1);
        final Cup a = one.getNext();
        final Cup b = a.getNext();
        return (long) a.getValue() * (long) b.getValue();
    }

    public String list() {
        Cup next = currentCup.getNext();

        final StringBuilder sb = new StringBuilder();
        sb.append(currentCup.getValue());
        while (next != currentCup) {
            sb.append(next.getValue());
            next = next.getNext();
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Game{" +
                "currentCup=" + currentCup +
                ", highestValue=" + highestValue +
                ", cups=" + cups.size() +
                '}';
    }
}
