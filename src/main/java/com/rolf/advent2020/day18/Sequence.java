package com.rolf.advent2020.day18;

import java.util.ArrayList;
import java.util.List;

public class Sequence {

    private final List<Long> values = new ArrayList<>();
    private final List<Operator> operators = new ArrayList<>();

    public static Sequence parse(final String input) {
        final Sequence sequence = new Sequence();

        boolean isOperator = false;
        int parentheses = 0;
        boolean parenthesesFound = false;
        int lastSpace = -1;
        for (int i = 0; i < input.length(); i++) {
            final char c = input.charAt(i);
            if (c == '(') {
                parentheses++;
                parenthesesFound = true;
            } else if (c == ')') {
                parentheses--;
                parenthesesFound = true;
            }

            // When hitting a space outside parentheses, parse range
            if (parentheses == 0 && c == ' ') {
                final String value = input.substring(lastSpace + 1, i);
                lastSpace = i;

                if (parenthesesFound) {
                    final Sequence s = Sequence.parse(removeParentheses(value));
                    final long number = s.solve();
                    sequence.values.add(number);
                } else {
                    if (isOperator) {
                        final Operator operator = Operator.from(value.charAt(0));
                        sequence.operators.add(operator);
                    } else {
                        final long number = Long.parseLong(value);
                        sequence.values.add(number);
                    }
                }
                isOperator = !isOperator;
                parenthesesFound = false;
            }
        }

        // Add the last bit
        final String value = input.substring(lastSpace + 1);
        if (parenthesesFound) {
            final Sequence s = Sequence.parse(removeParentheses(value));
            final long number = s.solve();
            sequence.values.add(number);
        } else {
            final long number = Long.parseLong(value);
            sequence.values.add(number);
        }

        return sequence;
    }

    private static String removeParentheses(final String input) {
        if (input.startsWith("(") && input.endsWith(")")) {
            return new StringBuilder(input)
                    .deleteCharAt(input.lastIndexOf(')'))
                    .deleteCharAt(input.indexOf('('))
                    .toString()
                    .trim();
        } else {
            return input;
        }
    }

    public long solve() {
        // Go from left to right
        // 1 + 2 * 4 = 12
        // 2 * 2 + 4 = 8

        long result = values.get(0);
        for (int i = 0; i < operators.size(); i++) {
            final Operator operator = operators.get(i);
            final long value = values.get(i + 1);
            result = switch (operator) {
                case SUM -> result + value;
                case MULTIPLY -> result * value;
                default -> throw new IllegalStateException("Unexpected value: " + operator);
            };
        }
        return result;
    }
}
