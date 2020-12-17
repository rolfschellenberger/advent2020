package com.rolf.advent2020.day17;

import java.util.*;
import java.util.stream.Collectors;

public class Space {

    private Map<Point, Point> points = new HashMap<>();

    public Space cycle() {
        final Space space = new Space();

        // Keep track of neighbours to also iterate next step
        final Set<Point> allNeighbours = new HashSet<>();
        for (final Point point : points.values()) {
            final Set<Point> neighbours = getNeighbours(point);
            final long activeCount = getActiveCount(neighbours);
            allNeighbours.addAll(neighbours);

            final Point newPoint = point.clone();
            space.points.put(newPoint, newPoint);
            updateState(activeCount, newPoint);
        }

        // Remove points from neighbours first
        allNeighbours.removeAll(points.values());

        for (final Point neighbour : allNeighbours) {
            final Set<Point> neighbours = getNeighbours(neighbour);
            final long activeCount = getActiveCount(neighbours);
            updateState(activeCount, neighbour);
            space.points.put(neighbour, neighbour);
        }

        return space;
    }

    private Set<Point> getNeighbours(final Point point) {
        final Set<Point> neighbours = new HashSet<>();
        for (int x = point.getX() - 1; x <= point.getX() + 1; x++) {
            for (int y = point.getY() - 1; y <= point.getY() + 1; y++) {
                for (int z = point.getZ() - 1; z <= point.getZ() + 1; z++) {
                    if (x != point.getX() || y != point.getY() || z != point.getZ()) {
                        final Point p = new Point(x, y, z, false);
                        final Point neighbour = points.getOrDefault(p, p);
                        neighbours.add(neighbour);
                    }
                }
            }
        }
        return neighbours;
    }

    private long getActiveCount(final Set<Point> points) {
        return points.stream()
                .filter(Point::isActive)
                .count();
    }

    private void updateState(final long activeCount, final Point point) {
        if (point.isActive()) {
            if (activeCount != 2 && activeCount != 3) {
                point.setActive(false);
            }
        } else {
            if (activeCount == 3) {
                point.setActive(true);
            }
        }
    }

    public Set<Point> getActivePoints() {
        return points.values().stream()
                .filter(Point::isActive)
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "Space{" +
                "points=" + getActivePoints() +
                '}';
    }

    public static Space parse(final List<String> lines) {
        final Space space = new Space();

        final int z = 0;
        for (int y = 0; y < lines.size(); y++) {
            final String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                final char c = line.charAt(x);
                if (c == '#') {
                    final Point point = new Point(x, y, z, true);
                    space.points.put(point, point);
                }
            }
        }

        return space;
    }
}
