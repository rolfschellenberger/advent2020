package com.rolf.advent2020.day13;

public class Bus {
    final long busId;
    final long timeOffset;

    public Bus(final long busId, final long timeOffset) {
        this.busId = busId;
        this.timeOffset = timeOffset;
    }

    public long getBusId() {
        return busId;
    }

    public long getTimeOffset() {
        return timeOffset;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "busId=" + busId +
                ", timeOffset=" + timeOffset +
                '}';
    }
}
