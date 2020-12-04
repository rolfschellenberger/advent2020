package com.rolf.advent2020.passport;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EyeColorValidator extends FieldValidator {

    private static final String FIELD = "ecl";
    private static final Set<String> COLORS = new HashSet<>() {
        {
            add("amb");
            add("blu");
            add("brn");
            add("gry");
            add("grn");
            add("hzl");
            add("oth");
        }
    };

    @Override
    public boolean isValid(final Passport passport) {
        final Map<String, String> fields = passport.getFields();
        if (isMissingField(fields, FIELD)) {
            return false;
        }
        return isValid(fields.get(FIELD));
    }

    private boolean isValid(final String value) {
        return COLORS.contains(value);
    }
}
