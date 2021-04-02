package google;
// Maximum Points You Can Obtain from Cards
public class Q1423 {

    public int maxScore(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int l = 0, r = n, sum = 0;
        while(k > 0) {
            sum += cardPoints[--r]; //如果是r = n - 1和r--,r会多退一位.
            k--;
        }
        if(r == 0) {   // [9,7,7,9,7,7,9] 7
            return sum;
        }
        int max = sum;
        while(r < n) {
            sum += cardPoints[l++] - cardPoints[r++];
            max = Math.max(max, sum);
        }
        return max;
    }
    // O(n) O(1)
    public int maxScore1(int[] cardPoints, int k) {
        int sum = 0;
        for(int i = 0; i < k; i++) {
            sum += cardPoints[i];
        }
        int n = cardPoints.length;
        if(k == n) {
            return sum;
        }
        int max = sum;
        for(int i = 0; i < k; i++) {
            sum += cardPoints[n - i - 1] - cardPoints[k - 1 - i];
            max = Math.max(max, sum);
        }
        return max;
    }
}
