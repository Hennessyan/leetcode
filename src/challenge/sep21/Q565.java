package challenge.sep21;
// Array Nesting
public class Q565 {
    // elements are all unique, hence no two values point to same index => check length of cycle.
    // O(n) O(n)
    public int arrayNesting(int[] nums) {
        int n = nums.length;
        boolean[] seen = new boolean[n];
        int res = 0, count = 0;
        for(int i = 0; i < n; i++) {
            if(!seen[i]) {
                count = 0;
                int start = i;
                do {
                    seen[start] = true;
                    start = nums[start];
                    count++;
                } while(start != i);
                res = Math.max(res, count);
            }
        }
        return res;
    }
    // O(n) O(1)
    public int arrayNesting1(int[] nums) {
        int res = 0, count = 0, start = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != -1) {
                start = i;
                count = 0;
                while(nums[start] != -1) {
                    int tmp = start;
                    start = nums[start];
                    nums[tmp] = -1;
                    count++;
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }
}
