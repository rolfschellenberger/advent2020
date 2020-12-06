package com.rolf.advent2020.day6;

import com.rolf.advent2020.util.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Component
public class Assignment6 {

    private final Logger logger = LoggerFactory.getLogger(Assignment6.class);

    @Scheduled(initialDelay = 0, fixedDelay = Integer.MAX_VALUE)
    public void run1() throws IOException {
        logger.info("Starting 6a...");
        final List<String> lines = FileReader.readLines("/6a");
        final int sum = QuestionsParser.parse(lines).stream()
                .map(Group::getUniqueAnswers)
                .map(Set::size)
                .reduce(0, Integer::sum);
        logger.info("6a: " + sum);
    }

    @Scheduled(initialDelay = 0, fixedDelay = Integer.MAX_VALUE)
    public void run2() throws IOException {
        logger.info("Starting 6b...");
        final List<String> lines = FileReader.readLines("/6a");
        final int sum = QuestionsParser.parse(lines).stream()
                .map(Group::getIntersectAnswers)
                .map(Set::size)
                .reduce(0, Integer::sum);
        logger.info("6b: " + sum);
    }
}
