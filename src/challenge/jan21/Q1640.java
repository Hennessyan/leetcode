package challenge.jan21;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

// Check Array Formation Through Concatenation
public class Q1640 {

    // method1: O(nlgn) -> sort and [n * binary search (lgn)]
    // SC : O(n) - sort algorithm will take O(n) space if TC is O(nlgn) (Check sort algorithms)
    public boolean canFormArray(int[] arr, int[][] pieces) {
        Arrays.sort(pieces, new Comparator<int[]>(){
            @Override
            public int compare(int[] p1, int[] p2) {
                return p1[0] - p2[0];
            }
        });

        int i = 0, n = arr.length;
        while(i < n) {
            int index = binarySearch(pieces, arr[i]);
            if(index == -1) {
                return false;
            }
            int[] piece = pieces[index];
            for(int p : piece) {
                if(arr[i++] != p) {
                    return false;
                }
            }
        }
        return true;
    }
    private int binarySearch(int[][] pieces, int val) {
        int l = 0, r = pieces.length - 1;
        while(l <= r) {
            int mid = l + (r - l) / 2;
            if(pieces[mid][0] == val) {
                return mid;
            } else if(pieces[mid][0] > val) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    // method2 : O(n) O(n)
    public boolean canFormArray2(int[] arr, int[][] pieces) {
        Map<Integer, int[]> map = new HashMap<>();
        for(int[] p : pieces) {
            map.put(p[0], p);
        }
        int i = 0, n = arr.length;
        while(i < n) {
            if(!map.containsKey(arr[i])) {
                return false;
            }
            for(int p : map.get(arr[i])) {
                if(arr[i++] != p) {
                    return false;
                }
            }
        }
        return true;
    }
}
