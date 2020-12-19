package com.rolf.advent2020.day19;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rule {

    private final int id;
    private final String input;
    private final Map<Integer, Rule> allRules = new HashMap<>();
    private String regex;

    public Rule(final int id, final String input) {
        this.id = id;
        this.input = input;
    }

    public int getId() {
        return id;
    }

    public String getInput() {
        return input;
    }

    public void setAllRules(final List<Rule> rules) {
        allRules.clear();
        rules.forEach(r -> allRules.put(r.getId(), r));
    }

    public String toRegex() {
        if (regex != null) {
            return regex;
        }

        // A fixed string.
        if (input.startsWith("\"")) {
            regex = input.replace("\"", "");
            return regex;
        }

        final StringBuilder sb = new StringBuilder();
        sb.append("(");
        final String[] orParts = input.split(" \\| ");
        for (final String part : orParts) {
            if (orParts.length > 1) {
                sb.append("(");
            }
            final String[] numbers = part.split(" ");
            for (final String number : numbers) {
                final int id = Integer.parseInt(number);
                final Rule rule = allRules.get(id);
                sb.append(rule.toRegex());
            }
            if (orParts.length > 1) {
                sb.append(")");
            }
            sb.append("|");
        }
        // Remove last |
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        regex = sb.toString();
        return regex;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "id=" + id +
                ", input='" + input + '\'' +
                '}';
    }

    public static Rule from(final String line, final boolean newRules) {
        final String[] split = line.split(":");
        final int id = Integer.parseInt(split[0]);
        String input = split[1].trim();
        // Very nasty hack to have a limited set of looping
        if (newRules && id == 8) {
            input = "42 | 42 42 | 42 42 42 | 42 42 42 42 | 42 42 42 42 42 | 42 42 42 42 42 42";
        }
        if (newRules && id == 11) {
            input = "42 31 | 42 42 31 31 | 42 42 42 31 31 31 | 42 42 42 42 31 31 31 31 | 42 42 42 42 42 31 31 31 31 31";
        }
        return new Rule(id, input);
    }
}
