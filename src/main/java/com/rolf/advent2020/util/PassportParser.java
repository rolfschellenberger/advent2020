package com.rolf.advent2020.util;

import com.rolf.advent2020.passport.Passport;
import org.apache.tomcat.util.buf.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PassportParser {

    private PassportParser() {
    }

    public static List<Passport> parse(final List<String> input) {
        final List<Passport> passports = new ArrayList<>();

        final List<String> passportLines = new ArrayList<>();
        for (final String line : input) {
            passportLines.add(line);
            if (line.isBlank()) {
                passports.add(parsePassport(passportLines));
                passportLines.clear();
            }
        }

        // Final passport
        passports.add(parsePassport(passportLines));

        return passports;
    }

    private static Passport parsePassport(final List<String> lines) {
        final String passportInput = StringUtils.join(lines, ' ');
        return Passport.from(passportInput);
    }
}
