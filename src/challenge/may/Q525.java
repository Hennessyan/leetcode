package challenge.may;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Contiguous Array
public class Q525 {

    public static void main(String[] args) {

    }

    // O(n) O(n)
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int count = 0, len = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i] == 0 ? -1 : 1;
            if (map.containsKey(count)) {
                len = Math.max(len, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return len;
    }

    public int findMaxLength1(int[] nums) {
        int[] arr = new int[2 * nums.length + 1];
        Arrays.fill(arr, -2);
        arr[nums.length] = -1;
        int maxlen = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 0 ? -1 : 1);
            if (arr[count + nums.length] >= -1) {
                maxlen = Math.max(maxlen, i - arr[count + nums.length]);
            } else {
                arr[count + nums.length] = i;
            }

        }
        return maxlen;
    }
}
