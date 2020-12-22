package com.rolf.advent2020.day22;

public class Game {
    private final Hand player1;
    private final Hand player2;

    public Game(final Hand player1, final Hand player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public boolean move() {
        final int p1 = player1.getTop();
        final int p2 = player2.getTop();

        if (p1 > p2) {
            player1.add(p1, p2);
        } else {
            player2.add(p2, p1);
        }

        return !hasWinner();
    }

    public boolean hasWinner() {
        return player1.isEmpty() || player2.isEmpty();
    }

    public Hand getWinner() {
        if (!hasWinner()) {
            return null;
        }
        return player1.isEmpty() ? player2 : player1;
    }

    public long getWinnerScore() {
        final Hand winner = getWinner();
        if (winner == null) {
            return -1;
        }

        long score = 0;
        for (int i = 0; i < winner.getCards().size(); i++) {
            final int value = winner.getCards().get(i);
            final int rank = winner.getCards().size() - i;
            score += value * rank;
        }
        return score;
    }
}
