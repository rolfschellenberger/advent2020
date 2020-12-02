package com.rolf.advent2020.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordParser {

    private static final Pattern PATTERN = Pattern.compile("(\\d+)-(\\d+) (\\w+): (\\w+)");

    private PasswordParser() {
    }

    public static Password parse(final String input) {
        final Matcher matcher = PATTERN.matcher(input);
        if (matcher.matches()) {
            final int minCount = Integer.parseInt(matcher.group(1));
            final int maxCount = Integer.parseInt(matcher.group(2));
            final String character = matcher.group(3);
            final String password = matcher.group(4);
            return new Password(password, new LetterRequirements(character.charAt(0), minCount, maxCount));
        }
        throw new RuntimeException("Invalid input to parse: " + input);
    }
}
