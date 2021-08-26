package amazon;
// Remove Boxes
public class Q546 {
    // method1 : brute force : O(n!) O(n^2) - depth of n, each round takes O(n) for tmp - TLE
    public int removeBoxes1(int[] boxes) {
        return removes(boxes);
    }
    private int removes(int[] boxes) {
        if(boxes.length == 0) {
            return 0;
        }
        int res = 0;
        for(int i = 0, j = i + 1; i < boxes.length; i++) {
            while(j < boxes.length && boxes[i] == boxes[j]) {
                j++;
            }
            int[] tmp = new int[boxes.length - j + i];
            for(int k = 0, p = 0; k < boxes.length; k++) {
                if(k == i) {
                    k = j;
                }
                if(k < boxes.length) {
                    tmp[p++] = boxes[k];
                }
            }
            res = Math.max(res, removes(tmp) + (j - i) * (j - i));
        }

        return res;
    }
    // https://www.youtube.com/watch?v=wT7aS5fHZhs
    // dp[i][j][k] = max(dp[i][j-1][0] + (k - 1)^2, dp[i][p][k+1], dp[p+1][j-1][0])
    // dp - n^3, sub-question is find the break point which is n => O(n^4)

    // AAA - dp[0][0][2] => 0 + (2 + 1)^2 = 9
    // DP : O(n^4) O(n^3)
    public int removeBoxes(int[] boxes) {
        int[][][] memo = new int[100][100][100];    // 100 is good enough as count is (k + 1) * (k + 1)
        return removes(boxes, 0, boxes.length - 1, 0, memo);
    }
    // keep match and remove ends if possible
    private int removes(int[] boxes, int l, int r, int k, int[][][] memo) {
        if(l > r) return 0;
//        if(memo[l][r][k] != 0) return memo[l][r][k]; - wrong position

        while(l < r && boxes[r] == boxes[r - 1]) {
            r--;
            k++;
        }
        if(memo[l][r][k] != 0) return memo[l][r][k];    // correct position.

        memo[l][r][k] = removes(boxes, l, r - 1, 0, memo) + (k + 1) * (k + 1);
        for(int i = l; i < r; i++) {
            if (boxes[i] == boxes[r]) {
                memo[l][r][k] = Math.max(memo[l][r][k],
                        removes(boxes, l, i, k + 1, memo) + removes(boxes,i + 1, r - 1, 0, memo));
            }
        }
        return memo[l][r][k];
    }

}
