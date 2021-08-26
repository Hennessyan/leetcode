package microsoft;
//  Check if Number is a Sum of Powers of Three
public class Q1780 {
    // O(1) O(1) ???
    public boolean checkPowersOfThree(int n) {
//        int val = (int) (Math.log(1e7) / Math.log(3));
//        System.out.println(val);                       => 14
        for(int i = 14; i >= 0; i--) {
            int tmp = (int) Math.pow(3, i);
            if(n - tmp >= 0) {
                n -= tmp;
            }
            if(n == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean checkPowersOfThree1(int n) {
        return solve(1, n);
    }
    private boolean solve(int i, int n) {
        if(n == 0) {
            return true;
        }
        while(i <= n) {

            if(solve(i * 3, n - i)) {
                return true;
            }
            i *= 3;
        }
        return false;
    }
}
