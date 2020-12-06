package com.rolf.advent2020.day4;

import java.util.Map;
import java.util.regex.Pattern;

public class HairColorValidator extends FieldValidator {

    private static final String FIELD = "hcl";
    private static final Pattern PATTERN = Pattern.compile("#[0-9a-f]{6}");

    @Override
    public boolean isValid(final Passport passport) {
        final Map<String, String> fields = passport.getFields();
        if (isMissingField(fields, FIELD)) {
            return false;
        }
        return isValid(fields.get(FIELD));
    }

    private boolean isValid(final String value) {
        return PATTERN.matcher(value).matches();
    }
}
