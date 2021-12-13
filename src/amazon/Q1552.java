package amazon;

import java.util.Arrays;

// Magnetic Force Between Two Balls
public class Q1552 {
    // O(nlgn + n*lg(max-min)) O(lgn + lg(max - min))
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int n = position.length, l = 0, r = position[n - 1] - 1, ans = 0;
        while(l <= r) {
            int mid = l + (r - l) / 2;
            if(count(position, mid) >= m) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }
    private int count(int[] p, int val) {
        int total = 0, pre = -1;
        for(int cur : p) {
            if(pre == -1 || cur - pre >= val) {
                total++;
                pre = cur;
            }
        }
        return total;
    }
    // same as method1
    public int maxDistance1(int[] position, int m) {
        Arrays.sort(position);
        int n = position.length, l = 1, r = position[n - 1] - position[0], ans = 0;
        while(l <= r) {
            int mid = l + (r - l) / 2;
            if(count1(position, mid) >= m) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }
    private int count1(int[] p, int val) {
        int total = 1, pre = p[0];
        for(int cur : p) {
            if(cur - pre >= val) {
                total++;
                pre = cur;
            }
        }
        return total;
    }
}
