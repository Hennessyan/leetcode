package amazon;
// Longest Turbulent Subarray
public class Q978 {
    // O(n) O(n)
    public int maxTurbulenceSize1(int[] arr) {
        if(arr == null || arr.length == 0) return 0;
        int n = arr.length, max = 1;
        int[] inc = new int[n];
        int[] dec = new int[n];
        inc[0] = dec[0] = 1;
        for(int i = 1; i < n; i++) {
            if(arr[i - 1] < arr[i]) {
                inc[i] = dec[i - 1] + 1;
                dec[i] = 1;
                max = Math.max(max, inc[i]);
            } else if(arr[i - 1] > arr[i]) {
                dec[i] = inc[i - 1] + 1;
                inc[i] = 1;
                max = Math.max(max, dec[i]);
            } else {
                inc[i] = dec[i] = 1;
            }
        }
        return max;
    }
    // O(n) O(1)
    public int maxTurbulenceSize(int[] arr) {
        if(arr == null || arr.length == 0) return 0;
        int n = arr.length, max = 1, anchor = 0;
        for(int i = 1; i < n; i++) {
            int c = Integer.compare(arr[i-1],arr[i]);
            if(c == 0) {
                anchor = i;
            } else if(i == n - 1 || (c * Integer.compare(arr[i], arr[i+1]) != -1)) {
                max = Math.max(max, i - anchor + 1);
                anchor = i;
            }
        }
        return max;
    }
}
