package com.rolf.advent2020.day21;

import java.util.*;
import java.util.stream.Collectors;

public class FoodList {

    private final List<Food> food;
    private final Set<String> allergens;

    public FoodList(final List<Food> food) {
        this.food = food;
        this.allergens = food.stream()
                .map(Food::getAllergens)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    public List<Food> getFood() {
        return food;
    }

    public Set<String> getAllergens() {
        return allergens;
    }

    public List<String> getIngredients() {
        final List<String> result = new ArrayList<>();
        for (final Food f : food) {
            result.addAll(f.getIngredients());
        }
        return result;
    }

    // Map from allergens to ingredients
    public Map<String, String> getAllergensMap() {
        final Map<String, String> allergenToIngredient = new HashMap<>();

        // Build a map from every allergen to all possible food.
        final Map<String, List<Food>> allergenToFood = new HashMap<>();
        for (final Food f : food) {
            for (final String allergen : f.getAllergens()) {
                final List<Food> foodList = allergenToFood.computeIfAbsent(allergen, k -> new ArrayList<>());
                foodList.add(f.clone());
            }
        }

        // Iterate allergens until all are found
        final Set<String> allergens = new HashSet<>(this.allergens);
        while (!allergens.isEmpty()) {
            final Set<String> allergensFound = new HashSet<>();

            for (final String allergen : allergens) {
                // Find the sets of ingredients for this allergen.
                final List<Set<String>> allIngredients = new ArrayList<>();
                for (final Food food : allergenToFood.getOrDefault(allergen, Collections.emptyList())) {
                    allIngredients.add(new HashSet<>(food.getIngredients()));
                }

                // If the union of all ingredients results into 1, this matches the allergen
                final Set<String> union = allIngredients.get(0);
                for (final Set<String> ingredients : allIngredients) {
                    union.retainAll(ingredients);
                }
                if (union.size() == 1) {
                    final String ingredient = union.iterator().next();
                    allergenToIngredient.put(allergen, ingredient);

                    // Remove this ingredient from all food
                    for (final List<Food> foodList : allergenToFood.values()) {
                        for (final Food food : foodList) {
                            food.getIngredients().remove(ingredient);
                        }
                    }

                    // Remove allergen
                    allergensFound.add(allergen);
                }
            }

            // Remove
            allergens.removeAll(allergensFound);
        }

        return allergenToIngredient;
    }
}
