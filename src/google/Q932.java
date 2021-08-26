package google;

import java.util.HashMap;
import java.util.Map;

// Beautiful Array
public class Q932 {
    // O(nlgn) O(nlgn)
    Map<Integer, int[]> map;
    public int[] beautifulArray(int n) {
        map = new HashMap<>();
        return helper(n);
    }
    private int[] helper(int n) {
        if(map.containsKey(n)) return map.get(n);
        int[] res = new int[n];
        if(n == 1) {
            res[0] = 1;
        } else {
            int t = 0;
            for(int val : helper((n + 1) / 2)) {
                res[t++] = 2 * val - 1;
            }
            for(int val : helper(n / 2)) {
                res[t++] = 2 * val;
            }
            map.put(n, res);
        }
        return res;
    }
}
