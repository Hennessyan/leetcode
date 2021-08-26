package facebook;
// Number of Ways to Paint N × 3 Grid
// Q1349
public class Q1411 {
    // explanation : https://github.com/wisdompeak/LeetCode/tree/master/Dynamic_Programming/1411.Number-of-Ways-to-Paint-N%C3%973-Grid
    /*
       six 2-colors-combination, and six 3-colors-combination if n == 1

        R Y G -> Y R Y / Y G Y
              -> G R Y / Y G R
        R Y R -> Y G Y / G R G / Y R Y
              -> Y R G / G R Y
        => color2 = 3*color2' + 2*color3'
           color3 = 2*color2' + 2*color3'
     */
    // O(n) O(1)
    public int numOfWays0(int n) {
        long color2 = 6, color3 = 6;
        long mode = (long) (1e9 + 7);
        for(int i = 1; i < n; i++) {
            long tmp = color2;
            color2 = (3 * color2 + 2 * color3) % mode;
            color3 = (2 * tmp + 2 * color3) % mode;
        }
        return (int) ((color2 + color3) % mode);
    }

    int[] t1;
    int[] t2;
    public int numOfWays(int n) {
        int[] dp = new int[27]; // 3^3 => 27
        t1 = new int[3];
        t2 = new int[3];
        for(int i = 0; i < 27; i++) {
            if(selfOk(i)) {
                dp[i] = 1;
            }
        }

        int mode = (int) (1e9+7);
        for(int i = 1; i < n; i++) {
            int[] tmp = dp.clone();
            for(int p = 0; p < 27; p++) {
                if(selfOk(p)) {
                    dp[p] = 0;
                    for(int q = 0; q < 27; q++) {
                        if(selfOk(q) && crossOK(p,q)) {
                            dp[p] = (dp[p] + tmp[q]) % mode;
                        }
                    }
                }
            }
        }
        int sum = 0;
        for(int i = 0; i < 27; i++) {
            if(selfOk(i)) {
                sum = (sum + dp[i]) % mode;
            }
        }
        return sum;
    }
    // ternary number
    private boolean selfOk(int val) {
        for(int i = 0; i < 3; i++) {
            t1[i] = val % 3;
            val /= 3;
        }
        return t1[0] != t1[1] && t1[1] != t1[2];
    }
    private boolean crossOK(int p, int q) {
        for(int i = 0; i < 3; i++) {
            t1[i] = p % 3;
            p /= 3;
            t2[i] = q % 3;
            q /= 3;
        }
        return t1[0] != t2[0] && t1[1] != t2[1] && t1[2] != t2[2];
    }
}
