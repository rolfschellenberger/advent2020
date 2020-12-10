package com.rolf.advent2020.day04;

import java.util.Map;

public class PassportIdValidator extends FieldValidator {

    private static final String FIELD = "pid";

    @Override
    public boolean isValid(final Passport passport) {
        final Map<String, String> fields = passport.getFields();
        if (isMissingField(fields, FIELD)) {
            return false;
        }
        return isValid(fields.get(FIELD));
    }

    private boolean isValid(final String value) {
        return value.trim().length() == 9;
    }
}
