package com.rolf.advent2020.day11;

import java.util.ArrayList;
import java.util.List;

public class Floor {

    private final Location[][] floor;

    public Floor(final Location[][] floor) {
        this.floor = floor;
    }

    public int step() {
        final List<Location> occupy = new ArrayList<>();
        final List<Location> empty = new ArrayList<>();
        // Inspect all locations.
        for (int x = 0; x < floor.length; x++) {
            for (int y = 0; y < floor[x].length; y++) {
                final Location location = floor[x][y];
                if (location.getType() == Type.CHAIR_EMPTY) {
                    final int neighboorChairs = getNeighboorChairsOccupied(x, y);
                    if (neighboorChairs == 0) {
                        occupy.add(location);
                    }
                }
                if (location.getType() == Type.CHAIR_OCCUPIED) {
                    final int neighboorChairs = getNeighboorChairsOccupied(x, y);
                    if (neighboorChairs >= 4) {
                        empty.add(location);
                    }
                }
            }
        }

        for (final Location location : occupy) {
            location.setType(Type.CHAIR_OCCUPIED);
        }
        for (final Location location : empty) {
            location.setType(Type.CHAIR_EMPTY);
        }
        return occupy.size() + empty.size();
    }

    private int getNeighboorChairsOccupied(final int x, final int y) {
        int count = 0;
        count += isChairOccupied(x + 1, y);
        count += isChairOccupied(x - 1, y);
        count += isChairOccupied(x, y + 1);
        count += isChairOccupied(x, y - 1);
        count += isChairOccupied(x + 1, y + 1);
        count += isChairOccupied(x + 1, y - 1);
        count += isChairOccupied(x - 1, y + 1);
        count += isChairOccupied(x - 1, y - 1);
        return count;
    }

    private int isChairOccupied(final int x, final int y) {
        if (x < 0 || y < 0 || x >= floor.length || y >= floor[x].length) {
            return 0;
        }
        return floor[x][y].getType() == Type.CHAIR_OCCUPIED ? 1 : 0;
    }

    public int getOccupiedChairCount() {
        int count = 0;
        for (final Location[] locations : floor) {
            for (final Location location : locations) {
                if (location.getType() == Type.CHAIR_OCCUPIED) {
                    count++;
                }
            }
        }
        return count;
    }

    public int step2() {
        final List<Location> occupy = new ArrayList<>();
        final List<Location> empty = new ArrayList<>();
        // Inspect all locations.
        for (final Location[] locations : floor) {
            for (final Location location : locations) {
                if (location.getType() == Type.CHAIR_EMPTY) {
                    final int neighboorChairs = getNeighboorChairsOccupied(location);
                    if (neighboorChairs == 0) {
                        occupy.add(location);
                    }
                }
                if (location.getType() == Type.CHAIR_OCCUPIED) {
                    final int neighboorChairs = getNeighboorChairsOccupied(location);
                    if (neighboorChairs >= 5) {
                        empty.add(location);
                    }
                }
            }
        }

        for (final Location location : occupy) {
            location.setType(Type.CHAIR_OCCUPIED);
        }
        for (final Location location : empty) {
            location.setType(Type.CHAIR_EMPTY);
        }
        return occupy.size() + empty.size();
    }

    private int getNeighboorChairsOccupied(final Location location) {
        int count = 0;
        for (final Direction direction : Direction.values()) {
            count += isChairOccupied(next(location, direction), direction);
        }
        return count;
    }

    private int isChairOccupied(final Location location, final Direction direction) {
        if (location == null) {
            return 0;
        }
        if (location.getType() == Type.FLOOR) {
            return isChairOccupied(next(location, direction), direction);
        }
        return location.getType() == Type.CHAIR_OCCUPIED ? 1 : 0;
    }

    private Location next(final Location location, final Direction direction) {
        int x = location.getX() + direction.getxDelta();
        int y = location.getY() + direction.getyDelta();
        if (x < 0 || y < 0 || x >= floor.length || y >= floor[x].length) {
            return null;
        }
        return floor[x][y];
    }
}
