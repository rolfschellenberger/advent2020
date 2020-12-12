package com.rolf.advent2020.day12;

public class Map {

    private final Ship ship;
    private final Waypoint waypoint;

    public Map(final Ship ship, final Waypoint waypoint) {
        this.ship = ship;
        this.waypoint = waypoint;
    }

    public void move(final Action action) {
        switch (action.getActionType()) {
            case RIGHT:
            case LEFT:
                waypoint.rotate(action);
                break;
            case FORWARD:
                ship.move(waypoint, action.getValue());
            default:
                waypoint.move(action);
        }
    }

    @Override
    public String toString() {
        return "Map{" +
                "ship=" + ship +
                ", waypoint=" + waypoint +
                '}';
    }
}
