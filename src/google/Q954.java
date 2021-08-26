package google;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

// Array of Doubled Pairs
public class Q954 {
    // O(nlgn) O(n)
    public boolean canReorderDoubled(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int val : arr) {
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        int n = arr.length;
        Integer[] b = new Integer[n];  // need Integer for Arrays.sort, can't use arr.clone / Arrays.copyOf().
        for(int i = 0; i < n; i++) {
            b[i] = arr[i];
        }
        Arrays.sort(b, Comparator.comparingInt(Math::abs));
//        Arrays.sort(b, (b1, b2) -> Math.abs(b1) - Math.abs(b2));
        for(int num : b) {
            Integer c0 = map.get(num);
            if(c0 == 0) continue;
            Integer c1 = map.get(num * 2);
            if(c1 == null || c1 == 0) return false;
            map.put(num, c0 - 1);
            map.put(2 * num, c1 - 1);
        }
        return true;
    }
    // O(n) O(n)
    public boolean canReorderDoubled1(int[] arr) {
        int n = arr.length, max = Integer.MIN_VALUE;
        for(int num : arr) {
            max = Math.max(max, Math.abs(num));
        }
        int[] pos = new int[max + 1];
        int[] neg = new int[max + 1];
        for(int num : arr) {
            if(num >= 0) {
                pos[num]++;
            } else {
                neg[-num]++;
            }
        }

        for(int i = 0; i < max + 1; i++) {
            if(pos[i] > 0) {
                if(2 * i > max || pos[2 * i] < pos[i]) return false;
                pos[2 * i] -= pos[i];
            }
            if(neg[i] > 0) {
                if(2 * i > max || neg[2 * i] < neg[i]) return false;
                neg[2 * i] -= neg[i];
            }
        }
        return true;
    }
}
