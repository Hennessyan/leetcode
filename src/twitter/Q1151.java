package twitter;

import java.util.Arrays;

// Minimum Swaps to Group All 1's Together
public class Q1151 {

    public int minSwaps(int[] data) {
        int ones = Arrays.stream(data).sum();
        int l = 0, r = 0, n = data.length;
        int count_one = 0, max_one = 0;
        while(r < n) {
            count_one += data[r++];
            if(r - l  > ones) {
                count_one -= data[l++];
            }
            max_one = Math.max(max_one, count_one);
        }
        return ones - max_one;
    }
}
