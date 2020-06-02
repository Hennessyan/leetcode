package challenge.april;

import java.util.HashMap;
import java.util.Map;

// Counting Elements
public class CountingElements {

    public static void main(String[] args) {
        CountingElements ce = new CountingElements();
        int[] arr = {1, 1, 3, 3, 5, 5, 7, 7};
        System.out.println(ce.countElements(arr));  // 0

    }

    //[1,1,2] => 2 count number of x which has x + 1 in arr
    public int countElements(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : arr) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        int count = 0;
        for (int key : map.keySet()) {
            if (map.containsKey(key + 1)) {
                count += map.get(key);
            }
        }
        return count;
    }
}
