package com.rolf.advent2020.day1;

import com.rolf.advent2020.day5.BoardingPass;
import com.rolf.advent2020.day5.Plane;
import com.rolf.advent2020.day5.SideParser;
import com.rolf.advent2020.util.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Component
public class Assignment5 {

    private final Logger logger = LoggerFactory.getLogger(Assignment5.class);

    //    @Scheduled(initialDelay = 0, fixedDelay = Integer.MAX_VALUE)
    public void run1() throws IOException {
        logger.info("Starting 5a...");
        final List<String> lines = FileReader.readLines("/5a");
        final int maxSeatId = lines.stream()
                .map(SideParser::parse)
                .map(BoardingPass::getSeatId)
                .mapToInt(v -> v)
                .max().orElseThrow(NoSuchElementException::new);
        logger.info("5a: " + maxSeatId);
    }

    //    @Scheduled(initialDelay = 0, fixedDelay = Integer.MAX_VALUE)
    public void run2() throws IOException {
        logger.info("Starting 5b...");
        final List<String> lines = FileReader.readLines("/5a");
        final List<BoardingPass> boardingPasses = lines.stream()
                .map(SideParser::parse)
                .collect(Collectors.toList());

        final List<Integer> seatIds = lines.stream()
                .map(SideParser::parse)
                .map(BoardingPass::getSeatId)
                .sorted()
                .collect(Collectors.toList());

        int previousSeatId = seatIds.get(0);
        for (int seatId : seatIds) {
            if (seatId - previousSeatId > 1) {
                logger.info("5b: " + (seatId - 1));
                logger.info("5b: " + new Plane(boardingPasses));
                break;
            }
            previousSeatId = seatId;
        }
    }
}
