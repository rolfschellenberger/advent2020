package com.rolf.advent2020;

import com.rolf.advent2020.util.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class Assignment1 {

    private final Logger logger = LoggerFactory.getLogger(Assignment1.class);

//    @Scheduled(initialDelay = 0, fixedDelay = Integer.MAX_VALUE)
    public void run1() throws IOException {
        logger.info("Starting 1a...");
        final List<Long> numbers = FileReader.readLongs("/1a");
        // 145875
        final Optional<NumberPair> pair = new Sum().findSum(numbers, 2, 2020);
        logger.info("1a: " + pair);
    }

//    @Scheduled(initialDelay = 0, fixedDelay = Integer.MAX_VALUE)
    public void run2() throws IOException {
        logger.info("Starting 1b...");
        final List<Long> numbers = FileReader.readLongs("/1a");
        // 145875
        final Optional<NumberPair> pair = new Sum().findSum(numbers, 3, 2020);
        logger.info("1b: " + pair);
    }
}
