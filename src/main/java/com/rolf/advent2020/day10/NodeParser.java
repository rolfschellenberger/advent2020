package com.rolf.advent2020.day10;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodeParser {

    private NodeParser() {
    }

    public static long parseNodes(final List<Long> input) {
        // Remember recursive loops
        final Map<Node, Long> cache = new HashMap<>(input.size());

        // Convert to nodes.
        final Map<Long, Node> nodesByValue = createNodeMapByValue(input);

        // Build tree.
        Node highestValueNode = new Node(0);
        for (final Node node : nodesByValue.values()) {
            if (node.getValue() > highestValueNode.getValue()) {
                highestValueNode = node;
            }
            final Node one = nodesByValue.get(node.getValue() + 1);
            final Node two = nodesByValue.get(node.getValue() + 2);
            final Node three = nodesByValue.get(node.getValue() + 3);
            node.addConnection(one);
            node.addConnection(two);
            node.addConnection(three);
        }

        // Now loop from highest node back up and go depth first, so we can use a cache.
        return loopBackUp(cache, highestValueNode);
    }

    private static Map<Long, Node> createNodeMapByValue(final List<Long> input) {
        final Map<Long, Node> nodes = new HashMap<>(input.size());
        final Node startNode = new Node(0);
        nodes.put(startNode.getValue(), startNode);
        for (final long value : input) {
            nodes.put(value, new Node(value));
        }
        return nodes;
    }

    private static long loopBackUp(final Map<Node, Long> cache, final Node node) {
        // Cache hit.
        if (cache.containsKey(node)) {
            return cache.get(node);
        }

        // Count paths upwards.
        long count = 0;
        for (final Node parent : node.getParentNodes()) {
            // Root
            if (parent.getValue() == 0) {
                count++;
            }

            // Depth first
            count += loopBackUp(cache, parent);
        }

        // Add to cache
        cache.put(node, count);
        return count;
    }
}
