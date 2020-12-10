package com.rolf.advent2020.day07;

import com.rolf.advent2020.util.Assignment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Assignment7 extends Assignment {

    private static final int DAY = 7;

    @Override
    protected int getDay() {
        return DAY;
    }

    @Override
    protected Object getResult1() throws IOException {
        final List<String> lines = readLines();
        final BagRules bagRules = BagRulesParser.parse(lines);
        return bagRules.findContainingBags(new Bag("shiny gold")).size();
    }

    @Override
    protected Object getResult2() throws IOException {
        final List<String> lines = readLines();
        final BagRules bagRules = BagRulesParser.parse(lines);
        return bagRules.findContainBagCount(new Bag("shiny gold"));
    }
}
