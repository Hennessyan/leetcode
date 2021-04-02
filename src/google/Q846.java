package google;

import java.util.Arrays;
import java.util.TreeMap;

// Hand of Straights
// same as Q1296
public class Q846 {
    // O(nlgn) O(n)
    public boolean isNStraightHand(int[] hand, int W) {
        TreeMap<Integer, Integer> map = new TreeMap<>();	//需要使用TreeMap从最小的做起
        for(int h : hand) {
            map.put(h, map.getOrDefault(h, 0) + 1);
        }
        for(int key : map.keySet()) {
            int count = map.get(key);
            if(count > 0) {
                // for(int i = W - 1; i >= 0; i--) {			//并需要从大往小循环.
                for(int i = 0; i < W; i++) {
                    int tmp = map.getOrDefault(key + i, 0);
                    if(tmp < count) {
                        return false;
                    }
                    map.put(key + i, tmp - count);
                }
            }
        }
        return true;
    }

    public boolean isNStraightHand1(int[] hand, int W) {
        TreeMap<Integer, Integer> count = new TreeMap();
        for (int card: hand) {
            if (!count.containsKey(card))
                count.put(card, 1);
            else
                count.replace(card, count.get(card) + 1);
        }

        while (count.size() > 0) {
            int first = count.firstKey();
            for (int card = first; card < first + W; ++card) {
                if (!count.containsKey(card)) return false;
                int c = count.get(card);
                if (c == 1) count.remove(card);
                else count.replace(card, c - 1);
            }
        }

        return true;
    }
    // much faster
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
