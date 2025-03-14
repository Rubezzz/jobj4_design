package ru.job4j.algo;

public class SmallestRangeFinder {

    public static int[] findSmallestRange(int[] nums, int k) {
        int length = nums.length;
        int count = k - 1;
        int[] result = new int[2];
        int left = 0, right = count;

        while (left + count < length) {
            int i;
            for (i = 0; i < count; i++) {
                if (nums[left + i] == nums[left + i + 1]) {
                    break;
                }
            }
            if (i == count) {
                result[0] = left;
                result[1] = right;
                return result;
            }
            left++;
            right++;
        }
        return null;
    }
}