package com.rolf.advent2020.day04;

import com.rolf.advent2020.util.Assignment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Assignment4 extends Assignment {

    private static final int DAY = 4;

    @Override
    protected int getDay() {
        return DAY;
    }

    @Override
    protected Object getResult1() throws IOException {
        final List<String> lines = readLines();
        final List<Passport> passports = PassportParser.parse(lines);
        return passports.stream()
                .filter(Passport::isValid)
                .count();
    }

    @Override
    protected Object getResult2() throws IOException {
        final List<String> lines = readLines();
        final List<Passport> passports = PassportParser.parse(lines);
        return passports.stream()
                .filter(Passport::isValid2)
                .count();
    }
}
