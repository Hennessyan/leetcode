package challenge;
// Missing Number In Arithmetic Progression (等差数列)
// Q1502
public class Q1228 {
    // O(n) O(1)
    public int missingNumber(int[] arr) {
        int n = arr.length;
        int diff = (arr[n - 1] - arr[0]) / n;       // A0 + (n - 1)d = An
        int expect = arr[0];
        for(int val : arr) {
            if(val != expect) {
                return expect;
            }
            expect += diff;
        }
        return expect;
    }
    // O(lgn) O(1)
    public int missingNumber1(int[] arr) {
        int n = arr.length;
        int diff = (arr[n - 1] - arr[0]) / n;
        int l = 0, r = n - 1;
        while(l < r) {
            int mid = l + (r - l) / 2;
            int cur = arr[0] + mid * diff;
            if(cur == arr[mid]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return arr[0] + l * diff;
    }
}
