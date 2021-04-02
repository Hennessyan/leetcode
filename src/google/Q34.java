package google;
// Find First and Last Position of Element in Sorted Array
public class Q34 {

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1,-1};
        res[0] = getFirst(nums, target);
        res[1] = getLast(nums, target);
        return res;
    }
    private int getFirst(int[] nums, int target) {
        int val = -1;
        int l = 0, r = nums.length - 1;
        while(l <= r) {
            int m = l + (r - l) / 2;
            if(nums[m] >= target) {
                r = m - 1;
            }else {
                l = m + 1;
            }
            if(nums[m] == target) val = m;
        }
        return val;
    }
    private int getLast(int[] nums, int target) {
        int val = -1;
        int l = 0, r = nums.length - 1;
        while(l <= r) {
            int m = l + (r - l) / 2;
            if(nums[m] <= target) {
                l = m + 1;
            }else {
                r = m - 1;
            }
            if(nums[m] == target) val = m;
        }
        return val;
    }
    /*method2*/
//	public int[] searchRange(int[] A, int target) {
//		int start = firstGreaterEqual(A, target);
//		if (start == A.length || A[start] != target) {
//			return new int[]{-1, -1};
//		}
//		return new int[]{start, firstGreaterEqual(A, target + 1) - 1};
//	}
//
//	//find the first number that is greater than or equal to target.
//	//could return A.length if target is greater than A[A.length-1].
//	//actually this is the same as lower_bound in C++ STL.
//	private int firstGreaterEqual(int[] A, int target) {
//		int low = 0, high = A.length;	//注意这里不是a.length -1
//		while (low < high) {	//注意这里是<
//			int mid = low + ((high - low) >> 1);
//			if (A[mid] < target) {
//				low = mid + 1;
//			} else {
//				high = mid;
//			}
//		}
//		return low;
//	}
    /*method3*/
    //Divide and Conquer (O(n) O(lgn) : 所有的都比=> O(n),最深lgn => O(lgn))
    public int[] searchRange0(int[] nums, int target) {
        if(nums == null || nums.length == 0){		//[1] 1 => [0, 0]
            return new int[]{-1, -1};
        }
        return helper(nums, target, 0, nums.length - 1);
    }
    private int[] helper(int[] nums, int target, int lo, int hi) {
        if (nums[lo] == target && nums[hi] == target) return new int[]{lo, hi};
        if (nums[lo] <= target && nums[hi] >= target) {
            int mid = lo + (hi - lo) / 2;
            int[] left = helper(nums, target, lo, mid);
            int[] right = helper(nums, target, mid + 1, hi);
            if (left[0] == -1) return right;
            if (right[0] == -1) return left;
            return new int[]{left[0], right[1]};
        }
        return new int[]{-1, -1};
    }
    //https://leetcode.com/problems/search-for-a-range/solution/
    /*method4 Approach #1 Linear Scan [Accepted]*/
    //fast !!!
    public int[] searchRange1(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        // find the index of the leftmost appearance of `target`.
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                targetRange[0] = i;
                break;
            }
        }

        // if the last loop did not find any index, then there is no valid range
        // and we return [-1, -1].
        if (targetRange[0] == -1) {
            return targetRange;
        }

        // find the index of the rightmost appearance of `target` (by reverse
        // iteration). it is guaranteed to appear.
        for (int j = nums.length-1; j >= 0; j--) {
            if (nums[j] == target) {
                targetRange[1] = j;
                break;
            }
        }

        return targetRange;
    }
}
