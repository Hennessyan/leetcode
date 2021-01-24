package tech;
// Q307(M) Q1649(H)
public class SegmentTree {

    public static void main(String[] args) {
        SegmentTree st = new SegmentTree();
        int[] arr = {6, 10, 5, 2, 7, 1, 0, 9};
        int n = arr.length;

        // build segment tree
        int[] nums = new int[2 * n];
        System.arraycopy(arr, 0, nums, n, n);
        // 0   1   2   3  4   5  6  7  8  9   10 11 12 13 14 15
        // [0, 10, 10, 9, 10, 5, 7, 9, 6, 10, 5, 2, 7, 1, 0, 9]
        for(int i = n - 1; i > 0; i--) {
            nums[i] = Math.max(nums[2 * i], nums[2 * i + 1]);
        }

        // query : O(lgn)
        System.out.println(st.max(nums, 0, 8)); // 10
        System.out.println(st.max(nums, 1, 5)); // 10
        System.out.println(st.max(nums, 2, 7)); // 7

        st.update(nums, 6, 11);
        System.out.println(st.max(nums, 2, 7)); // 11

    }
    // query : O(lgn)
    private int max(int[] nums, int l, int r) {     // [l, r)
        int max = Integer.MIN_VALUE;
        l += nums.length / 2;
        r += nums.length / 2;

        while(l < r) {
            if((l & 1) == 1) {
                max = Math.max(max, nums[l]);
                l++;
            }
            if((r & 1) == 1) {
                r--;
                max = Math.max(max, nums[r]);
            }
            l /= 2;
            r /= 2;
        }
        return max;
    }

    // update : O(lgn)
    private void update(int[] nums, int i, int val) {
        i += nums.length / 2;
        nums[i] = val;

        int newMax;
        while(i > 1) {
            i /= 2;
            newMax = Math.max(nums[2 * i], nums[2 * i + 1]);

            if(nums[i] != newMax) {
                nums[i] = newMax;
            } else {
                return;
            }
        }
    }
}


