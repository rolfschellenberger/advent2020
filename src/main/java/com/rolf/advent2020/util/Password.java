package com.rolf.advent2020.util;

public class Password {

    private final LetterRequirements requirements;
    private final String password;

    public Password(final String password, final LetterRequirements requirements) {
        this.password = password;
        this.requirements = requirements;
    }

    public boolean meets() {
        return requirements.meets(password);
    }
}
