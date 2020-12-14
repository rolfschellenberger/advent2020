package com.rolf.advent2020.day14;


import java.math.BigInteger;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

public class Mask {

    private final Map<Integer, Boolean> mask = new HashMap<>();

    public BigInteger apply(final long input) {
        final String binaryString = Long.toBinaryString(input);
        final String leadingZeroBinaryString = ("000000000000000000000000000000000000" + binaryString).substring(binaryString.length());
        final char[] binary = reverse(leadingZeroBinaryString.toCharArray());
        final BitSet bitSet = BitSet.valueOf(BigInteger.valueOf(input).toByteArray());
        for (Map.Entry<Integer, Boolean> entry : mask.entrySet()) {
            bitSet.set(entry.getKey(), entry.getValue());
            binary[entry.getKey()] = entry.getValue() ? '1' : '0';
        }

        return new BigInteger(new String(reverse(binary)), 2);
    }

    private char[] reverse(final char[] array) {
        final char[] result = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            result[array.length - i - 1] = array[i];
        }
        return result;
    }

    public static Mask fromValue(final String input) {
        final Mask mask = new Mask();
        final String pattern = input.substring(7);
        for (int i = 0; i < pattern.length(); i++) {
            final char c = pattern.charAt(i);
            final int index = pattern.length() - i - 1;
            switch (c) {
                case '1' -> mask.mask.put(index, true);
                case '0' -> mask.mask.put(index, false);
            }
        }
        return mask;
    }

    public Map<Integer, Boolean> getMask() {
        return mask;
    }

    @Override
    public String toString() {
        return "Mask{" +
                "mask=" + mask +
                '}';
    }
}
