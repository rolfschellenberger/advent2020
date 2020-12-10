package com.rolf.advent2020.day04;

import java.util.Map;

public class HeightValidator extends FieldValidator {

    private static final String FIELD = "hgt";

    @Override
    public boolean isValid(final Passport passport) {
        final Map<String, String> fields = passport.getFields();
        if (isMissingField(fields, FIELD)) {
            return false;
        }
        return isValid(fields.get(FIELD));
    }

    private boolean isValid(final String value) {
        if (isInch(value)) {
            return isValidInch(value);
        } else {
            return isValidCm(value);
        }
    }

    private boolean isInch(final String value) {
        return value.endsWith("in");
    }

    private boolean isValidInch(final String value) {
        try {
            final int number = Integer.parseInt(value.replace("in", ""));
            return number >= 59 && number <= 76;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidCm(final String value) {
        try {
            final int number = Integer.parseInt(value.replace("cm", ""));
            return number >= 150 && number <= 193;
        } catch (final NumberFormatException e) {
            return false;
        }
    }
}
