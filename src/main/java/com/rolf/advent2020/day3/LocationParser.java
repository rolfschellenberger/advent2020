package com.rolf.advent2020.day3;

public class LocationParser {

    private LocationParser() {
    }

    public static Location parse(final String input) {
        return new Location(Structure.fromValue(input));
    }
}
