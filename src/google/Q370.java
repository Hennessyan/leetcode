package google;
// Range Addition
public class Q370 {
    // O(n + k) O(1) - no consider res size.
    // follow-up : all elements are not the same: convert [2,3,10,5] to [2, 1, 7, -5] first.
    // more complex version -> https://leetcode.com/articles/a-recursive-approach-to-segment-trees-range-sum-queries-lazy-propagation/
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] res = new int[length];
        for(int[] update : updates) {
            res[update[0]] += update[2];
            if(update[1] + 1 < length) {
                res[update[1] + 1] -= update[2];
            }
        }

        for(int i = 1; i < length; i++) {
            res[i] += res[i - 1];
        }
        return res;
    }
}
