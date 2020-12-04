package com.rolf.advent2020.passport;

import java.util.Map;

public class BirthYearValidator extends FieldValidator {

    private static final String FIELD = "byr";

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
            return number >= 1920 && number <= 2002;
        } catch (final NumberFormatException e) {
            return false;
        }
    }
}
