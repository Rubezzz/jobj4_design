package ru.job4j.algo.hash;

import java.util.*;
import java.util.stream.Collectors;

public class LongestUniqueSubstring {

    public static String longestUniqueSubstring(String str) {
        int size = str.length();
        List<HashSet<Character>> list = new ArrayList<>(size);
        int i = 0;
        while (i < size) {
            HashSet<Character> substr = new HashSet<>();
            for (int j = i; j < size; j++) {
                if (!substr.contains(str.charAt(j))) {
                    substr.add(str.charAt(j));
                } else {
                    break;
                }
            }
            list.add(substr);
            i++;
        }
        return list.stream()
                .max(Comparator.comparingInt(HashSet::size))
                .map(set -> set.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining()))
                .orElse("");
    }
}