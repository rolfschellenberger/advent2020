package com.rolf.advent2020.day17;

import java.util.*;
import java.util.stream.Collectors;

public class Space2 {

    private Map<Point2, Point2> points = new HashMap<>();

    public Space2 cycle() {
        final Space2 space = new Space2();

        // Keep track of neighbours to also iterate next step
        final Set<Point2> allNeighbours = new HashSet<>();
        for (final Point2 point : points.values()) {
            final Set<Point2> neighbours = getNeighbours(point);
            final long activeCount = getActiveCount(neighbours);
            allNeighbours.addAll(neighbours);

            final Point2 newPoint = point.clone();
            space.points.put(newPoint, newPoint);
            updateState(activeCount, newPoint);
        }

        // Remove points from neighbours first
        allNeighbours.removeAll(points.values());

        for (final Point2 neighbour : allNeighbours) {
            final Set<Point2> neighbours = getNeighbours(neighbour);
            final long activeCount = getActiveCount(neighbours);
            updateState(activeCount, neighbour);
            space.points.put(neighbour, neighbour);
        }

        return space;
    }

    private Set<Point2> getNeighbours(final Point2 point) {
        final Set<Point2> neighbours = new HashSet<>();
        for (int x = point.getX() - 1; x <= point.getX() + 1; x++) {
            for (int y = point.getY() - 1; y <= point.getY() + 1; y++) {
                for (int z = point.getZ() - 1; z <= point.getZ() + 1; z++) {
                    for (int w = point.getW() - 1; w <= point.getW() + 1; w++) {
                        if (x != point.getX() || y != point.getY() || z != point.getZ() || w != point.getW()) {
                            final Point2 p = new Point2(x, y, z, w, false);
                            final Point2 neighbour = points.getOrDefault(p, p);
                            neighbours.add(neighbour);
                        }
                    }
                }
            }
        }
        return neighbours;
    }

    private long getActiveCount(final Set<Point2> points) {
        return points.stream()
                .filter(Point2::isActive)
                .count();
    }

    private void updateState(final long activeCount, final Point2 point) {
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

    public Set<Point2> getActivePoints() {
        return points.values().stream()
                .filter(Point2::isActive)
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "Space2{" +
                "points=" + getActivePoints() +
                '}';
    }

    public static Space2 parse(final List<String> lines) {
        final Space2 space = new Space2();

        final int z = 0;
        final int w = 0;
        for (int y = 0; y < lines.size(); y++) {
            final String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                final char c = line.charAt(x);
                if (c == '#') {
                    final Point2 point = new Point2(x, y, z, w, true);
                    space.points.put(point, point);
                }
            }
        }

        return space;
    }
}
