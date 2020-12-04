package com.rolf.advent2020.map;

import com.rolf.advent2020.map.Location;
import com.rolf.advent2020.map.Structure;

public class LocationParser {

    private LocationParser() {
    }

    public static Location parse(final String input) {
        return new Location(Structure.fromValue(input));
    }
}
