package com.rolf.advent2020.passport;

import java.util.Map;

public class IssueYearValidator extends FieldValidator {

    private static final String FIELD = "iyr";

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
            return number >= 2010 && number <= 2020;
        } catch (final NumberFormatException e) {
            return false;
        }
    }
}
