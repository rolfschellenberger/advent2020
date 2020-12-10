package com.rolf.advent2020.day10;

import com.rolf.advent2020.util.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Assignment10 {

    private final Logger logger = LoggerFactory.getLogger(Assignment10.class);

    @Scheduled(initialDelay = 0, fixedDelay = Integer.MAX_VALUE)
    public void run1() throws IOException {
        logger.info("Starting 10a...");
        final List<Long> lines = FileReader.readLongs("/10a");
        final long output = Sockets.multiplyOneThreeCount(lines);
        logger.info("10a: " + output);
    }

    @Scheduled(initialDelay = 0, fixedDelay = Integer.MAX_VALUE)
    public void run2() throws IOException {
        logger.info("Starting 10b...");
        final List<Long> lines = FileReader.readLongs("/10a");
        final long parentSize = NodeParser.parseNodes(lines);
        logger.info("10b: " + parentSize);
    }
}
