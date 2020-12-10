package com.rolf.advent2020.day04;

import java.util.Map;

public class ExpirationYearValidator extends FieldValidator {

    private static final String FIELD = "eyr";

    @Override
    public boolean isValid(final Passport passport) {
        final Map<String, String> fields = passport.getFields();
        if (isMissingField(fields, FIELD)) {
            return false;
        }
        return isValid(fields.get(FIELD));
    }

    private boolean isValid(final String value) {
        try {
            final int number = Integer.parseInt(value);
            return number >= 2020 && number <= 2030;
        } catch (final NumberFormatException e) {
            return false;
        }
    }
}
