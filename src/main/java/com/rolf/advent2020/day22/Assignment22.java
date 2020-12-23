package com.rolf.advent2020.day22;

import com.rolf.advent2020.util.Assignment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Assignment22 extends Assignment {

    private static final int DAY = 22;

    @Override
    protected int getDay() {
        return DAY;
    }

    @Override
    protected Object getResult1() throws IOException {
        final List<String> lines = readLines();
        final List<String> player1 = new ArrayList<>();
        final List<String> player2 = new ArrayList<>();
        List<String> toAdd = player1;
        for (final String line : lines) {
            if (!line.contains("Player")) {
                if (line.isBlank()) {
                    toAdd = player2;
                } else {
                    toAdd.add(line);
                }
            }
        }

        final Hand p1 = Hand.from(player1);
        final Hand p2 = Hand.from(player2);
        final Game game = new Game(p1, p2);
        while (game.move()) ;
        return game.getWinnerScore();
    }

    @Override
    protected Object getResult2() throws IOException {
        final List<String> lines = readLines();
        final List<String> player1 = new ArrayList<>();
        final List<String> player2 = new ArrayList<>();
        List<String> toAdd = player1;
        for (final String line : lines) {
            if (!line.contains("Player")) {
                if (line.isBlank()) {
                    toAdd = player2;
                } else {
                    toAdd.add(line);
                }
            }
        }

        final Hand2 p1 = Hand2.from(1, player1);
        final Hand2 p2 = Hand2.from(2, player2);
        final Game2 game = new Game2(p1, p2);
        game.play();
        return game.getWinnerScore();
    }
}
