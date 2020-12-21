package com.rolf.advent2020.day21;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Food {
    private final Set<String> ingredients;
    private final Set<String> allergens;

    public Food(final Set<String> ingredients, final Set<String> allergens) {
        this.ingredients = ingredients;
        this.allergens = allergens;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public Set<String> getAllergens() {
        return allergens;
    }

    public Food clone() {
        return new Food(new HashSet<>(ingredients), new HashSet<>(allergens));
    }

    @Override
    public String toString() {
        return "Food{" +
                "ingredients=" + ingredients +
                ", allergens=" + allergens +
                '}';
    }

    public static Food from(final String input) {
        final String[] parts = input.split(" \\(contains ");
        final String[] in = parts[0].split(" ");
        final Set<String> ingredients = new HashSet<>(Arrays.asList(in));
        final String[] al = parts[1].substring(0, parts[1].length() - 1).split(", ");
        final Set<String> allergens = new HashSet<>(Arrays.asList(al));
        return new Food(ingredients, allergens);
    }
}
