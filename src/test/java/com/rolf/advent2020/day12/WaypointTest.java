package com.rolf.advent2020.day12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WaypointTest {

    @Test
    public void test1() {
        final Waypoint waypoint = new Waypoint(10, 2);
        waypoint.rotate(new Action(ActionType.RIGHT, 90));
        assertEquals(2, waypoint.getX());
        assertEquals(-10, waypoint.getY());
    }

    @Test
    public void test2() {
        final Waypoint waypoint = new Waypoint(10, 2);
        waypoint.rotate(new Action(ActionType.RIGHT, 270));
        assertEquals(-2, waypoint.getX());
        assertEquals(10, waypoint.getY());
    }

    @Test
    public void test3() {
        final Waypoint waypoint = new Waypoint(10, 2);
        waypoint.rotate(new Action(ActionType.LEFT, 270));
        assertEquals(2, waypoint.getX());
        assertEquals(-10, waypoint.getY());
    }
}
