package com.rolf.advent2020.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {

    private final long value;
    private final List<Node> parentNodes = new ArrayList<>();

    public Node(final long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public void addConnection(final Node node) {
        if (node != null) {
            node.addParentNode(this);
        }
    }

    private void addParentNode(final Node node) {
        this.parentNodes.add(node);
    }

    public List<Node> getParentNodes() {
        return parentNodes;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Node node = (Node) o;
        return value == node.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
