package com.rolf.advent2020.day16;

import java.util.ArrayList;
import java.util.List;

public class Input {

    private final List<Range> ranges;
    private final Ticket myTicket;
    private final List<Ticket> otherTickets;

    private Input(final List<Range> ranges, final Ticket myTicket, final List<Ticket> otherTickets) {
        this.ranges = ranges;
        this.myTicket = myTicket;
        this.otherTickets = otherTickets;
    }

    public List<Range> getRanges() {
        return ranges;
    }

    public Ticket getMyTicket() {
        return myTicket;
    }

    public List<Ticket> getOtherTickets() {
        return otherTickets;
    }

    public static Input parse(final List<String> input) {
        // Ranges.
        final List<Range> ranges = new ArrayList<>();
        int lineNumber = 0;
        for (; lineNumber < input.size(); lineNumber++) {
            final String line = input.get(lineNumber);
            if (line.isBlank()) {
                lineNumber += 2;
                break;
            }
            ranges.add(Range.fromValue(line));
        }

        // My ticket
        final Ticket myTicket = Ticket.fromValue(input.get(lineNumber));
        lineNumber += 3;

        // Other tickets
        final List<Ticket> otherTickets = new ArrayList<>();
        for (; lineNumber < input.size(); lineNumber++) {
            final String line = input.get(lineNumber);
            otherTickets.add(Ticket.fromValue(line));
        }

        return new Input(ranges, myTicket, otherTickets);
    }
}
