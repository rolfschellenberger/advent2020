package com.rolf.advent2020.day21;

import com.rolf.advent2020.util.Assignment;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Assignment21 extends Assignment {

    private static final int DAY = 21;

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
        final List<Food> food = lines.stream()
                .map(Food::from)
                .collect(Collectors.toList());
        final FoodList foodList = new FoodList(food);
        final Map<String, String> allergenToIngredient = foodList.getAllergensMap();
        final List<String> ingredients = foodList.getIngredients();
        for (final String matchedIngredient : allergenToIngredient.values()) {
            while (ingredients.remove(matchedIngredient)) ;
        }
        return ingredients.size();
    }

    @Override
    protected Object getResult2() throws IOException {
        final List<String> lines = readLines();
        final List<Food> food = lines.stream()
                .map(Food::from)
                .collect(Collectors.toList());
        final FoodList foodList = new FoodList(food);
        final Map<String, String> allergenToIngredient = foodList.getAllergensMap();
        final List<String> allergens = new ArrayList<>(allergenToIngredient.keySet());
        Collections.sort(allergens);
        final List<String> ingredients = new ArrayList<>();
        for (final String allergen : allergens) {
            ingredients.add(allergenToIngredient.get(allergen));
        }
        return StringUtils.join(ingredients, ',');
    }
}
