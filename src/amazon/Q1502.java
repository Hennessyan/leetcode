package amazon;

import java.util.Arrays;

//  Can Make Arithmetic Progression From Sequence
// Q1228(E)
public class Q1502 {
    // check Q1630(M)
    // O(n) O(n)
    public boolean canMakeArithmeticProgression(int[] arr) {
        int n = arr.length;
        boolean[] seen = new boolean[n];
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int a : arr) {
            min = Math.min(min, a);
            max = Math.max(max, a);
        }
        if(min == max) return true;
        if((max - min) % (n - 1) != 0) {            // % (n - 1)
            return false;
        }
        int d = (max - min) / (n - 1);
        for(int a : arr) {
            int tmp = a - min;
            if((tmp % d != 0) || seen[tmp / d]) {   // % d
                return false;
            }
            seen[tmp / d] = true;
        }
        return true;
    }
    // O(nlgn) O(lgn)
    public boolean canMakeArithmeticProgression1(int[] arr) {
        Arrays.sort(arr);
        int d = arr[1] - arr[0];
        for(int i = 1; i < arr.length - 1; i++) {
            if(arr[i] + d != arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
