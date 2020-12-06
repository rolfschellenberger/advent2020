package com.rolf.advent2020.day2;

import com.rolf.advent2020.util.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Assignment2 {

    private final Logger logger = LoggerFactory.getLogger(Assignment2.class);

    //    @Scheduled(initialDelay = 0, fixedDelay = Integer.MAX_VALUE)
    public void run1() throws IOException {
        logger.info("Starting 2a...");
        final List<String> lines = FileReader.readLines("/2a");
        final long validCount = lines.stream()
                .map(PasswordParser::parse)
                .filter(Password::meets)
                .count();
        logger.info("2a: " + validCount);
    }

    //    @Scheduled(initialDelay = 0, fixedDelay = Integer.MAX_VALUE)
    public void run2() throws IOException {
        logger.info("Starting 2b...");
        final List<String> lines = FileReader.readLines("/2a");
        final long validCount = lines.stream()
                .map(PasswordParser2::parse)
                .filter(Password2::meets)
                .count();
        logger.info("2b: " + validCount);
    }
}
