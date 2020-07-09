package facebook;
// N-th Tribonacci Number
public class Q1137 {

    public static void main(String[] args) {
        Q1137 q = new Q1137();
        System.out.println(q.tribonacci(4));   //4
    }
    // O(n) O(1)
    public int tribonacci1(int n) {
        if(n < 3) return n == 0 ? 0 : 1;
        int a = 0, b = 1, c = 1;
        for(int i = 3; i <= n; i++) {
            int d = a + b + c;
            a = b;
            b = c;
            c = d;
        }
        return c;
    }

    public static Tri t = new Tri();
    public int tribonacci(int n) {
        return t.nums[n];
    }
}
class Tri {
    private int n = 38;
    public int[] nums = new int[n];
    Tri() {
        nums[1] = 1;
        nums[2] = 1;
        for (int i = 3; i < n; ++i)
            nums[i] = nums[i - 1] + nums[i - 2] + nums[i - 3];
    }
}
