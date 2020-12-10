package com.rolf.advent2020.day06;

import java.util.HashSet;
import java.util.Set;

public class Person {

    private final Set<Character> answers = new HashSet<>();

    public Person addAnswer(final char answer) {
        this.answers.add(answer);
        return this;
    }

    public Set<Character> getAnswers() {
        return answers;
    }
}
