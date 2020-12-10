package com.rolf.advent2020.day9;

import com.rolf.advent2020.util.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Assignment9 {

    private final Logger logger = LoggerFactory.getLogger(Assignment9.class);

    //    @Scheduled(initialDelay = 0, fixedDelay = Integer.MAX_VALUE)
    public void run1() throws IOException {
        logger.info("Starting 9a...");
        final List<Long> lines = FileReader.readLongs("/9a");
        final Collection collection = new Collection(lines);
        final long result = collection.findFirstNotSum(25);
        logger.info("9a: " + result);
    }

    //    @Scheduled(initialDelay = 0, fixedDelay = Integer.MAX_VALUE)
    public void run2() throws IOException {
        logger.info("Starting 9b...");
        final List<Long> lines = FileReader.readLongs("/9a");
        final Collection collection = new Collection(lines);
        final List<Long> range = collection.findContinuousRange(15353384L);
        final long min = range.stream().min(Long::compare).get();
        final long max = range.stream().max(Long::compare).get();
        final long sum = min + max;
        logger.info("9b: " + range + ": " + sum);
    }
}
