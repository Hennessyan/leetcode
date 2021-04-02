package google;

import java.util.Arrays;

// Divide Array in Sets of K Consecutive Numbers
public class Q1296 {

    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length == 0 || nums.length % k != 0) {
            return false;
        }

        int groupNum = nums.length / k;

        int[][] buckets = new int[k][groupNum];

        int[] counts = new int[k];

        for (int i = 0; i < nums.length; i++) {
            int index = nums[i] % k;

            if (counts[index] >= groupNum) {
                return false;
            }

            buckets[index][counts[index]] = nums[i];

            counts[index]++;
        }

        for (int i = 0; i < k; i++) {
            Arrays.sort(buckets[i]);
        }

        for (int i = 0; i < groupNum; i++) {
            int minIndex = 0;

            int minValue = Integer.MAX_VALUE;

            for (int j = 0; j < k; j++) {
                if (buckets[j][i] < minValue) {
                    minIndex = j;

                    minValue = buckets[j][i];
                }
            }

            int lastValue = buckets[minIndex][i];

            for (int j = minIndex + 1; j < k; j++) {
                if (buckets[j][i] != lastValue + 1) {
                    return false;
                }

                lastValue += 1;
            }

            for (int j = 0; j < minIndex; j++) {
                if (buckets[j][i] != lastValue + 1) {
                    return false;
                }

                lastValue += 1;
            }
        }

        return true;
    }
}
