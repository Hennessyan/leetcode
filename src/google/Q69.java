package google;
// Sqrt(x)
public class Q69 {

    public static void main(String[] args) {
        Q69 q = new Q69();
        System.out.println(8);  // 2
    }
    // n is non-negative

    // method1 : O(sqrt(x)) O(1)
    // wrong if x = 2, need to use i = 1 / return 1
    public int mySqrt1(int x) {
        if(x < 2) {
            return x;
        }
        for(int i = 2; i <= x / i; i++) {
            if(i + 1 > x / (i + 1)) {   //注意不是等于
                return i;
            }
        }
        return -1;
    }

    // method2 : O(lgx) O(1)
    public int mySqrt2(int x) {
        if(x < 2) {
            return x;
        }
        long num;
        int l = 2, r = (int) Math.sqrt(x), m;
        while(l <= r) {
            m = l + (r - l) / 2;
            num = (long)m * m;
            if(num == x) {
                return m;
            } else if(num > x) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return r;
    }
    // avoid using long type
    public int mySqrt2_1(int x) {
        if (x == 0) return 0;
        int start = 1, end = x;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (mid == x / mid)// Found the result
                return mid;
            else if (mid > x / mid)// Keep checking the left part
                end = mid - 1;
            else
                start = mid + 1;// Keep checking the right part
        }
        return end;
    }

    // method3 : Newton's method O(lgx) O(1)
    public int mySqrt3(int x) {
        if(x < 2) {
            return x;
        }
        long x1 = x;
        while(x1 * x1 > x) {
            x1 = (x / x1 + x1) / 2;
        }
        return (int) x1;
    }

    public int mySqrt(int x) {
        if (x < 2) return x;

        double x0 = x;
        double x1 = (x0 + x / x0) / 2.0;
        while (Math.abs(x0 - x1) >= 1) {
            x0 = x1;
            x1 = (x0 + x / x0) / 2.0;
        }

        return (int)x1;
    }
}
