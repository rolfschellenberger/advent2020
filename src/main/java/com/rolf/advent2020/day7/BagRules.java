package com.rolf.advent2020.day7;

import java.util.*;

public class BagRules {

    private final Map<Bag, Set<Bag>> bagLookup = new HashMap<>();
    private final Map<Bag, Set<Bag>> bagContains = new HashMap<>();

    public BagRules addRule(final Bag bag, final Bag canHold) {
        final Set<Bag> from = bagLookup.computeIfAbsent(canHold, k -> new HashSet<>());
        from.add(bag);
        final Set<Bag> to = bagContains.computeIfAbsent(bag, k -> new HashSet<>());
        to.add(canHold);
        return this;
    }

    public Set<Bag> findContainingBags(final Bag bag) {
        final Set<Bag> result = new HashSet<>();
        for (final Bag isHeldBy : bagLookup.getOrDefault(bag, Collections.emptySet())) {
            if (result.add(isHeldBy)) {
                result.addAll(findContainingBags(isHeldBy));
            }
        }
        return result;
    }

    public int findContainBagCount(final Bag bag) {
        int count = 0;
        for (final Bag contains : bagContains.getOrDefault(bag, Collections.emptySet())) {
            count += contains.getAmount();
            count += contains.getAmount() * findContainBagCount(contains);
        }
        return count;
    }
}
