package amazon;

import java.util.*;

// Least Number of Unique Integers after K Removals
public class Q1481 {
    // O(nlgn) O(n)
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        if(arr == null || arr.length == 0) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> list = new ArrayList<>(map.values());
        Collections.sort(list);
        int i = 0;
        while(k > 0 && i < list.size()) {
            k -= list.get(i++);
//            if(k < 0) {
//                i--;
//                break;
//            }
        }
        if(k < 0) i--;
        return list.size() - i;
    }
    // use this method!
    public int findLeastNumOfUniqueInts1(int[] arr, int k) {
        Arrays.sort(arr);
        int n = arr.length;
        int[] fre = new int[n+1];
        int unique = 1, count = 1;
        for(int i = 1; i < n; i++) {
            if(arr[i] != arr[i - 1]) {
                unique++;
                fre[count]++;
                count = 1;
            } else {
                count++;
            }
        }
        fre[count]++;

        for(int i = 1; i <= n && k > 0; i++) {
            if(fre[i] * i <= k) {
                k -= fre[i] * i;
                unique -= fre[i];
            } else {
                unique -= k / i;
                break;
            }
        }
        return unique;
    }
}
