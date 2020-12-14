package com.rolf.advent2020.day14;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MaskTest {

    @Test
    public void test1() {
        final String maskString = "mask = 1X1XX11110001X11X10100110100000X1111";
        final Mask mask = Mask.fromValue(maskString);
        assertEquals(true, mask.getMask().get(0));
        assertEquals(true, mask.getMask().get(1));
        assertEquals(true, mask.getMask().get(2));
        assertEquals(true, mask.getMask().get(3));
        assertNull(mask.getMask().get(4));
        assertEquals(false, mask.getMask().get(5));
        assertNull(mask.getMask().get(32));
        assertEquals(true, mask.getMask().get(33));
        assertNull(mask.getMask().get(34));
        assertEquals(true, mask.getMask().get(35));
    }

    @Test
    public void test2() {
        final String maskString = "mask = 1X1XX11110001X11X10100110100000X1111";
        final long input = 990891369; // 111011000011111100110101101001
        final Mask mask = Mask.fromValue(maskString);
        final BigInteger result = mask.apply(input);
        // 101001111000101111010011010000001111 = 44975338511
        assertEquals(44975338511L, result.longValue());
    }
}
