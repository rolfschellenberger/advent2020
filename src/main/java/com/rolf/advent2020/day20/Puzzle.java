package com.rolf.advent2020.day20;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Puzzle {
    private final List<Tile> tiles;
    private List<List<Tile>> grid;

    public Puzzle(final List<Tile> tiles) {
        this.tiles = tiles;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public List<Tile> getCorners() {
        return tiles.stream()
                .filter(Tile::isCorner)
                .collect(Collectors.toList());
    }

    public List<List<Tile>> getEdges() {
        final List<List<Tile>> edges = new ArrayList<>();
        final List<Tile> corners = getCorners();

        // Remember what tiles were traveled.
        final Set<Tile> traveledTiles = new HashSet<>(corners);

        // Travel from corner to corner
        for (final Tile corner : corners) {
            final List<Tile> edge = new ArrayList<>();
            edge.add(corner);
            Set<Tile> directions = new HashSet<>(corner.getConnections(3));
            directions.removeAll(traveledTiles);
            Tile step = null;
            while (!directions.isEmpty()) {
                // Pick just the next (first).
                step = directions.iterator().next();
                traveledTiles.add(step);
                edge.add(step);
                directions = new HashSet<>(step.getConnections(3));
                directions.removeAll(traveledTiles);
            }
            // Add last corner
            edge.add(step.getConnections(2).iterator().next());
            edges.add(edge);
        }

        return edges;
    }

    public void setGrid() {
        final List<List<Tile>> edges = getEdges();
        // Pick a random edge to start with and let's assume this is the top edge.
        final List<Tile> startEdge = edges.get(0);
        final List<List<Tile>> grid = new ArrayList<>();

        // Now travel from each tile to the next (and only available) tile.
        final Set<Tile> traveledTiles = new HashSet<>(startEdge);
        List<Tile> lastLine = new ArrayList<>(startEdge);
        while (!lastLine.isEmpty()) {
            grid.add(lastLine);

            final List<Tile> nextLine = new ArrayList<>();
            for (final Tile tile : lastLine) {
                // Get the next tiles
                final Set<Tile> nextTiles = new HashSet<>(tile.getConnectedTiles());
                // Remove all traveled, so only 1 remains
                nextTiles.removeAll(traveledTiles);
                traveledTiles.addAll(nextTiles);

                if (!nextTiles.isEmpty()) {
                    nextLine.add(nextTiles.iterator().next());
                }
            }
            // Next line
            lastLine = nextLine;
        }

        // Remove borders after alignment of the grid
        removeTileBorders();

        // Now we have a grid, but some tiles need to be rotated.
        for (int y = 0; y < grid.size(); y++) {
            final List<Tile> row = grid.get(y);
            for (int x = 0; x < row.size(); x++) {
                final Tile tile = row.get(x);
                final Tile top = getTile(grid, x, y - 1);
                final Tile right = getTile(grid, x + 1, y);
                final Tile bottom = getTile(grid, x, y + 1);
                final Tile left = getTile(grid, x - 1, y);
                tile.rotate(top, right, bottom, left);
            }
        }

        this.grid = grid;
    }

    private Tile getTile(final List<List<Tile>> grid, final int x, final int y) {
        if (x >= 0 && y >= 0 && y < grid.size() && x < grid.get(0).size()) {
            return grid.get(y).get(x);
        }
        return null;
    }

    public void removeTileBorders() {
        for (final Tile tile : tiles) {
            tile.removeBorder();
        }
    }

    public boolean[][] toGrid() {
        final Tile sample = grid.get(0).get(0);
        final int height = grid.size() * sample.getHeight();
        final int width = grid.get(0).size() * sample.getWidth();

        final boolean[][] result = new boolean[height][width];
        for (int r = 0; r < grid.size(); r++) {
            final List<Tile> row = grid.get(r);
            for (int c = 0; c < row.size(); c++) {
                final Tile tile = row.get(c);
                for (int a = 0; a < tile.getHeight(); a++) {
                    for (int b = 0; b < tile.getWidth(); b++) {
                        final int y = r * tile.getHeight() + a;
                        final int x = c * tile.getWidth() + b;
                        result[y][x] = tile.getGridWithoutBorders()[a][b];
                    }
                }
            }
        }
        return result;
    }

    public int findSeaMonsters() {
        boolean[][] grid = toGrid();

        int maxMonsterCount = 0;
        for (int i = 0; i < 4; i++) {
            int monsterCount = getMonsterCount(grid);
            if (monsterCount > maxMonsterCount) {
                maxMonsterCount = monsterCount;
            }
            // Try in different rotations
            grid = rotate(grid);
        }
        return maxMonsterCount;
    }

    private int getMonsterCount(final boolean[][] grid) {
        int monsterCount = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (isMonster(grid, x, y)) {
                    monsterCount++;
                }
            }
        }
        return monsterCount;
    }

    private boolean isMonster(final boolean[][] grid, final int x, final int y) {
        // Out of bounds
        if (x + 19 > grid[0].length - 1) {
            return false;
        }
        if (y - 1 < 0) {
            return false;
        }
        if (y + 1 > grid.length - 1) {
            return false;
        }

        // Start position
        if (!grid[y][x]) {
            return false;
        }
        // x+1, y+1
        if (!grid[y + 1][x + 1]) {
            return false;
        }
        // x+4, y+1
        if (!grid[y + 1][x + 4]) {
            return false;
        }
        // x+5, y
        if (!grid[y][x + 5]) {
            return false;
        }
        // x+6, y
        if (!grid[y][x + 6]) {
            return false;
        }
        // x+7, y+1
        if (!grid[y + 1][x + 7]) {
            return false;
        }
        // x+10, y+1
        if (!grid[y + 1][x + 10]) {
            return false;
        }
        // x+11, y
        if (!grid[y][x + 11]) {
            return false;
        }
        // x+12, y
        if (!grid[y][x + 12]) {
            return false;
        }
        // x+13, y+1
        if (!grid[y + 1][x + 13]) {
            return false;
        }
        // x+16, y+1
        if (!grid[y + 1][x + 16]) {
            return false;
        }
        // x+17, y
        if (!grid[y][x + 17]) {
            return false;
        }
        // x+18, y
        if (!grid[y][x + 18]) {
            return false;
        }
        // x+18, y-1
        if (!grid[y - 1][x + 18]) {
            return false;
        }
        // x+19, y
        if (!grid[y][x + 19]) {
            return false;
        }

        return true;
    }

    public int findSea() {
        final boolean[][] grid = toGrid();

        int seaCount = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x]) {
                    seaCount++;
                }
            }
        }
        return seaCount;
    }

    private boolean[][] rotate(final boolean[][] in) {
        final int w = in.length;
        final int h = in[0].length;
        final boolean[][] ret = new boolean[h][w];
        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                ret[i][j] = in[w - j - 1][i];
            }
        }
        return ret;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (boolean[] row : toGrid()) {
            for (boolean v : row) {
                sb.append(v ? '#' : '.');
            }
            sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }
}
