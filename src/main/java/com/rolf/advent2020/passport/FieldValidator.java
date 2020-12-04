package com.rolf.advent2020.passport;

import java.util.Map;

public abstract class FieldValidator {

    public abstract boolean isValid(final Passport passport);

    boolean isMissingField(final Map<String, String> fields, final String field) {
        return !fields.containsKey(field);
    }

}
