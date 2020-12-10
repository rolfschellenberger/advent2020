package com.rolf.advent2020.day05;

import java.util.List;

public class Plane {

    private final List<BoardingPass> boardingPasses;

    public Plane(final List<BoardingPass> boardingPasses) {
        this.boardingPasses = boardingPasses;
    }

    @Override
    public String toString() {
        final int[][] layout = new int[128][8];
        for (final BoardingPass boardingPass : boardingPasses) {
            layout[boardingPass.getRow()][boardingPass.getColumn()] = 1;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append('\n');
        for (int c = 0; c < layout.length; c++) {
            sb.append(c).append(". ");
            for (int r = 0; r < layout[c].length; r++) {
                sb.append(layout[c][r]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
