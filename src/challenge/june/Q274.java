package challenge.june;

import java.util.Arrays;

// H-Index
public class Q274 {

    public static void main(String[] args) {
        Q274 q = new Q274();
        int[] cs = {3,0,6,1,5};
        System.out.println(q.hIndex(cs));   //3
    }
    // https://leetcode.com/problems/h-index/solution/
    // O(nlgn) O(1)
    public int hIndex1(int[] citations) {
        Arrays.sort(citations);
        int i = 0;
        while(i < citations.length && citations[citations.length - i - 1] > i) {
            i++;
        }
        return i;
    }
    // O(n) O(n)
    public int hIndex(int[] citations) {
        int len = citations.length;
        int[] d = new int[len + 1];
        for(int c : citations) {
            d[Math.min(c, len)]++;
        }
        int k = len;
        for(int s = d[k]; k > s; s += d[k]) {
            k--;
        }
        return k;
    }

    public int hIndex2(int[] cs) {
        int n = cs.length;
        int[] d = new int[n + 1];
        for(int c : cs) {
            d[Math.min(c, n)]++;
        }
        int h = n, total = d[n];
        while(total < h) {
            total += d[--h];
        }
        return h;
    }
}
