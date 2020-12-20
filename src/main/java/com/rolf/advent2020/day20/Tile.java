package com.rolf.advent2020.day20;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tile {

    private final int id;
    private final boolean[][] grid;
    private boolean[][] gridWithoutBorders;

    private final List<Tile> top = new ArrayList<>();
    private final List<Tile> right = new ArrayList<>();
    private final List<Tile> bottom = new ArrayList<>();
    private final List<Tile> left = new ArrayList<>();

    public Tile(final int id, final boolean[][] grid) {
        this.id = id;
        this.grid = grid;
    }

    public long getId() {
        return id;
    }

    public boolean[] getTopSide() {
        final int y = 0;
        final boolean[] top = new boolean[grid[y].length];
        for (int x = 0; x < grid[0].length; x++) {
            top[x] = grid[y][x];
        }
        return top;
    }

    public boolean[] getRightSide() {
        final int x = grid[0].length - 1;
        final boolean[] right = new boolean[grid.length];
        for (int y = 0; y < grid.length; y++) {
            right[y] = grid[y][x];
        }
        return right;
    }

    public boolean[] getBottomSide() {
        final int y = grid.length - 1;
        final boolean[] bottom = new boolean[grid[y].length];
        for (int x = 0; x < grid[y].length; x++) {
            bottom[x] = grid[y][x];
        }
        return bottom;
    }

    public boolean[] getLeftSide() {
        final int x = 0;
        final boolean[] left = new boolean[grid.length];
        for (int y = 0; y < grid.length; y++) {
            left[y] = grid[y][x];
        }
        return left;
    }

    public List<boolean[]> getSides() {
        return Arrays.asList(
                getTopSide(),
                getRightSide(),
                getBottomSide(),
                getLeftSide()
        );
    }

    public List<Tile> getTop() {
        return top;
    }

    public List<Tile> getRight() {
        return right;
    }

    public List<Tile> getBottom() {
        return bottom;
    }

    public List<Tile> getLeft() {
        return left;
    }

    public List<Tile> getConnectedTiles() {
        return Stream.of(top, right, bottom, left)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public boolean isCorner() {
        return top.size() + right.size() + bottom.size() + left.size() == 2;
    }

    public void lineUp(final Tile other) {
        if (getId() != other.getId()) {
            for (final boolean[] side : other.getSides()) {
                if (equals(getTopSide(), side)) {
                    top.add(other);
                }
                if (equals(getRightSide(), side)) {
                    right.add(other);
                }
                if (equals(getBottomSide(), side)) {
                    bottom.add(other);
                }
                if (equals(getLeftSide(), side)) {
                    left.add(other);
                }
            }
        }
    }

    private boolean equals(final boolean[] a, final boolean[] b) {
        if (Arrays.equals(a, b)) {
            return true;
        }
        return Arrays.equals(reverse(a), b);
    }

    private boolean[] reverse(final boolean[] a) {
        final boolean[] result = new boolean[a.length];
        for (int i = 0; i < a.length; i++) {
            result[a.length - i - 1] = a[i];
        }
        return result;
    }

    public List<Tile> getConnections(final int withMaxConnectedTiles) {
        return getConnectedTiles().stream()
                .filter(t -> t.getConnectedTiles().size() <= withMaxConnectedTiles)
                .collect(Collectors.toList());
    }

    public void removeBorder() {
        gridWithoutBorders = new boolean[grid.length - 2][grid[0].length - 2];
        for (int y = 1; y < grid.length - 1; y++) {
            for (int x = 1; x < grid[y].length - 1; x++) {
                gridWithoutBorders[y - 1][x - 1] = grid[y][x];
            }
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Tile tile = (Tile) o;
        return id == tile.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Tile{" +
                "id=" + id +
                ", top=" + top.size() +
                ", right=" + right.size() +
                ", bottom=" + bottom.size() +
                ", left=" + left.size() +
                '}';
    }

    public static Tile from(final List<String> lines) {
        final String idLine = lines.remove(0);
        final int id = Integer.parseInt(idLine.replace("Tile ", "").replace(":", ""));
        final boolean[][] grid = new boolean[lines.size()][lines.get(0).length()];
        for (int y = 0; y < lines.size(); y++) {
            final String line = lines.get(y);
            for (int x = 0; x < line.length(); x++) {
                grid[y][x] = line.charAt(x) == '#';
            }
        }
        return new Tile(id, grid);
    }

    public void rotate(final Tile top, final Tile right, final Tile bottom, final Tile left) {
        for (int i = 0; i < 4; i++) {
            if (isCorrectPosition(top, right, bottom, left)) {
                return;
            }
            rotate();
        }
        // Flip
        flip();
        for (int i = 0; i < 4; i++) {
            if (isCorrectPosition(top, right, bottom, left)) {
                return;
            }
            rotate();
        }
        throw new RuntimeException("No valid rotation found!?!");
    }

    private boolean isCorrectPosition(final Tile top, final Tile right, final Tile bottom, final Tile left) {
        if (top != null && !getTop().contains(top)) {
            return false;
        }
        if (right != null && !getRight().contains(right)) {
            return false;
        }
        if (bottom != null && !getBottom().contains(bottom)) {
            return false;
        }
        if (left != null && !getLeft().contains(left)) {
            return false;
        }
        return true;
    }

    private void rotate() {
        // Rotate gridWithoutBorders
        gridWithoutBorders = rotate(gridWithoutBorders);

        // Rotate top -> right -> bottom -> left -> top
        final List<Tile> t = new ArrayList<>(top);
        top.clear();
        top.addAll(left);
        left.clear();
        left.addAll(bottom);
        bottom.clear();
        bottom.addAll(right);
        right.clear();
        right.addAll(t);
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

    private void flip() {
        // Flip grid and gridWithoutBorders
        gridWithoutBorders = flip(gridWithoutBorders);

        // Flip right and left
        final List<Tile> l = new ArrayList<>(left);
        left.clear();
        left.addAll(right);
        right.clear();
        right.addAll(l);
    }

    private boolean[][] flip(final boolean[][] in) {
        final int y = in.length;
        final int x = in[0].length;
        final boolean[][] ret = new boolean[y][x];
        for (int i = 0; i < y; ++i) {
            for (int j = 0; j < x; ++j) {
                ret[i][j] = in[i][x - j - 1];
            }
        }
        return ret;
    }

    public int getHeight() {
        return gridWithoutBorders.length;
    }

    public int getWidth() {
        return gridWithoutBorders[0].length;
    }

    public boolean[][] getGridWithoutBorders() {
        return gridWithoutBorders;
    }

    public String toPrintRow(final int y) {
        final StringBuilder sb = new StringBuilder();
        final boolean[] row = gridWithoutBorders[y];
        for (final boolean v : row) {
            sb.append(v ? '#' : '.');
        }
        return sb.toString();
    }
}
