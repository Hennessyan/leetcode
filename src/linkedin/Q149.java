package linkedin;

import java.util.HashMap;
import java.util.Map;

// Max Points on a Line
public class Q149 {

    int[][] ps;
    int n;
    public int maxPoints(int[][] ps) {
        this.n = ps.length;
        if(this.n < 3) return this.n;
        this.ps = ps;
        int max = 2;
        for(int i = 0; i < n - 1; i++) {
            max = Math.max(max, check(i) + 1);
        }
        return max;
    }
    private int check(int i) {
        Map<String, Integer> map = new HashMap<>();
        String key = "";
        int x = ps[i][0], y = ps[i][1];
        for(int j = i + 1; j < this.n; j++) {
            int x1 = ps[j][0], y1 = ps[j][1];
            if(x == x1) {
                if(y == y1) {
                    key = "d";  // duplicates
                } else {
                    key = "v";  // vertical
                }
            } else {
                int deltaX = x - x1;
                int deltaY = y - y1;
                int gcd = gcd(deltaX, deltaY);
                if(gcd == 0) {
                    key = "h";  // horizon
                } else {
                    deltaX /= gcd;
                    deltaY /= gcd;
                    key = Math.abs(deltaX) + "/" + Math.abs(deltaY);
                    if(deltaX * deltaY < 0) {
                        key = "-" + key;
                    }
                }
            }
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        int d_count = map.getOrDefault("d", 0);
        for(String tk : map.keySet()) {
            if(!tk.equals("d")) {
                map.put(tk, map.get(tk) + d_count);
            }
        }
        int max = 0;
        for(int val : map.values()) {
            max = Math.max(max, val);
        }
        return max;
    }
    private int gcd(int x, int y) {
        while(y != 0) {
            int tmp = x;
            x = y;
            y = tmp % y;
        }
        return x;
    }
//    private int gcd(int x, int y) {
//        if(x == 0) return y;
//        if(y == 0) return x;
//        return gcd(y, x % y);
//    }
}
