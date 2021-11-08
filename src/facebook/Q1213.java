package facebook;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// Intersection of Three Sorted Arrays
public class Q1213 {
    // strictly increasing order means no duplicates
    // if one array is much smaller than another two,
    // we may want to use binary search to check if number in arr1 exist exist in other two arrs
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> res = new ArrayList<>();
        int p1 = 0, p2 = 0, p3 = 0;
        while(p1 < arr1.length && p2 < arr2.length && p3 < arr3.length) {
            if(arr1[p1] == arr2[p2] && arr2[p2] == arr3[p3]) {
                res.add(arr1[p1]);
                p1++;
                p2++;
                p3++;
            } else {
                if(arr1[p1] < arr2[p2]) {
                    p1++;
                } else if(arr2[p2] < arr3[p3]) {
                    p2++;
                } else {
                    p3++;
                }
            }
        }
        return res;
    }

    public List<Integer> arraysIntersection1(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> ans = new ArrayList<>();

        // note that HashMap won't work here as it does not guarantee the key orders
        Map<Integer, Integer> counter = new TreeMap<>();

        for (Integer e: arr1) {
            counter.put(e, counter.getOrDefault(e, 0) + 1);
        }
        for (Integer e: arr2) {
            counter.put(e, counter.getOrDefault(e, 0) + 1);
        }
        for (Integer e: arr3) {
            counter.put(e, counter.getOrDefault(e, 0) + 1);
        }

        for (Integer item: counter.keySet()) {
            if (counter.get(item) == 3) {
                ans.add(item);
            }
        }
        return ans;

    }
}
