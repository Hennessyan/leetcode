package challenge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Powerful Integers
public class Q970 {
    // O(m*n) O(m*n) - m = log-x-bound, n = log-y-bound
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        int a = x == 1 ? bound : (int) (Math.log(bound) / Math.log(x));
        int b = y == 1 ? bound : (int) (Math.log(bound) / Math.log(y));
        Set<Integer> powers = new HashSet<>();

        for(int i = 0; i <= a; i++) {
            for(int j = 0; j <= b; j++) {
                int val = (int) (Math.pow(x, i) + Math.pow(y, j));
                if(val <= bound) {
                    powers.add(val);
                }
                if(y == 1 || val > bound) {
                    break;
                }
            }
            if(x == 1) {
                break;
            }
        }

        return new ArrayList<>(powers);
    }
}
