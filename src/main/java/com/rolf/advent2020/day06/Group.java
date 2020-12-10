package com.rolf.advent2020.day06;

import java.util.*;
import java.util.stream.Collectors;

public class Group {

    private final List<Person> persons = new ArrayList<>();

    public boolean isEmpty() {
        return persons.isEmpty();
    }

    public Group add(final Person person) {
        this.persons.add(person);
        return this;
    }

    public Set<Character> getUniqueAnswers() {
        return persons.stream()
                .map(Person::getAnswers)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    public Set<Character> getIntersectAnswers() {
        if (persons.isEmpty()) {
            return Collections.emptySet();
        }
        final Set<Character> intersection = new HashSet<>(persons.get(0).getAnswers());
        for (final Person person : persons) {
            intersection.retainAll(person.getAnswers());
        }
        return intersection;
    }
}
