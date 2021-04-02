package challenge.jan21;
// Smallest String With A Given Numeric Value
public class Q1663 {
    // O(n) O(n)
    public String getSmallestString(int n, int k) {
        char[] arr = new char[n];
        for(int i = 0; i < n; i++) {
            int left = n - i - 1;
            if(k > 26 * left) {
                arr[i] = (char) (k - 26 * left - 1 + 'a');
                k = 26 * left;
            } else {
                arr[i] = 'a';
                k -= 1;
            }
        }
        return String.valueOf(arr);
    }

    public String getSmallestString1(int n, int k) {
        char[] arr = new char[n];
        for(int i = n - 1; i >= 0; i--) {
            int add = Math.min(k - i, 26);
            arr[i] = (char) ('a' + add - 1);
            k -= add;
        }
        return String.valueOf(arr);
    }
}
