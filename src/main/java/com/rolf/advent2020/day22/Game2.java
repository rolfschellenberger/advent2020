package com.rolf.advent2020.day22;

import java.util.HashSet;
import java.util.Set;

public class Game2 {
    private final Hand2 player1;
    private final Hand2 player2;

    private final Set<Round2> previousRounds = new HashSet<>();

    public Game2(final Hand2 player1, final Hand2 player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Hand2 play() {

        // Play until there is a winner.
        while (!hasWinner()) {

            // If we had this round before, player1 wins this game.
            final Round2 round = new Round2(player1.copy(), player2.copy());
            if (!previousRounds.add(round)) {
                return player1;
            }

            // Draw cards
            final int p1 = player1.getTop();
            final int p2 = player2.getTop();

            // If the value is equal to the remaining cards:
            if (player1.getCards().size() >= p1 &&
                    player2.getCards().size() >= p2) {
                // Determine winner recursively
                final Game2 recursiveGame = new Game2(player1.copy(p1), player2.copy(p2));
                final Hand2 winner = recursiveGame.play();
                if (winner.getPlayerId() == player1.getPlayerId()) {
                    player1.add(p1, p2);
                } else {
                    player2.add(p2, p1);
                }
            }

            // Otherwise win with higher value.
            else {
                if (p1 > p2) {
                    player1.add(p1, p2);
                } else {
                    player2.add(p2, p1);
                }
            }
        }

        return getWinner();
    }

    public boolean hasWinner() {
        return player1.isEmpty() || player2.isEmpty();
    }

    public Hand2 getWinner() {
        if (!hasWinner()) {
            return null;
        }
        return player1.isEmpty() ? player2 : player1;
    }

    public long getWinnerScore() {
        final Hand2 winner = getWinner();
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
