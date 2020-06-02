package google;
// similar as Q410
// Divide Chocolate
public class Q1231 {

    public static void main(String[] args) {
        Q1231 q = new Q1231();
        int[] sweetness = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(q.maximizeSweetness(sweetness, 5));  //6 - [1,2,3], [4,5], [6], [7], [8], [9]
    }
    // TC : O(lg(INT_MAX)*n) => O(31*n) => O(n), SC : O(1)
    public int maximizeSweetness(int[] sweetness, int K) {
        int l = 1, r = Integer.MAX_VALUE;
        while (l < r) {
            int m = l + (r - l + 1) / 2;
            int slice = 0, sweet = 0;
            for (int s : sweetness) {
                sweet += s;
                if (sweet >= m) {
                    sweet = 0;
                    slice++;
                    if (slice > K) {
                        break;
                    }
                }
            }
            if (slice > K) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return l;
    }
}
