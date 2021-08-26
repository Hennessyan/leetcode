package google;
// Power of Three
// Q342 Q231 Q1780
public class Q326 {
    // m1: O(log-3-n) O(1)
    public boolean isPowerOfThree(int n) {
        if(n < 1) {
            return false;
        }
        while(n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    // m2: O(log-3-n) O(log-3-n) - very slow
    public boolean isPowerOfThree2(int n) {
        // $ 匹配输入行尾
        // ^ 匹配输入字行首
        return Integer.toString(n, 3).matches("^10*$");
    }
    // m3: O(lgn) -> TC based on algorithm of Math.log10 actually, O(1)

    public boolean isPowerOfThree3(int n) {
        // check if val is integer => val % 1 == 0 (1.5 % 1 => 0.5)
        return (Math.log10(n) / Math.log10(3)) % 1 == 0;
        // log-4-n => lgn / lg 4 = 1/2 * (lgn) / lg2 => lgn / lg2
        // return (num > 0) && (Math.log(num) / Math.log(2) % 2 == 0);
    }
    // wrong method
//    public boolean isPowerOfThree5(int n) {
//        int l = 0, r = (int) (Math.sqrt(n));    // r is not corret
//        while(l <= r) {
//            int m = l + (r - l) / 2;
//            int val = (int) Math.pow(3, m);
//            if(val == n) {
//                return true;
//            } else if(val < n) {
//                l = m + 1;
//            } else {
//                r = m - 1;
//            }
//        }
//        return false;
//    }

    public boolean isPowerOfThree4(int n) {
        for(int i = 0; i < 31; i++) {
            double val = Math.pow(3, i);
            if(val == n) {
                return true;
            } else if(val > n) {
                break;
            }
        }
        return false;
    }
}
