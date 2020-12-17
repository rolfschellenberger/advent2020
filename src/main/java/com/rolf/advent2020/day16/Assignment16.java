package com.rolf.advent2020.day16;

import com.rolf.advent2020.util.Assignment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Assignment16 extends Assignment {

    private static final int DAY = 16;

    @Override
    protected int getDay() {
        return DAY;
    }

    @Override
    protected Object getResult1() throws IOException {
        final List<String> lines = readLines();
        final Input input = Input.parse(lines);

        final Set<Integer> allowedValues = input.getRanges().stream()
                .map(Range::getAllowedValues)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());

        return input.getOtherTickets().stream()
                .map(Ticket::getNumbers)
                .flatMap(Set::stream)
                .filter(n -> !allowedValues.contains(n))
                .reduce(0, Integer::sum);
    }

    @Override
    protected Object getResult2() throws IOException {
        final List<String> lines = readLines();
        final Input input = Input.parse(lines);

        final Set<Integer> allowedValues = input.getRanges().stream()
                .map(Range::getAllowedValues)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());

        final List<Ticket> validTickets = new ArrayList<>();
        validTickets.add(input.getMyTicket());
        for (final Ticket otherTicket : input.getOtherTickets()) {
            if (allowedValues.containsAll(otherTicket.getNumbers())) {
                validTickets.add(otherTicket);
            }
        }

        // Start with all possible ranges per field
        final List<Set<Range>> fields = new ArrayList<>();
        for (int i = 0; i < input.getRanges().size(); i++) {
            fields.add(i, new HashSet<>(input.getRanges()));
        }

        // Now iterate the tickets to see if certain fields are no match, so should be removed.
        validTickets.forEach(t -> filterFields(fields, t));

        // Now iterate the fields to reduce the options
        reduceFields(fields);

        // Look for the fields that start with 'departure' on my ticket.
        long result = 1;
        for (int i = 0; i < fields.size(); i++) {
            final Range field = fields.get(i).iterator().next();
            if (field.getName().startsWith("departure")) {
                result *= input.getMyTicket().getNumberList().get(i);
            }
        }

        return result;
    }

    private void filterFields(final List<Set<Range>> fields, final Ticket ticket) {
        for (int i = 0; i < ticket.getNumberList().size(); i++) {
            final int number = ticket.getNumberList().get(i);
            final Set<Range> ranges = fields.get(i);
            final Set<Range> possibleRanges = ranges.stream()
                    .filter(r -> r.getAllowedValues().contains(number))
                    .collect(Collectors.toSet());
            fields.set(i, possibleRanges);
        }
    }

    private void reduceFields(final List<Set<Range>> fields) {
        boolean changes = true;
        while (changes) {
            changes = false;

            // Find unique ranges
            final Set<Range> uniqueRanges = new HashSet<>();
            for (final Set<Range> ranges : fields) {
                // Unique == 1
                if (ranges.size() == 1) {
                    uniqueRanges.add(ranges.iterator().next());
                }
            }

            // Remove unique ranges from non-unique fields
            for (final Set<Range> ranges : fields) {
                if (ranges.size() > 1) {
                    changes |= ranges.removeAll(uniqueRanges);
                }
            }
        }
    }
}
