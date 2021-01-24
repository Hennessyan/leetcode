package microsoft;
// Kth Missing Positive Number
public class Q1539 {

    public static void main(String[] args) {
        Q1539 q = new Q1539();
        int[] arr = {2,3,4,7,11};
        System.out.println(q.findKthPositive(arr, 5));  // 9
    }
    // brute force
    public int findKthPositive1(int[] arr, int k) {
        int i = 1;
        for(int num : arr) {
            while(i < num) {
                if(--k == 0) {
                    return i;
                }
                i++;
            }
            i++;
        }
        while(--k > 0) {
            i++;
        }
        return i;
    }

    public int findKthPositive2(int[] arr, int k) {
        int ret = k;
        int i = 0;
        while (i < arr.length && arr[i] <= ret) {
            ret++;
            i++;
        }
        return ret;
    }

    public int findKthPositive3(int[] arr, int k) {
        if(k <= arr[0] - 1) {
            return k;
        }
        int n = arr.length;
        k -= arr[0] - 1;
        for(int i = 1; i < n; i++) {
            int curMiss = arr[i] - arr[i - 1] - 1;
            if(k <= curMiss) {
                return arr[i - 1] + k;
            }
            k -= curMiss;
        }
        return arr[n - 1] + k;
    }
    // binary search O(lgn) O(1)
    // arr : 2 3 7 9 11 12
    // index : 0 1 2 3 4  5
    //  miss : 1 1 4 5 6 6   arr[index] - index - 1
    public int findKthPositive(int[] arr, int k) {
        int l = 0, r = arr.length - 1;
        while(l <= r) {
            int mid = l + (r - l) / 2;
            if(arr[mid] - mid - 1 < k) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l + k;   //arr[r] + k - (arr[r] - r - 1) => k + r + 1 => left + k (don't use arr[r], may throw OutOfIndex)
    }
}
