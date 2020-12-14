package com.rolf.advent2020.day14;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mask2 {

    private final char[] mask = new char[36];

    public List<BigInteger> apply(final long input) {

        final char[] binary = getBinaryChars(input);
        for (int i = 0; i < binary.length; i++) {
            switch (mask[i]) {
                case '1', 'X' -> binary[i] = mask[i];
            }
        }

        // Duplicate all possible values for X characters.
        return duplicate(binary);

    }

    private List<BigInteger> duplicate(final char[] binary) {

        List<char[]> variations = new ArrayList<>();
        variations.add(binary);
        int variationSize = 0;

        // Keep iterating until all variations have been processed
        while (variationSize != variations.size()) {
            variationSize = variations.size();
            variations = createNextVariations(variations);
        }

        final List<BigInteger> result = new ArrayList<>();
        for (final char[] variation : variations) {
            result.add(new BigInteger(new String(variation), 2));
        }
        return result;
    }

    private List<char[]> createNextVariations(final List<char[]> variations) {
        final List<char[]> result = new ArrayList<>();
        for (final char[] variation : variations) {
            boolean foundX = false;
            for (int i = 0; i < variation.length; i++) {
                final char c = variation[i];
                if (c == 'X') {
                    final char[] a = Arrays.copyOf(variation, variation.length);
                    a[i] = '1';
                    result.add(a);
                    final char[] b = Arrays.copyOf(variation, variation.length);
                    b[i] = '0';
                    result.add(b);
                    foundX = true;
                    break;
                }
            }
            // If no X was found, add the original variation
            if (!foundX) {
                result.add(variation);
            }
        }
        return result;
    }

    private char[] getBinaryChars(final long input) {
        final String binaryString = Long.toBinaryString(input);
        final String leadingZeroBinaryString = ("000000000000000000000000000000000000" + binaryString).substring(binaryString.length());
        return leadingZeroBinaryString.toCharArray();
    }

    public static Mask2 fromValue(final String input) {
        final Mask2 mask = new Mask2();
        final String pattern = input.substring(7);
        for (int i = 0; i < pattern.length(); i++) {
            mask.mask[i] = pattern.charAt(i);
        }
        return mask;
    }

    @Override
    public String toString() {
        return "Mask{" +
                "mask=" + new String(mask) +
                '}';
    }
}
