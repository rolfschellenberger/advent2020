package com.rolf.advent2020.day24;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Floor {

    private final Map<Point, Tile> allTiles = new HashMap<>();

    public Tile get(final int x, final int y) {
        return allTiles.computeIfAbsent(new Point(x, y), Tile::new);
    }

    public Tile getStartingTile() {
        return get(0, 0);
    }

    public Tile findTile(final List<Direction> directions) {
        Tile current = getStartingTile();
        for (final Direction direction : directions) {
            current = move(current, direction);
        }
        return current;
    }

    private Tile move(final Tile from, final Direction direction) {
        final Point point = from.getPoint();
        return get(point.getX() + direction.getDeltaX(),
                point.getY() + direction.getDeltaY());
    }

    public long getBlackCount() {
        return allTiles.values().stream()
                .filter(Tile::isBlack)
                .count();
    }

    public Set<Tile> findTilesToFlip() {
        // First make sure all black tiles have their neighbours known, so the count can be calculated.
        final Set<Tile> blackTiles = allTiles.values().stream()
                .filter(Tile::isBlack)
                .collect(Collectors.toSet());
        for (final Tile blackTile : blackTiles) {
            for (final Direction direction : Direction.values()) {
                get(blackTile.getPoint().getX() + direction.getDeltaX(),
                        blackTile.getPoint().getY() + direction.getDeltaY());
            }
        }

        return allTiles.values().stream()
                .filter(this::shouldBeFlipped)
                .collect(Collectors.toSet());
    }

    private boolean shouldBeFlipped(final Tile tile) {
        // Count the black neighbours (don't create them).
        long neighboursBlackCount = 0;
        for (final Direction direction : Direction.values()) {
            final Point neighbourPoint = new Point(
                    tile.getPoint().getX() + direction.getDeltaX(),
                    tile.getPoint().getY() + direction.getDeltaY());
            final Tile neighbour = allTiles.get(neighbourPoint);
            neighboursBlackCount += neighbour != null && neighbour.isBlack() ? 1 : 0;
        }

        // Any black tile with zero or more than 2 black tiles immediately adjacent to it is flipped to white.
        if (tile.isBlack() && (neighboursBlackCount == 0 || neighboursBlackCount > 2)) {
            return true;
        }
        // Any white tile with exactly 2 black tiles immediately adjacent to it is flipped to black.
        return tile.isWhite() && neighboursBlackCount == 2;
    }
}
