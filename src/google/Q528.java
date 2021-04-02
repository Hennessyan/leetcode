package google;

import java.util.Random;

// Random Pick with Weight
public class Q528 {
    // O(N) O(lgN)
//    int[] sum;
//    int total;
//    Random random;
//
//    public Solution(int[] w) {
//        int n = w.length;
//        sum = new int[n];
//        for (int i = 0; i < n; i++) {
//            total += w[i];
//            sum[i] = total;
//        }
//        random = new Random();
//    }
//
//    public int pickIndex() {
//        int val = random.nextInt(total) + 1;
//        int l = 0, r = sum.length - 1;
//        while (l < r) {
//            int m = l + (r - l) / 2;
//            if (sum[m] >= val) {
//                r = m;
//            } else {
//                l = m + 1;
//            }
//        }
//        return r;
//    }
//        public int pickIndex() {
//            int val = random.nextInt(total) + 1; // double val = Math.random() * total;
//            int l = 0, r = sum.length - 1;
//            while (l <= r) {
//                int m = l + (r - l) / 2;
//                if (sum[m] >= val) {
//                    r = m - 1;
//                } else {
//                    l = m + 1;
//                }
//            }
//            return l;
//        }

}
