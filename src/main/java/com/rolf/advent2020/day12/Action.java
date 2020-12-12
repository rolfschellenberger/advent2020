package com.rolf.advent2020.day12;

public class Action {
    private final ActionType actionType;
    private final int value;

    Action(final ActionType actionType, final int value) {
        this.actionType = actionType;
        this.value = value;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public int getValue() {
        return value;
    }

    public static Action from(final String input) {
        final ActionType actionType = ActionType.fromValue(input.charAt(0));
        final int value = Integer.parseInt(input.substring(1));
        return new Action(actionType, value);
    }
}
