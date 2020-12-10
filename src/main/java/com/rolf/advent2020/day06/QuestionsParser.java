package com.rolf.advent2020.day06;

import java.util.ArrayList;
import java.util.List;

public class QuestionsParser {

    private QuestionsParser() {
    }

    public static List<Group> parse(final List<String> input) {
        final List<Group> groups = new ArrayList<>();

        Group group = new Group();
        for (final String line : input) {
            if (line.isBlank()) {
                groups.add(group);
                group = new Group();
            } else {
                final Person person = new Person();
                line.chars()
                        .mapToObj(i -> (char) i)
                        .forEach(person::addAnswer);
                group.add(person);
            }
        }

        // Final
        if (!group.isEmpty()) {
            groups.add(group);
        }

        return groups;
    }
}
