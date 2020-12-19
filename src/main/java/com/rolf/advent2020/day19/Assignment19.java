package com.rolf.advent2020.day19;

import com.rolf.advent2020.util.Assignment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class Assignment19 extends Assignment {

    private static final int DAY = 19;

    @Override
    protected boolean isEnabled() {
        return true;
    }

    @Override
    protected int getDay() {
        return DAY;
    }

    @Override
    protected Object getResult1() throws IOException {
        final List<String> lines = readLines();
        return validRules(lines, false);
    }

    @Override
    protected Object getResult2() throws IOException {
        final List<String> lines = readLines();
        return validRules(lines, true);
    }

    private Object validRules(final List<String> lines, final boolean newRules) {
        final List<Rule> rules = new ArrayList<>();
        Rule root = null;
        int i = 0;

        // Rules
        for (; i < lines.size(); i++) {
            final String line = lines.get(i);
            if (line.isBlank()) {
                i++;
                break;
            }
            final Rule rule = Rule.from(line, newRules);
            if (rule.getId() == 0) {
                root = rule;
            }
            rules.add(rule);
        }
        rules.forEach(r -> r.setAllRules(rules));

        // Input
        int matchCount = 0;
        final Pattern pattern = Pattern.compile(root.toRegex());
        for (; i < lines.size(); i++) {
            if (pattern.matcher(lines.get(i)).matches()) {
                matchCount++;
            }
        }
        return matchCount;
    }
}
