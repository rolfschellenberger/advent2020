package com.rolf.advent2020;

import com.rolf.advent2020.passport.Passport;
import com.rolf.advent2020.passport.PassportParser;
import com.rolf.advent2020.util.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Assignment4 {

    private final Logger logger = LoggerFactory.getLogger(Assignment4.class);

    //    @Scheduled(initialDelay = 0, fixedDelay = Integer.MAX_VALUE)
    public void run1() throws IOException {
        logger.info("Starting 4a...");
        final List<String> lines = FileReader.readLines("/4a");
        final List<Passport> passports = PassportParser.parse(lines);
        final long validCount = passports.stream()
                .filter(Passport::isValid)
                .count();
        logger.info("4a: " + validCount);
    }

    //    @Scheduled(initialDelay = 0, fixedDelay = Integer.MAX_VALUE)
    public void run2() throws IOException {
        logger.info("Starting 4b...");
        final List<String> lines = FileReader.readLines("/4a");
        final List<Passport> passports = PassportParser.parse(lines);
        final long validCount = passports.stream()
                .filter(Passport::isValid2)
                .count();
        logger.info("4b: " + validCount);
    }
}
