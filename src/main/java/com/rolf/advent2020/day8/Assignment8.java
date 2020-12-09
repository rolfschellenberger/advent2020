package com.rolf.advent2020.day8;

import com.rolf.advent2020.util.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Assignment8 {

    private final Logger logger = LoggerFactory.getLogger(Assignment8.class);

    //    @Scheduled(initialDelay = 0, fixedDelay = Integer.MAX_VALUE)
    public void run1() throws IOException {
        logger.info("Starting 8a...");
        final List<String> lines = FileReader.readLines("/8a");
        final Program program = Program.from(lines);
        final Result state = program.execute();
        logger.info("8a: " + state);
    }

    //    @Scheduled(initialDelay = 0, fixedDelay = Integer.MAX_VALUE)
    public void run2() throws IOException {
        logger.info("Starting 8b...");
        final List<String> lines = FileReader.readLines("/8a");
        final Program program = Program.from(lines);
        final Result state = program.executeTree();
        logger.info("8b: " + state);
    }
}
