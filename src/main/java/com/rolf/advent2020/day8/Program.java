package com.rolf.advent2020.day8;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rolf.advent2020.day8.Operator.JMP;
import static com.rolf.advent2020.day8.Operator.NOP;

public class Program {
    private final List<Command> commands;

    public Program(final List<Command> commands) {
        this.commands = commands;
    }

    public Result execute() {
        final Set<Integer> linesRun = new HashSet<>();
        Result state = new Result(0, 0);
        while (linesRun.add(state.getLine())) {
            final Command command = commands.get(state.getLine());
            state = command.execute(state);
        }
        return state;
    }

    private Result executeSafe() {
        final Set<Integer> linesRun = new HashSet<>();
        Result state = new Result(0, 0);
        boolean go = true;
        while (go) {
            // If we hit a line twice, we are not succesful.
            if (!linesRun.add(state.getLine())) {
                return null;
            }
            // If this is the last line, stop.
            if (state.getLine() + 1 == commands.size()) {
                go = false;
            }

            final Command command = commands.get(state.getLine());
            state = command.execute(state);
        }
        return state;
    }

    public Result executeTree() {
        // Create copies of yourself where every nop->jmp and every jmp->nop.
        final List<Program> programs = new ArrayList<>();
        for (int i = 0; i < commands.size(); i++) {
            final List<Command> modifiedCommands = new ArrayList<>(commands);
            final Command commandToModify = modifiedCommands.get(i);
            if (commandToModify.getOperator() == NOP) {
                modifiedCommands.set(i, new Command(JMP, commandToModify.getArgument()));
            }
            if (commandToModify.getOperator() == JMP) {
                modifiedCommands.set(i, new Command(NOP, commandToModify.getArgument()));
            }
            programs.add(new Program(modifiedCommands));
        }

        // Now try every program for a safe termination.
        for (final Program program : programs) {
            final Result result = program.executeSafe();
            if (result != null) {
                return result;
            }
        }
        throw new RuntimeException("No valid program found.");
    }

    public static Program from(final List<String> lines) {
        return new Program(
                lines.stream()
                        .map(Command::from)
                        .collect(Collectors.toList())
        );
    }
}
