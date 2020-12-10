package com.rolf.advent2020.day02;

public class LetterRequirements {

    private final char character;
    private final int minCount;
    private final int maxCount;

    public LetterRequirements(final char character, final int minCount, final int maxCount) {
        this.character = character;
        this.minCount = minCount;
        this.maxCount = maxCount;
    }

    public char getCharacter() {
        return character;
    }

    public int getMinCount() {
        return minCount;
    }

    public int getMaxCount() {
        return maxCount;
    }

    @Override
    public String toString() {
        return "LetterRequirements{" +
                "character=" + character +
                ", minCount=" + minCount +
                ", maxCount=" + maxCount +
                '}';
    }

    public boolean meets(final String password) {
        final long charCount = password.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> c == character)
                .count();
        return charCount >= minCount && charCount <= maxCount;
    }
}
