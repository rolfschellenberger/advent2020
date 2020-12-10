package com.rolf.advent2020.day02;

public class LetterRequirements2 {

    private final char character;
    private final int position1;
    private final int position2;

    public LetterRequirements2(final char character, final int position1, final int position2) {
        this.character = character;
        this.position1 = position1;
        this.position2 = position2;
    }

    public char getCharacter() {
        return character;
    }

    public int getPosition1() {
        return position1;
    }

    public int getPosition2() {
        return position2;
    }

    @Override
    public String toString() {
        return "LetterRequirements2{" +
                "character=" + character +
                ", position1=" + position1 +
                ", position2=" + position2 +
                '}';
    }

    public boolean meets(final String password) {
        boolean meetPos1 = password.length() >= position1 && password.charAt(position1 - 1) == character;
        boolean meetPos2 = password.length() >= position2 && password.charAt(position2 - 1) == character;
        return meetPos1 ^ meetPos2;
    }
}
