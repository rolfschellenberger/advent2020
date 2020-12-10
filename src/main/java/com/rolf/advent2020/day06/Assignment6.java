package com.rolf.advent2020.day06;

import com.rolf.advent2020.util.Assignment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Component
public class Assignment6 extends Assignment {

    private static final int DAY = 6;

    @Override
    protected int getDay() {
        return DAY;
    }

    @Override
    protected Object getResult1() throws IOException {
        final List<String> lines = readLines();
        return QuestionsParser.parse(lines).stream()
                .map(Group::getUniqueAnswers)
                .map(Set::size)
                .reduce(0, Integer::sum);
    }

    @Override
    protected Object getResult2() throws IOException {
        final List<String> lines = readLines();
        return QuestionsParser.parse(lines).stream()
                .map(Group::getIntersectAnswers)
                .map(Set::size)
                .reduce(0, Integer::sum);
    }
}
