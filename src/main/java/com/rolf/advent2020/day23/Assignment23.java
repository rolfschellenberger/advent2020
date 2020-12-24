package com.rolf.advent2020.day23;

import com.rolf.advent2020.util.Assignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@Component
public class Assignment23 extends Assignment {

    private static final int DAY = 23;

    @Override
    protected int getDay() {
        return DAY;
    }

    @Override
    protected Object getResult1() throws IOException {
        final Game game = new Game(Arrays.asList(3, 8, 9, 1, 2, 5, 4, 6, 7));
        for (int i = 0; i < 100; i++) {
            game.move();
        }
        return game.getResult1();
    }

    @Override
    protected Object getResult2() throws IOException {
        final Game game = new Game(Arrays.asList(6, 4, 3, 7, 1, 9, 2, 5, 8), 1_000_000);
        for (int i = 0; i < 10_000_000; i++) {
            game.move();
        }
        return game.getResult2();
    }
}
