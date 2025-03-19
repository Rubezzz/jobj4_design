package ru.job4j.algo.sort;

import java.util.Arrays;
import java.util.Comparator;

class IntervalMerger {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(value -> value[0]));
        int[][] result = new int[intervals.length][2];
        int lastIndex = intervals.length - 1;
        int resultSize = 0;
        for (int i = 0; i < lastIndex; i++) {
            int start = i;
            while (i < lastIndex && intervals[i][1] >= intervals[i + 1][0]) {
                i++;
            }
            result[resultSize][0] = intervals[start][0];
            result[resultSize][1] = intervals[i][1];
            resultSize++;
            if (i == lastIndex - 1 && intervals[i][1] < intervals[i + 1][0]) {
                result[resultSize][0] = intervals[lastIndex][0];
                result[resultSize][1] = intervals[lastIndex][1];
                resultSize++;
            }
        }
        return Arrays.copyOf(result, resultSize);
    }
}
