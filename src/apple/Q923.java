package apple;

import java.util.Arrays;

// 3Sum With Multiplicity
public class Q923 {

    public static void main(String[] args) {
        Q923 q = new Q923();
        int[] A = {1,1,2,2,3,3,4,4,5,5};
        int[] B = {1,1,2,2,2,2};
        System.out.println(q.threeSumMulti(A, 8));  //20
        System.out.println(q.threeSumMulti(B, 5));  //12
    }
    // O(n^2) O(1)
    public int threeSumMulti(int[] A, int target) {
        if(A == null || A.length < 3) {
            return 0;
        }
        Arrays.sort(A);
        int mod = (int)1e9 + 7;
        int len = A.length;
        long ans = 0;
        for(int i = 0; i < len - 2; i++) {
            int t = target - A[i];
            int l = i + 1, r = len - 1;
            while(l < r) {
                int sum = A[l] + A[r];
                if(sum < t) {
                    l++;
                } else if(sum > t) {
                    r--;
                } else if(A[l] != A[r]) {
                    int cl = 1, cr = 1;
                    while(l + 1< r && A[l] == A[l + 1]) {
                        cl++;
                        l++;
                    }
                    while(l < r - 1 && A[r] == A[r - 1]) {
                        cr++;
                        r--;
                    }
                    ans = (ans + cl * cr) % mod;
                    l++;
                    r--;
                } else {
                    int count = r - l + 1;
                    ans = (ans + count * (count - 1) / 2) % mod;
                    break;
                }
            }
        }
        return (int)ans;
    }
    // O(n^2) O(n)
    public int threeSumMulti1(int[] A, int target) {
        if(A == null || A.length < 3) {
            return 0;
        }
        Arrays.sort(A);
        long ans = 0;
        int mod = (int)1e9+7;

        long[] count = new long[101];   // can't use int, because maximum element in A is 3000 => 3000^3 > Integer.Max_value
        int unique = 0;
        for(int a : A) {
            if(count[a] == 0) {
                unique++;
            }
            count[a]++;
        }
        int[] keys = new int[unique];
        int index = 0;
        for(int j = 0; j < 101; j++) {
            if(count[j] > 0) {
                keys[index++] = j;
            }
        }

        for(int i = 0; i < unique; i++) {
            int x = keys[i];
            int t = target - x;
            int j = i, k = unique - 1;
            while(j <= k) {
                int y = keys[j], z = keys[k];
                if(y + z < t) {
                    j++;
                } else if(y + z > t) {
                    k--;
                } else {
                    if(i < j && j < k) {    // can use "!="
                        ans += count[x] * count[y] * count[z];
                    } else if(i < j && j == k) {
                        ans += count[x] * count[y] * (count[y] - 1) / 2;
                    } else if(i == j && j < k) {
                        ans += count[z] * count[x] * (count[x] - 1) / 2;
                    } else {
                        ans += count[x] * (count[x] - 1) * (count[x] - 2) / 6;
                    }
                    ans %= mod;
                    j++;
                    k--;
                }
            }
        }
        return (int) ans;
    }
}
