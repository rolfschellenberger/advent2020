package com.rolf.advent2020.day24;

import java.util.*;
import java.util.stream.Collectors;

public class Tile {
    private static int counter = 0;
    private static final Set<Tile> allTiles = new HashSet<>();

    private final int id;
    private boolean white = true;
    private Tile e;
    private Tile se;
    private Tile sw;
    private Tile w;
    private Tile nw;
    private Tile ne;
    private boolean initialized = false;

    public Tile() {
        this.id = counter++;
    }

    public boolean isWhite() {
        return white;
    }

    public boolean isBlack() {
        return !white;
    }

    public Tile setWhite(final boolean white) {
        this.white = white;
        return this;
    }

    public Tile getE() {
        return e;
    }

    public Tile setE(final Tile e) {
        this.e = e;
        return this;
    }

    public Tile getSe() {
        return se;
    }

    public Tile setSe(final Tile se) {
        this.se = se;
        return this;
    }

    public Tile getSw() {
        return sw;
    }

    public Tile setSw(final Tile sw) {
        this.sw = sw;
        return this;
    }

    public Tile getW() {
        return w;
    }

    public Tile setW(final Tile w) {
        this.w = w;
        return this;
    }

    public Tile getNw() {
        return nw;
    }

    public Tile setNw(final Tile nw) {
        this.nw = nw;
        return this;
    }

    public Tile getNe() {
        return ne;
    }

    public Tile setNe(final Tile ne) {
        this.ne = ne;
        return this;
    }

    public Tile get(final Direction direction) {
        switch (direction) {
            case e -> {
                return e;
            }
            case se -> {
                return se;
            }
            case sw -> {
                return sw;
            }
            case w -> {
                return w;
            }
            case nw -> {
                return nw;
            }
            case ne -> {
                return ne;
            }
        }
        throw new RuntimeException("Invalid direction: " + direction);
    }

    public boolean isInitialized() {
        return initialized;
    }

    public Tile setInitialized(final boolean initialized) {
        this.initialized = initialized;
        return this;
    }

    public void initialize(final int width) {
        initialize();
        initializeWidth(width);
        initializeHeight(width);
    }

    public void initializeWidth(final int width) {
        if (width == 0) return;
        if (!e.isInitialized()) {
            e.initialize();
            e.initializeWidth(width - 1);
        }
        if (!w.isInitialized()) {
            w.initialize();
            w.initializeWidth(width - 1);
        }
    }

    public void initializeHeight(final int width) {
        if (width == 0) return;
        if (!ne.isInitialized()) {
            ne.initialize();
            ne.initialize(width - 1);
        }
        if (!se.isInitialized()) {
            se.initialize();
            se.initialize(width - 1);
        }
    }

    // Create remaining tiles around this tile and link them
    public void initialize() {
        if (initialized) return;

        // Create tiles round it
        if (ne == null) {
            ne = new Tile();
            allTiles.add(ne);
        }
        if (e == null) {
            e = new Tile();
            allTiles.add(e);
        }
        if (se == null) {
            se = new Tile();
            allTiles.add(se);
        }
        if (sw == null) {
            sw = new Tile();
            allTiles.add(sw);
        }
        if (w == null) {
            w = new Tile();
            allTiles.add(w);
        }
        if (nw == null) {
            nw = new Tile();
            allTiles.add(nw);
        }

        // Link all surrounding tiles
        ne.setSw(this);
        ne.setSe(e);
        ne.setW(nw);

        e.setW(this);
        e.setNw(ne);
        e.setSw(se);

        se.setNw(this);
        se.setNe(e);
        se.setW(sw);

        sw.setNe(this);
        sw.setE(se);
        sw.setNw(w);

        w.setE(this);
        w.setSe(sw);
        w.setNe(nw);

        nw.setSe(this);
        nw.setSw(w);
        nw.setE(ne);

        initialized = true;
    }

    public void walk(final List<Direction> directions) {
//        System.out.println(this);
        if (directions.isEmpty()) {
            // Flip tile
//            System.out.println(this + ": " + (white ? "to black" : "to white"));
            flip();
            return;
        }
        final Direction step = directions.remove(0);
        final Tile nextTile = get(step);
        // Make sure the next tile knows its neighbours
        nextTile.initialize();
        nextTile.walk(directions);
    }

    public long getBlackCount() {
        return (isBlack() ? 1 : 0) + (allTiles.stream()
                .filter(Tile::isBlack)
                .count());
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
        final List<String> round = new ArrayList<>();
        round.add(ne != null ? ne.id + "" : "-");
        round.add(e != null ? e.id + "" : "-");
        round.add(se != null ? se.id + "" : "-");
        round.add(sw != null ? sw.id + "" : "-");
        round.add(w != null ? w.id + "" : "-");
        round.add(nw != null ? nw.id + "" : "-");
        return "Tile{" +
                "id=" + id +
                ", white=" + white +
                ", round=" + round +
                '}';
    }

    public Set<Tile> findToFlip() {
        final Set<Tile> toFlip = allTiles.stream()
                .filter(Tile::shouldFlipped)
                .collect(Collectors.toSet());
        if (shouldFlipped()) {
            toFlip.add(this);
        }
        return toFlip;
    }

    private boolean shouldFlipped() {
        int blackCount = 0;
        if (ne != null && ne.isBlack()) {
            blackCount++;
        }
        if (e != null && e.isBlack()) {
            blackCount++;
        }
        if (se != null && se.isBlack()) {
            blackCount++;
        }
        if (sw != null && sw.isBlack()) {
            blackCount++;
        }
        if (w != null && w.isBlack()) {
            blackCount++;
        }
        if (nw != null && nw.isBlack()) {
            blackCount++;
        }
        // Any black tile with zero or more than 2 black tiles immediately adjacent to it is flipped to white.
        if (isBlack() && (blackCount == 0 || blackCount > 2)) {
            return true;
        }
        // Any white tile with exactly 2 black tiles immediately adjacent to it is flipped to black.
        if (isWhite() && blackCount == 2) {
            return true;
        }
        return false;
    }

    public void flip() {
        white = !white;
    }
}
