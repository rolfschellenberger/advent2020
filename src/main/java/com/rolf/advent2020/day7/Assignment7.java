package com.rolf.advent2020.day7;

import com.rolf.advent2020.util.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Assignment7 {

    private final Logger logger = LoggerFactory.getLogger(Assignment7.class);

    //    @Scheduled(initialDelay = 0, fixedDelay = Integer.MAX_VALUE)
    public void run1() throws IOException {
        logger.info("Starting 7a...");
        final List<String> lines = FileReader.readLines("/7a");
        final BagRules bagRules = BagRulesParser.parse(lines);
        logger.info("7a: " + bagRules.findContainingBags(new Bag("shiny gold")).size());
    }

    //    @Scheduled(initialDelay = 0, fixedDelay = Integer.MAX_VALUE)
    public void run2() throws IOException {
        logger.info("Starting 7b...");
        final List<String> lines = FileReader.readLines("/7a");
        final BagRules bagRules = BagRulesParser.parse(lines);
        logger.info("7b: " + bagRules.findContainBagCount(new Bag("shiny gold")));
    }
}
