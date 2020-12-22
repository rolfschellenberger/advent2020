package com.rolf.advent2020.day22;

import java.util.Objects;

public class Round2 {
    private final Hand2 player1;
    private final Hand2 player2;

    public Round2(final Hand2 player1, final Hand2 player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Hand2 getPlayer1() {
        return player1;
    }

    public Hand2 getPlayer2() {
        return player2;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Round2 round2 = (Round2) o;
        return Objects.equals(player1, round2.player1) &&
                Objects.equals(player2, round2.player2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player1, player2);
    }

    @Override
    public String toString() {
        return "Round2{" +
                "player1=" + player1 +
                ", player2=" + player2 +
                '}';
    }
}
