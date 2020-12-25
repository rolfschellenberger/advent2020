package com.rolf.advent2020.day25;

public class Test {
    public static void main(String[] args) throws Exception {
        part1(12232269
                , 19452773);
    }

    private static void part1(long doorkey, long cardkey) {
        long value = 1;
        long loop = 0;
        while (value != doorkey && value != cardkey) {
            value *= 7;
            value %= 20201227;
            loop++;
        }

        final long subj = value == doorkey ? cardkey : doorkey;

        value = 1;
        for (long i = 0; i < loop; i++) {
            value *= subj;
            value %= 20201227;
        }

        System.out.println(value);
    }
}
