package com.rolf.advent2020.day04;

import java.util.Map;

public abstract class FieldValidator {

    public abstract boolean isValid(final Passport passport);

    boolean isMissingField(final Map<String, String> fields, final String field) {
        return !fields.containsKey(field);
    }

}
