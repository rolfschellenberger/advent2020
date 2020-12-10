package com.rolf.advent2020.day02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordParser2 {

    private static final Pattern PATTERN = Pattern.compile("(\\d+)-(\\d+) (\\w+): (\\w+)");

    private PasswordParser2() {
    }

    public static Password2 parse(final String input) {
        final Matcher matcher = PATTERN.matcher(input);
        if (matcher.matches()) {
            final int position1 = Integer.parseInt(matcher.group(1));
            final int position2 = Integer.parseInt(matcher.group(2));
            final String character = matcher.group(3);
            final String password = matcher.group(4);
            return new Password2(password, new LetterRequirements2(character.charAt(0), position1, position2));
        }
        throw new RuntimeException("Invalid input to parse: " + input);
    }
}
