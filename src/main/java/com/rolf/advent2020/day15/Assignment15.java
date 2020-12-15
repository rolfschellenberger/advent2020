package com.rolf.advent2020.day15;

import com.rolf.advent2020.util.Assignment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class Assignment15 extends Assignment {

    private static final int DAY = 15;

    @Override
    protected boolean isEnabled() {
        return true;
    }

    @Override
    protected int getDay() {
        return DAY;
    }

    @Override
    protected Object getResult1() {
        // 5,2,8,16,18,0,1
        final Map<Integer, Integer> numbers = creatStartMap(5, 2, 8, 16, 18, 0);
        return playGame(numbers, 1, 2020);
    }

    @Override
    protected Object getResult2() throws IOException {
        // 5,2,8,16,18,0,1
        final Map<Integer, Integer> numbers = creatStartMap(5, 2, 8, 16, 18, 0);
        return playGame(numbers, 1, 30000000);
    }

    private Map<Integer, Integer> creatStartMap(final int... numbers) {
        final Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            map.put(numbers[i], i + 1);
        }
        return map;
    }

    private Object playGame(final Map<Integer, Integer> numbers, final int lastValue, final int lastRound) {
        int last = lastValue;
        for (int i = numbers.size() + 1; i < lastRound; i++) {
            final Integer lastTurn = numbers.get(last);
            int next;
            if (lastTurn == null) {
                next = 0;
            } else {
                next = i - lastTurn;
            }
            numbers.put(last, i);

            last = next;
        }
        return last;
    }
}
