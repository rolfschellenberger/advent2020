package com.rolf.advent2020.day7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BagRulesParser {

    private static final String CONTAINS = " bags contain ";
    private static final String NO_OTHER_BAGS = "no other bags.";
    private static final String COMMA = ",";
    private static final String SPACE = " ";

    private BagRulesParser() {
    }

    public static BagRules parse(final List<String> input) {
        final BagRules rules = new BagRules();

        for (final String line : input) {
            final Bag fromBag = getFrom(line);
            final List<Bag> toBags = getTo(line);
            for (final Bag toBag : toBags) {
                rules.addRule(fromBag, toBag);
            }
        }

        return rules;
    }

    private static Bag getFrom(final String line) {
        return new Bag(line.substring(0, line.indexOf(CONTAINS)));
    }

    private static List<Bag> getTo(final String line) {
        if (line.endsWith(NO_OTHER_BAGS)) {
            return Collections.emptyList();
        }

        final String bagListLine = line.substring(line.indexOf(CONTAINS) + CONTAINS.length());
        return getBags(bagListLine);
    }

    private static List<Bag> getBags(final String line) {
        final String[] bagColors = line.split(COMMA);

        final List<Bag> bags = new ArrayList<>();
        for (final String bagColorLine : bagColors) {
            final String[] elements = bagColorLine.trim().split(SPACE);
            final int amount = Integer.parseInt(elements[0]);
            final String color = elements[1] + SPACE + elements[2];
            bags.add(new Bag(color, amount));
        }
        return bags;
    }
}
