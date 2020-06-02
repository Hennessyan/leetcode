package amaon;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// Largest Unique Number
public class Q1133 {

    public static void main(String[] args) {
        Q1133 q = new Q1133();
        int[] A = {5, 7, 3, 9, 4, 9, 8, 3, 1};
        System.out.println(q.largestUniqueNumber(A));
    }

    // O(n) O(n)
    public int largestUniqueNumber1(int[] A) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : A) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        int max = -1;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                max = Math.max(max, entry.getKey());
            }
        }
        return max;
    }

    // O(nlgn) O(n)
    public int largestUniqueNumber2(int[] A) {
        TreeMap<Integer, Integer> map = new TreeMap<>((a, b) -> b - a);
        for (int a : A) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return -1;
    }
    // based on context: O(n) O(1)
    // 1 <= A.length <= 2000
    // 0 <= A[i] <= 1000
    public int largestUniqueNumber(int[] A) {
        int[] array = new int[1001];
        for(int a : A) {
            array[a]++;
        }
        for(int i = 1000; i >= 0; i--) {
            if(array[i] == 1) {
                return i;
            }
        }
        return -1;
    }
}
