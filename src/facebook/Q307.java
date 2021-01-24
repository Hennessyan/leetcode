package facebook;

import java.util.Arrays;

public class Q307 {


    // Segment Tree
    class NumArray {
        int[] arr;
        int n;
        public NumArray(int[] nums) {
            n = nums.length;
            arr = new int[2 * n];
            System.arraycopy(nums, 0, arr, n, n);
            for(int i = n - 1; i > 0; i--) {
                arr[i] = arr[2 * i] + arr[2 * i + 1];
            }
        }

        public void update(int index, int val) {
            index += n;
            int diff = val - arr[index];
            arr[index] = val;

//            while(index > 1) {
//                index /= 2;
//                arr[index] = arr[2 * index] + arr[2 * index + 1];
//            }

            while(index > 1) {
                index /= 2;
                arr[index] += diff;
            }
        }
        // [1, 2, 3, 4]

        // [0, 10, 3, 7, 1, 2, 3, 4]
        //  0  1   2  3  4  5  6  7
        public int sumRange(int left, int right) {
            left += n;
            right += n;
            int sum = 0;

            while(left <= right) {
                if((left & 1) == 1) {
                    sum += arr[left++];
                }
                if((right & 1) == 0) {
                    sum += arr[right--];
                }
                left /= 2;
                right /= 2;
            }
            return sum;
        }
    }


    // Binary Indexed Tree
    class NumArray1 {
        int[] bit;
        int n;
        int[] nums;
        public NumArray1(int[] nums) {
            n = nums.length;
            this.nums = nums;
            bit = new int[n + 1];
            for(int i = 0; i < n; i++) {
                bit[i+1] = nums[i];
            }
            make();
            System.out.println(Arrays.toString(bit));
        }
        // build bit
        private void make() {   // O(n) to build tree
            for(int i = 1; i <= n; i++) {
                int p = i + (i & -i);
                if(p <= n) {
                    bit[p] += bit[i];
                }
            }
        }

        private void add(int i, int val) {
            while(i <= n) {
                bit[i] += val;
                i += i & -i;
            }
        }

        private int sum(int i) {
            int sum = 0;
            while(i > 0) {
                sum += bit[i];
                i -= i & -i;
            }
            return sum;
        }

        public void update(int index, int val) {
            int diff = val - nums[index];
            nums[index] = val;
            add(index + 1, diff);
        }

        public int sumRange(int left, int right) {
            return sum(right + 1) - sum(left);
        }
    }
    // this method shows why build tree is O(nlgn)
    class NumArray2 {

        int[] nums;
        int[] biTree;

        public NumArray2(int[] nums) {
            int len = nums.length;
            this.nums = new int[len];
            this.biTree = new int[len + 1];
            for(int i = 0; i < len; i++) update(i, nums[i]);
        }

        public void update(int i, int val) {
            // cc
            int len = nums.length;
            int diff = val - nums[i];
            nums[i] = val;
            i++;
            while(i <= len) {
                biTree[i] += diff;
                i += i & (-i);
            }
        }

        public int sumRange(int i, int j) {
            return prefixSum(j) - prefixSum(i) + nums[i];
        }

        private int prefixSum(int i) {
            i++;
            int sum = 0;
            while(i > 0) {
                sum += biTree[i];
                i -= i & (-i);
            }
            return sum;
        }
    }
}
