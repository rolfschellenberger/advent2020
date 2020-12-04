package com.rolf.advent2020.passport;

import java.util.*;

public class Passport {

    private static final List<FieldValidator> REQUIRED_FIELD_LIST = List.of(
            new BirthYearValidator(),
            new IssueYearValidator(),
            new ExpirationYearValidator(),
            new HeightValidator(),
            new HairColorValidator(),
            new EyeColorValidator(),
            new PassportIdValidator()
    );

    private static final Set<String> REQUIRED_FIELDS = new HashSet<>() {
        {
            add("byr");
            add("iyr");
            add("eyr");
            add("hgt");
            add("hcl");
            add("ecl");
            add("pid");
            // cid field is optionally
        }
    };
    private final Map<String, String> fields;

    public Passport(final Map<String, String> fields) {
        this.fields = fields;
    }

    public Map<String, String> getFields() {
        return fields;
    }

    public boolean contains(final String field) {
        return fields.containsKey(field);
    }

    public boolean isValid() {
        final Set<String> uniqueFields = fields.keySet();
        return uniqueFields.containsAll(REQUIRED_FIELDS);
    }

    public boolean isValid2() {
        boolean valid = true;
        for (final FieldValidator validator : REQUIRED_FIELD_LIST) {
            valid &= validator.isValid(this);
        }
        return valid;
    }

    public static Passport from(final String passportInput) {
        final String[] elements = passportInput.trim().split(" ");
        final Map<String, String> fields = new HashMap<>();
        for (final String element : elements) {
            final String[] keyValue = element.split(":");
            if (keyValue.length == 2) {
                fields.put(keyValue[0].trim(), keyValue[1].trim());
            }
        }
        return new Passport(fields);
    }
}
