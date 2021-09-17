package amazon;
// Capacity To Ship Packages Within D Days
public class Q1011 {

    public int shipWithinDays(int[] weights, int days) {
        int r = 0, l = 0;
        for(int w : weights) {
            r += w;
            l = Math.max(l, w);
        }
        while(l <= r) {
            int m = l + (r - l) / 2;
            int tmp = valid(weights, m);
            if(tmp <= days) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
    private int valid(int[] ws, int val) {
        int day = 1, weight = 0;
        for(int w : ws) {
            weight += w;
            if(weight > val) {
                day++;
                weight = w;
            }
        }
        return day;
    }

    public int shipWithinDays1(int[] weights, int days) {
        int r = 0, l = 0;
        for(int w : weights) {
            r += w;
            l = Math.max(l, w);
        }
        while(l < r) {
            int m = l + (r - l) / 2;
            int tmp = valid1(weights, m);
            if(tmp <= days) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
    private int valid1(int[] ws, int val) {
        int day = 1, weight = 0;
        for(int w : ws) {
            if(weight + w > val) {
                day++;
                weight = 0;
            }
            weight += w;
        }
        return day;
    }
}
