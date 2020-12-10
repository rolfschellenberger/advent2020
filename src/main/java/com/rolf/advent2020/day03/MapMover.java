package com.rolf.advent2020.day03;

import java.util.HashMap;
import java.util.List;

public class MapMover {

    private final Map map;

    public MapMover(final Map map) {
        this.map = map;
    }

    public int moveAndFind(final int startX, final int startY, final List<Move> moves, final Structure structureToFind) {
        final java.util.Map<Structure, Integer> structureCounts = new HashMap<>();
        Location position = map.get(startX, startY);
        while (position != null) {
            position = position.move(moves);
            if (position != null) {
                structureCounts.merge(position.getStructure(), 1, Integer::sum);
            }
        }
        return structureCounts.getOrDefault(structureToFind, 0);
    }
}
