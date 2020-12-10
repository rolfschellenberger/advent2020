package com.rolf.advent2020.day02;

import com.rolf.advent2020.util.Assignment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Assignment2 extends Assignment {

    private static final int DAY = 2;

    @Override
    protected int getDay() {
        return DAY;
    }

    @Override
    protected Object getResult1() throws IOException {
        final List<String> lines = readLines();
        return lines.stream()
                .map(PasswordParser::parse)
                .filter(Password::meets)
                .count();
    }

    @Override
    protected Object getResult2() throws IOException {
        final List<String> lines = readLines();
        return lines.stream()
                .map(PasswordParser2::parse)
                .filter(Password2::meets)
                .count();
    }
}
