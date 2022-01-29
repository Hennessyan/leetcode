package linkedin;
// Sort Transformed Array
public class Q360 {

    // a > 0, two sides bigger than middle
    // otherwise, middle bigger than two sides
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        if(nums == null || nums.length == 0) return nums;
        int n = nums.length, l = 0, r = n - 1;
        int[] ans = new int[n];
        int index = a > 0 ? n - 1 : 0;  // here  a > 0
        while(l <= r) {
            if(a > 0) {                 // here a > 0
                int ql = quadratic(nums[l], a, b, c);
                int qr = quadratic(nums[r], a, b, c);
                if(ql < qr) {
                    ans[index] = qr;
                    r--;
                } else {
                    ans[index] = ql;
                    l++;
                }
                index--;
            } else {
                int ql = quadratic(nums[l], a, b, c);
                int qr = quadratic(nums[r], a, b, c);
                if(ql < qr) {
                    ans[index] = ql;
                    l++;
                } else {
                    ans[index] = qr;
                    r--;
                }
                index++;
            }
        }
        return ans;
    }
    private int quadratic(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }
}
