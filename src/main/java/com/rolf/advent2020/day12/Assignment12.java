package com.rolf.advent2020.day12;

import com.rolf.advent2020.util.Assignment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Assignment12 extends Assignment {

    private static final int DAY = 12;

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
        final Ferry ferry = new Ferry();
        lines.stream()
                .map(Action::from)
                .forEach(ferry::move);
        return Math.abs(ferry.getX()) + Math.abs(ferry.getY());
    }

    @Override
    protected Object getResult2() throws IOException {
        final List<String> lines = readLines();
        final Ship ship = new Ship();
        final Waypoint waypoint = new Waypoint(10, 1);
        final Map map = new Map(ship, waypoint);
        lines.stream()
                .map(Action::from)
                .forEach(map::move);
        return Math.abs(ship.getX()) + Math.abs(ship.getY());
    }
}
