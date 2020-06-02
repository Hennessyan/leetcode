package challenge.april;

// Happy Number
public class Q202 {

    public static void main(String[] args) {
        Q202 q = new Q202();
        System.out.println(q.isHappy(19));
    }

    // method1: Floyd Cycle detection algorithm
    // O(lgn) : because of Time complexity of sum method (either cycle or no cycle is constant times)
    // O(1)
    public boolean isHappy(int n) {
        int slow = n, fast = n;
        do {
            slow = sum(slow);
            fast = sum(fast);
            fast = sum(fast);
        } while (slow != fast);
        return slow == 1 ? true : false;
    }

    // O(lgn)
    private int sum(int val) {
        int sum = 0;
        while (val > 0) {
            sum += (val % 10) * (val % 10);
            val /= 10;
        }
        return sum;
    }

    //一定会得到只有一位数的情况，而在0～9中只有1跟7是happy number
//    public boolean isHappy(int n) {
//        while (true) {
//            int sum = 0;
//            while (n > 0) {
//                sum += (n % 10) * (n % 10);
//                n /= 10;
//            }
//            if (sum / 10 == 0) {
//                if (sum == 1 || sum == 7) {
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//            n = sum;
//        }
//    }
}
