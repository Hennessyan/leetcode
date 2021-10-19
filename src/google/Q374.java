package google;
// Guess Number Higher or Lower
public class Q374 {
    // binary search : O(lg2n) O(1)
//    public int guessNumber(int n) {
//        if(n == 1) return n;
//        int l = 1, r = n;
//        while(l < r) {
//            int m = l + (r - l) / 2;
//            if(guess(m) == 0) return m;
//            else if(guess(m) == -1) {
//                r = m - 1;
//            } else {
//                l = m + 1;
//            }
//        }
//        return l;
//    }
    // tenary : O(lg3n) O(1)
//    public int guessNumber(int n) {
//        int low = 1;
//        int high = n;
//        while (low <= high) {
//            int mid1 = low + (high - low) / 3;
//            int mid2 = high - (high - low) / 3;
//            int res1 = guess(mid1);
//            int res2 = guess(mid2);
//            if (res1 == 0)
//                return mid1;
//            if (res2 == 0)
//                return mid2;
//            else if (res1 < 0)
//                high = mid1 - 1;
//            else if (res2 > 0)
//                low = mid2 + 1;
//            else {
//                low = mid1 + 1;
//                high = mid2 - 1;
//            }
//        }
//        return -1;
//    }
}
