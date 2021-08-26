package amazon;
// Minimum Size Subarray Sum
public class Q209 {
    // O(n) O(1)
    public int minSubArrayLen1(int s, int[] nums) {
        // can't use min = len, because of => [1,2,3,4,5] 15
        int len = nums.length, min = Integer.MAX_VALUE;
        int l = 0, r = 0;
        int sum = 0;
        while(r < len) {
            sum += nums[r];
            while(l <= r && sum >= s) { // use while, and we can ignore l <= r
                min = Math.min(min, r - l + 1);
                sum -= nums[l++];
            }
            r++;
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    //TC: O(nlgn)	SC:O(n)
    //比较难理解
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        int res = n + 1;
        int[] sums = new int[n+1];
        sums[0] = 0;
        for(int i = 1; i < n + 1; i++){
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        for(int i = 0; i < n + 1; i++){
            int right = searchRight(i + 1, n, sums[i] + s, sums);
            if (right == n + 1) break;
            if (res > right - i) res = right - i;
        }
        return res == n + 1 ? 0 : res;
    }
    private int searchRight(int left, int right, int key, int sums[]) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (sums[mid] >= key) right = mid - 1;	//=不能少
            else left = mid + 1;
        }
        return left;
    }

    //TC: O(n^2)	SC: O(n)
//	public int minSubArrayLen(int s, int[] nums) {
//		int n = nums.length;
//		if(n == 0){
//			return 0;
//		}
//		int[] sum = new int[n];
//		int min = Integer.MAX_VALUE;
//		sum[0] = nums[0];
//		for(int i = 1; i < n; i++){
//			sum[i] = sum[i - 1] + nums[i];
//		}
//
//		for (int i = 0; i < n; i++) {
//	        for (int j = i; j < n; j++) {
//	        	int sums = sum[j] - sum[i] + nums[i];
//	        	if(sums >= s){
//	        		min = Math.min(min, j - i + 1);
//	        		break;
//	        	}
//	        }
//		}
//		return min != Integer.MAX_VALUE ? min : 0;
//	}
}
