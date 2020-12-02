package com.rolf.advent2020.util;

public class Password2 {

    private final String password;
    private final LetterRequirements2 requirements;

    public Password2(final String password, final LetterRequirements2 requirements) {
        this.password = password;
        this.requirements = requirements;
    }

    public boolean meets() {
        return requirements.meets(password);
    }
}
