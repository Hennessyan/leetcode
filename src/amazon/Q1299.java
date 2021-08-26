package amazon;
// Replace Elements with Greatest Element on Right Side
public class Q1299 {
    // O(n) O(n)
    public int[] replaceElements(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        int max = arr[n - 1];
        res[n - 1] = -1;
        for(int i = n - 2; i >= 0; i--) {
            res[i] = max;
            if(arr[i] > max) {
                max = arr[i];
            }
        }
        return res;
    }
}
