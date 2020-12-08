package com.rolf.advent2020.day8;

public class Command {
    private static final String SPACE = " ";
    private static final String PLUS = "+";
    private static final String EMPTY = "";

    private Operator operator;
    private final int argument;

    public Command(final Operator operator, final int argument) {
        this.operator = operator;
        this.argument = argument;
    }

    public Operator getOperator() {
        return operator;
    }

    public Command setOperator(final Operator operator) {
        this.operator = operator;
        return this;
    }

    public int getArgument() {
        return argument;
    }

    @Override
    public String toString() {
        return "Command{" +
                "operator=" + operator +
                ", argument=" + argument +
                '}';
    }

    public Result execute(final Result input) {
        return switch (operator) {
            case ACC -> new Result(input.getLine() + 1, input.getValue() + argument);
            case JMP -> new Result(input.getLine() + argument, input.getValue());
            case NOP -> new Result(input.getLine() + 1, input.getValue());
        };
    }

    public static Command from(final String line) {
        final String[] parts = line.split(SPACE);
        final Operator operator = Operator.fromValue(parts[0]);
        final int argument = Integer.parseInt(parts[1].replace(PLUS, EMPTY));
        return new Command(operator, argument);
    }
}
